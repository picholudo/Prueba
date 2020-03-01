package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.Application;
import com.mycompany.myapp.domain.Estadisticas;
import com.mycompany.myapp.domain.ZScore;
import com.mycompany.myapp.repository.EstadisticasRepository;
import com.mycompany.myapp.service.EstadisticasService;
import com.mycompany.myapp.service.dto.EstadisticasDTO;
import com.mycompany.myapp.service.mapper.EstadisticasMapper;
import com.mycompany.myapp.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static com.mycompany.myapp.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.domain.enumeration.Nivelestudio;
import com.mycompany.myapp.domain.enumeration.Sexo;
/**
 * Integration tests for the {@link EstadisticasResource} REST controller.
 */
@SpringBootTest(classes = Application.class)
public class EstadisticasResourceIT {

    private static final Integer DEFAULT_EDAD = 60;
    private static final Integer UPDATED_EDAD = 61;

    private static final Nivelestudio DEFAULT_ESTUDIOS = Nivelestudio.ILETRADO;
    private static final Nivelestudio UPDATED_ESTUDIOS = Nivelestudio.LEER_Y_ESCRIBIR;

    private static final Sexo DEFAULT_SEXO = Sexo.Hombre;
    private static final Sexo UPDATED_SEXO = Sexo.Mujer;

    private static final String DEFAULT_PRUEBA = "AAAAAAAAAA";
    private static final String UPDATED_PRUEBA = "BBBBBBBBBB";

    private static final Long DEFAULT_MEDIA = 1L;
    private static final Long UPDATED_MEDIA = 2L;

    private static final Long DEFAULT_DESVIACION = 1L;
    private static final Long UPDATED_DESVIACION = 2L;

    @Autowired
    private EstadisticasRepository estadisticasRepository;

    @Autowired
    private EstadisticasMapper estadisticasMapper;

    @Autowired
    private EstadisticasService estadisticasService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restEstadisticasMockMvc;

    private Estadisticas estadisticas;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EstadisticasResource estadisticasResource = new EstadisticasResource(estadisticasService);
        this.restEstadisticasMockMvc = MockMvcBuilders.standaloneSetup(estadisticasResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Estadisticas createEntity(EntityManager em) {
        Estadisticas estadisticas = new Estadisticas()
            .edad(DEFAULT_EDAD)
            .estudios(DEFAULT_ESTUDIOS)
            .sexo(DEFAULT_SEXO)
            .prueba(DEFAULT_PRUEBA)
            .media(DEFAULT_MEDIA)
            .desviacion(DEFAULT_DESVIACION);
        // Add required entity
        ZScore zScore;
        if (TestUtil.findAll(em, ZScore.class).isEmpty()) {
            zScore = ZScoreResourceIT.createEntity(em);
            em.persist(zScore);
            em.flush();
        } else {
            zScore = TestUtil.findAll(em, ZScore.class).get(0);
        }
        estadisticas.setZscore(zScore);
        return estadisticas;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Estadisticas createUpdatedEntity(EntityManager em) {
        Estadisticas estadisticas = new Estadisticas()
            .edad(UPDATED_EDAD)
            .estudios(UPDATED_ESTUDIOS)
            .sexo(UPDATED_SEXO)
            .prueba(UPDATED_PRUEBA)
            .media(UPDATED_MEDIA)
            .desviacion(UPDATED_DESVIACION);
        // Add required entity
        ZScore zScore;
        if (TestUtil.findAll(em, ZScore.class).isEmpty()) {
            zScore = ZScoreResourceIT.createUpdatedEntity(em);
            em.persist(zScore);
            em.flush();
        } else {
            zScore = TestUtil.findAll(em, ZScore.class).get(0);
        }
        estadisticas.setZscore(zScore);
        return estadisticas;
    }

    @BeforeEach
    public void initTest() {
        estadisticas = createEntity(em);
    }

    @Test
    @Transactional
    public void createEstadisticas() throws Exception {
        int databaseSizeBeforeCreate = estadisticasRepository.findAll().size();

        // Create the Estadisticas
        EstadisticasDTO estadisticasDTO = estadisticasMapper.toDto(estadisticas);
        restEstadisticasMockMvc.perform(post("/api/estadisticas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(estadisticasDTO)))
            .andExpect(status().isCreated());

        // Validate the Estadisticas in the database
        List<Estadisticas> estadisticasList = estadisticasRepository.findAll();
        assertThat(estadisticasList).hasSize(databaseSizeBeforeCreate + 1);
        Estadisticas testEstadisticas = estadisticasList.get(estadisticasList.size() - 1);
        assertThat(testEstadisticas.getEdad()).isEqualTo(DEFAULT_EDAD);
        assertThat(testEstadisticas.getEstudios()).isEqualTo(DEFAULT_ESTUDIOS);
        assertThat(testEstadisticas.getSexo()).isEqualTo(DEFAULT_SEXO);
        assertThat(testEstadisticas.getPrueba()).isEqualTo(DEFAULT_PRUEBA);
        assertThat(testEstadisticas.getMedia()).isEqualTo(DEFAULT_MEDIA);
        assertThat(testEstadisticas.getDesviacion()).isEqualTo(DEFAULT_DESVIACION);
    }

    @Test
    @Transactional
    public void createEstadisticasWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = estadisticasRepository.findAll().size();

        // Create the Estadisticas with an existing ID
        estadisticas.setId(1L);
        EstadisticasDTO estadisticasDTO = estadisticasMapper.toDto(estadisticas);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEstadisticasMockMvc.perform(post("/api/estadisticas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(estadisticasDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Estadisticas in the database
        List<Estadisticas> estadisticasList = estadisticasRepository.findAll();
        assertThat(estadisticasList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkEstudiosIsRequired() throws Exception {
        int databaseSizeBeforeTest = estadisticasRepository.findAll().size();
        // set the field null
        estadisticas.setEstudios(null);

        // Create the Estadisticas, which fails.
        EstadisticasDTO estadisticasDTO = estadisticasMapper.toDto(estadisticas);

        restEstadisticasMockMvc.perform(post("/api/estadisticas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(estadisticasDTO)))
            .andExpect(status().isBadRequest());

        List<Estadisticas> estadisticasList = estadisticasRepository.findAll();
        assertThat(estadisticasList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSexoIsRequired() throws Exception {
        int databaseSizeBeforeTest = estadisticasRepository.findAll().size();
        // set the field null
        estadisticas.setSexo(null);

        // Create the Estadisticas, which fails.
        EstadisticasDTO estadisticasDTO = estadisticasMapper.toDto(estadisticas);

        restEstadisticasMockMvc.perform(post("/api/estadisticas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(estadisticasDTO)))
            .andExpect(status().isBadRequest());

        List<Estadisticas> estadisticasList = estadisticasRepository.findAll();
        assertThat(estadisticasList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPruebaIsRequired() throws Exception {
        int databaseSizeBeforeTest = estadisticasRepository.findAll().size();
        // set the field null
        estadisticas.setPrueba(null);

        // Create the Estadisticas, which fails.
        EstadisticasDTO estadisticasDTO = estadisticasMapper.toDto(estadisticas);

        restEstadisticasMockMvc.perform(post("/api/estadisticas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(estadisticasDTO)))
            .andExpect(status().isBadRequest());

        List<Estadisticas> estadisticasList = estadisticasRepository.findAll();
        assertThat(estadisticasList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMediaIsRequired() throws Exception {
        int databaseSizeBeforeTest = estadisticasRepository.findAll().size();
        // set the field null
        estadisticas.setMedia(null);

        // Create the Estadisticas, which fails.
        EstadisticasDTO estadisticasDTO = estadisticasMapper.toDto(estadisticas);

        restEstadisticasMockMvc.perform(post("/api/estadisticas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(estadisticasDTO)))
            .andExpect(status().isBadRequest());

        List<Estadisticas> estadisticasList = estadisticasRepository.findAll();
        assertThat(estadisticasList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDesviacionIsRequired() throws Exception {
        int databaseSizeBeforeTest = estadisticasRepository.findAll().size();
        // set the field null
        estadisticas.setDesviacion(null);

        // Create the Estadisticas, which fails.
        EstadisticasDTO estadisticasDTO = estadisticasMapper.toDto(estadisticas);

        restEstadisticasMockMvc.perform(post("/api/estadisticas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(estadisticasDTO)))
            .andExpect(status().isBadRequest());

        List<Estadisticas> estadisticasList = estadisticasRepository.findAll();
        assertThat(estadisticasList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllEstadisticas() throws Exception {
        // Initialize the database
        estadisticasRepository.saveAndFlush(estadisticas);

        // Get all the estadisticasList
        restEstadisticasMockMvc.perform(get("/api/estadisticas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(estadisticas.getId().intValue())))
            .andExpect(jsonPath("$.[*].edad").value(hasItem(DEFAULT_EDAD)))
            .andExpect(jsonPath("$.[*].estudios").value(hasItem(DEFAULT_ESTUDIOS.toString())))
            .andExpect(jsonPath("$.[*].sexo").value(hasItem(DEFAULT_SEXO.toString())))
            .andExpect(jsonPath("$.[*].prueba").value(hasItem(DEFAULT_PRUEBA)))
            .andExpect(jsonPath("$.[*].media").value(hasItem(DEFAULT_MEDIA.intValue())))
            .andExpect(jsonPath("$.[*].desviacion").value(hasItem(DEFAULT_DESVIACION.intValue())));
    }
    
    @Test
    @Transactional
    public void getEstadisticas() throws Exception {
        // Initialize the database
        estadisticasRepository.saveAndFlush(estadisticas);

        // Get the estadisticas
        restEstadisticasMockMvc.perform(get("/api/estadisticas/{id}", estadisticas.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(estadisticas.getId().intValue()))
            .andExpect(jsonPath("$.edad").value(DEFAULT_EDAD))
            .andExpect(jsonPath("$.estudios").value(DEFAULT_ESTUDIOS.toString()))
            .andExpect(jsonPath("$.sexo").value(DEFAULT_SEXO.toString()))
            .andExpect(jsonPath("$.prueba").value(DEFAULT_PRUEBA))
            .andExpect(jsonPath("$.media").value(DEFAULT_MEDIA.intValue()))
            .andExpect(jsonPath("$.desviacion").value(DEFAULT_DESVIACION.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingEstadisticas() throws Exception {
        // Get the estadisticas
        restEstadisticasMockMvc.perform(get("/api/estadisticas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEstadisticas() throws Exception {
        // Initialize the database
        estadisticasRepository.saveAndFlush(estadisticas);

        int databaseSizeBeforeUpdate = estadisticasRepository.findAll().size();

        // Update the estadisticas
        Estadisticas updatedEstadisticas = estadisticasRepository.findById(estadisticas.getId()).get();
        // Disconnect from session so that the updates on updatedEstadisticas are not directly saved in db
        em.detach(updatedEstadisticas);
        updatedEstadisticas
            .edad(UPDATED_EDAD)
            .estudios(UPDATED_ESTUDIOS)
            .sexo(UPDATED_SEXO)
            .prueba(UPDATED_PRUEBA)
            .media(UPDATED_MEDIA)
            .desviacion(UPDATED_DESVIACION);
        EstadisticasDTO estadisticasDTO = estadisticasMapper.toDto(updatedEstadisticas);

        restEstadisticasMockMvc.perform(put("/api/estadisticas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(estadisticasDTO)))
            .andExpect(status().isOk());

        // Validate the Estadisticas in the database
        List<Estadisticas> estadisticasList = estadisticasRepository.findAll();
        assertThat(estadisticasList).hasSize(databaseSizeBeforeUpdate);
        Estadisticas testEstadisticas = estadisticasList.get(estadisticasList.size() - 1);
        assertThat(testEstadisticas.getEdad()).isEqualTo(UPDATED_EDAD);
        assertThat(testEstadisticas.getEstudios()).isEqualTo(UPDATED_ESTUDIOS);
        assertThat(testEstadisticas.getSexo()).isEqualTo(UPDATED_SEXO);
        assertThat(testEstadisticas.getPrueba()).isEqualTo(UPDATED_PRUEBA);
        assertThat(testEstadisticas.getMedia()).isEqualTo(UPDATED_MEDIA);
        assertThat(testEstadisticas.getDesviacion()).isEqualTo(UPDATED_DESVIACION);
    }

    @Test
    @Transactional
    public void updateNonExistingEstadisticas() throws Exception {
        int databaseSizeBeforeUpdate = estadisticasRepository.findAll().size();

        // Create the Estadisticas
        EstadisticasDTO estadisticasDTO = estadisticasMapper.toDto(estadisticas);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEstadisticasMockMvc.perform(put("/api/estadisticas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(estadisticasDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Estadisticas in the database
        List<Estadisticas> estadisticasList = estadisticasRepository.findAll();
        assertThat(estadisticasList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEstadisticas() throws Exception {
        // Initialize the database
        estadisticasRepository.saveAndFlush(estadisticas);

        int databaseSizeBeforeDelete = estadisticasRepository.findAll().size();

        // Delete the estadisticas
        restEstadisticasMockMvc.perform(delete("/api/estadisticas/{id}", estadisticas.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Estadisticas> estadisticasList = estadisticasRepository.findAll();
        assertThat(estadisticasList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
