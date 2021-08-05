package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.Application;
import com.mycompany.myapp.domain.EstadisticaTBA;
import com.mycompany.myapp.domain.Prueba;
import com.mycompany.myapp.domain.CodigoEstudio;
import com.mycompany.myapp.domain.EdadTipoPrueba;
import com.mycompany.myapp.repository.EstadisticaTBARepository;
import com.mycompany.myapp.service.EstadisticaTBAService;
import com.mycompany.myapp.service.dto.EstadisticaTBADTO;
import com.mycompany.myapp.service.mapper.EstadisticaTBAMapper;
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

/**
 * Integration tests for the {@link EstadisticaTBAResource} REST controller.
 */
@SpringBootTest(classes = Application.class)
public class EstadisticaTBAResourceIT {

    private static final Float DEFAULT_MEDIA = 0F;
    private static final Float UPDATED_MEDIA = 1F;

    private static final Float DEFAULT_DESVIACION_TIPICA = 0F;
    private static final Float UPDATED_DESVIACION_TIPICA = 1F;

    @Autowired
    private EstadisticaTBARepository estadisticaTBARepository;

    @Autowired
    private EstadisticaTBAMapper estadisticaTBAMapper;

    @Autowired
    private EstadisticaTBAService estadisticaTBAService;

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

    private MockMvc restEstadisticaTBAMockMvc;

    private EstadisticaTBA estadisticaTBA;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EstadisticaTBAResource estadisticaTBAResource = new EstadisticaTBAResource(estadisticaTBAService);
        this.restEstadisticaTBAMockMvc = MockMvcBuilders.standaloneSetup(estadisticaTBAResource)
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
    public static EstadisticaTBA createEntity(EntityManager em) {
        EstadisticaTBA estadisticaTBA = new EstadisticaTBA()
            .media(DEFAULT_MEDIA)
            .desviacionTipica(DEFAULT_DESVIACION_TIPICA);
        // Add required entity
        Prueba prueba;
        if (TestUtil.findAll(em, Prueba.class).isEmpty()) {
            prueba = PruebaResourceIT.createEntity(em);
            em.persist(prueba);
            em.flush();
        } else {
            prueba = TestUtil.findAll(em, Prueba.class).get(0);
        }
        estadisticaTBA.setPrueba(prueba);
        // Add required entity
        CodigoEstudio codigoEstudio;
        if (TestUtil.findAll(em, CodigoEstudio.class).isEmpty()) {
            codigoEstudio = CodigoEstudioResourceIT.createEntity(em);
            em.persist(codigoEstudio);
            em.flush();
        } else {
            codigoEstudio = TestUtil.findAll(em, CodigoEstudio.class).get(0);
        }
        estadisticaTBA.setCodigoEstudio(codigoEstudio);
        // Add required entity
        EdadTipoPrueba edadTipoPrueba;
        if (TestUtil.findAll(em, EdadTipoPrueba.class).isEmpty()) {
            edadTipoPrueba = EdadTipoPruebaResourceIT.createEntity(em);
            em.persist(edadTipoPrueba);
            em.flush();
        } else {
            edadTipoPrueba = TestUtil.findAll(em, EdadTipoPrueba.class).get(0);
        }
        estadisticaTBA.setEdadTipoPrueba(edadTipoPrueba);
        return estadisticaTBA;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EstadisticaTBA createUpdatedEntity(EntityManager em) {
        EstadisticaTBA estadisticaTBA = new EstadisticaTBA()
            .media(UPDATED_MEDIA)
            .desviacionTipica(UPDATED_DESVIACION_TIPICA);
        // Add required entity
        Prueba prueba;
        if (TestUtil.findAll(em, Prueba.class).isEmpty()) {
            prueba = PruebaResourceIT.createUpdatedEntity(em);
            em.persist(prueba);
            em.flush();
        } else {
            prueba = TestUtil.findAll(em, Prueba.class).get(0);
        }
        estadisticaTBA.setPrueba(prueba);
        // Add required entity
        CodigoEstudio codigoEstudio;
        if (TestUtil.findAll(em, CodigoEstudio.class).isEmpty()) {
            codigoEstudio = CodigoEstudioResourceIT.createUpdatedEntity(em);
            em.persist(codigoEstudio);
            em.flush();
        } else {
            codigoEstudio = TestUtil.findAll(em, CodigoEstudio.class).get(0);
        }
        estadisticaTBA.setCodigoEstudio(codigoEstudio);
        // Add required entity
        EdadTipoPrueba edadTipoPrueba;
        if (TestUtil.findAll(em, EdadTipoPrueba.class).isEmpty()) {
            edadTipoPrueba = EdadTipoPruebaResourceIT.createUpdatedEntity(em);
            em.persist(edadTipoPrueba);
            em.flush();
        } else {
            edadTipoPrueba = TestUtil.findAll(em, EdadTipoPrueba.class).get(0);
        }
        estadisticaTBA.setEdadTipoPrueba(edadTipoPrueba);
        return estadisticaTBA;
    }

    @BeforeEach
    public void initTest() {
        estadisticaTBA = createEntity(em);
    }

    @Test
    @Transactional
    public void createEstadisticaTBA() throws Exception {
        int databaseSizeBeforeCreate = estadisticaTBARepository.findAll().size();

        // Create the EstadisticaTBA
        EstadisticaTBADTO estadisticaTBADTO = estadisticaTBAMapper.toDto(estadisticaTBA);
        restEstadisticaTBAMockMvc.perform(post("/api/estadistica-tbas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(estadisticaTBADTO)))
            .andExpect(status().isCreated());

        // Validate the EstadisticaTBA in the database
        List<EstadisticaTBA> estadisticaTBAList = estadisticaTBARepository.findAll();
        assertThat(estadisticaTBAList).hasSize(databaseSizeBeforeCreate + 1);
        EstadisticaTBA testEstadisticaTBA = estadisticaTBAList.get(estadisticaTBAList.size() - 1);
        assertThat(testEstadisticaTBA.getMedia()).isEqualTo(DEFAULT_MEDIA);
        assertThat(testEstadisticaTBA.getDesviacionTipica()).isEqualTo(DEFAULT_DESVIACION_TIPICA);
    }

    @Test
    @Transactional
    public void createEstadisticaTBAWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = estadisticaTBARepository.findAll().size();

        // Create the EstadisticaTBA with an existing ID
        estadisticaTBA.setId(1L);
        EstadisticaTBADTO estadisticaTBADTO = estadisticaTBAMapper.toDto(estadisticaTBA);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEstadisticaTBAMockMvc.perform(post("/api/estadistica-tbas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(estadisticaTBADTO)))
            .andExpect(status().isBadRequest());

        // Validate the EstadisticaTBA in the database
        List<EstadisticaTBA> estadisticaTBAList = estadisticaTBARepository.findAll();
        assertThat(estadisticaTBAList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkMediaIsRequired() throws Exception {
        int databaseSizeBeforeTest = estadisticaTBARepository.findAll().size();
        // set the field null
        estadisticaTBA.setMedia(null);

        // Create the EstadisticaTBA, which fails.
        EstadisticaTBADTO estadisticaTBADTO = estadisticaTBAMapper.toDto(estadisticaTBA);

        restEstadisticaTBAMockMvc.perform(post("/api/estadistica-tbas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(estadisticaTBADTO)))
            .andExpect(status().isBadRequest());

        List<EstadisticaTBA> estadisticaTBAList = estadisticaTBARepository.findAll();
        assertThat(estadisticaTBAList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDesviacionTipicaIsRequired() throws Exception {
        int databaseSizeBeforeTest = estadisticaTBARepository.findAll().size();
        // set the field null
        estadisticaTBA.setDesviacionTipica(null);

        // Create the EstadisticaTBA, which fails.
        EstadisticaTBADTO estadisticaTBADTO = estadisticaTBAMapper.toDto(estadisticaTBA);

        restEstadisticaTBAMockMvc.perform(post("/api/estadistica-tbas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(estadisticaTBADTO)))
            .andExpect(status().isBadRequest());

        List<EstadisticaTBA> estadisticaTBAList = estadisticaTBARepository.findAll();
        assertThat(estadisticaTBAList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllEstadisticaTBAS() throws Exception {
        // Initialize the database
        estadisticaTBARepository.saveAndFlush(estadisticaTBA);

        // Get all the estadisticaTBAList
        restEstadisticaTBAMockMvc.perform(get("/api/estadistica-tbas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(estadisticaTBA.getId().intValue())))
            .andExpect(jsonPath("$.[*].media").value(hasItem(DEFAULT_MEDIA.doubleValue())))
            .andExpect(jsonPath("$.[*].desviacionTipica").value(hasItem(DEFAULT_DESVIACION_TIPICA.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getEstadisticaTBA() throws Exception {
        // Initialize the database
        estadisticaTBARepository.saveAndFlush(estadisticaTBA);

        // Get the estadisticaTBA
        restEstadisticaTBAMockMvc.perform(get("/api/estadistica-tbas/{id}", estadisticaTBA.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(estadisticaTBA.getId().intValue()))
            .andExpect(jsonPath("$.media").value(DEFAULT_MEDIA.doubleValue()))
            .andExpect(jsonPath("$.desviacionTipica").value(DEFAULT_DESVIACION_TIPICA.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingEstadisticaTBA() throws Exception {
        // Get the estadisticaTBA
        restEstadisticaTBAMockMvc.perform(get("/api/estadistica-tbas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEstadisticaTBA() throws Exception {
        // Initialize the database
        estadisticaTBARepository.saveAndFlush(estadisticaTBA);

        int databaseSizeBeforeUpdate = estadisticaTBARepository.findAll().size();

        // Update the estadisticaTBA
        EstadisticaTBA updatedEstadisticaTBA = estadisticaTBARepository.findById(estadisticaTBA.getId()).get();
        // Disconnect from session so that the updates on updatedEstadisticaTBA are not directly saved in db
        em.detach(updatedEstadisticaTBA);
        updatedEstadisticaTBA
            .media(UPDATED_MEDIA)
            .desviacionTipica(UPDATED_DESVIACION_TIPICA);
        EstadisticaTBADTO estadisticaTBADTO = estadisticaTBAMapper.toDto(updatedEstadisticaTBA);

        restEstadisticaTBAMockMvc.perform(put("/api/estadistica-tbas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(estadisticaTBADTO)))
            .andExpect(status().isOk());

        // Validate the EstadisticaTBA in the database
        List<EstadisticaTBA> estadisticaTBAList = estadisticaTBARepository.findAll();
        assertThat(estadisticaTBAList).hasSize(databaseSizeBeforeUpdate);
        EstadisticaTBA testEstadisticaTBA = estadisticaTBAList.get(estadisticaTBAList.size() - 1);
        assertThat(testEstadisticaTBA.getMedia()).isEqualTo(UPDATED_MEDIA);
        assertThat(testEstadisticaTBA.getDesviacionTipica()).isEqualTo(UPDATED_DESVIACION_TIPICA);
    }

    @Test
    @Transactional
    public void updateNonExistingEstadisticaTBA() throws Exception {
        int databaseSizeBeforeUpdate = estadisticaTBARepository.findAll().size();

        // Create the EstadisticaTBA
        EstadisticaTBADTO estadisticaTBADTO = estadisticaTBAMapper.toDto(estadisticaTBA);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEstadisticaTBAMockMvc.perform(put("/api/estadistica-tbas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(estadisticaTBADTO)))
            .andExpect(status().isBadRequest());

        // Validate the EstadisticaTBA in the database
        List<EstadisticaTBA> estadisticaTBAList = estadisticaTBARepository.findAll();
        assertThat(estadisticaTBAList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEstadisticaTBA() throws Exception {
        // Initialize the database
        estadisticaTBARepository.saveAndFlush(estadisticaTBA);

        int databaseSizeBeforeDelete = estadisticaTBARepository.findAll().size();

        // Delete the estadisticaTBA
        restEstadisticaTBAMockMvc.perform(delete("/api/estadistica-tbas/{id}", estadisticaTBA.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EstadisticaTBA> estadisticaTBAList = estadisticaTBARepository.findAll();
        assertThat(estadisticaTBAList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
