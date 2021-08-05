package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.Application;
import com.mycompany.myapp.domain.EstadisticaMMSE;
import com.mycompany.myapp.domain.CodigoEstudio;
import com.mycompany.myapp.domain.EdadTipoPrueba;
import com.mycompany.myapp.repository.EstadisticaMMSERepository;
import com.mycompany.myapp.service.EstadisticaMMSEService;
import com.mycompany.myapp.service.dto.EstadisticaMMSEDTO;
import com.mycompany.myapp.service.mapper.EstadisticaMMSEMapper;
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
 * Integration tests for the {@link EstadisticaMMSEResource} REST controller.
 */
@SpringBootTest(classes = Application.class)
public class EstadisticaMMSEResourceIT {

    private static final Float DEFAULT_MEDIA = 0F;
    private static final Float UPDATED_MEDIA = 1F;

    private static final Float DEFAULT_DESVIACION_TIPICA = 0F;
    private static final Float UPDATED_DESVIACION_TIPICA = 1F;

    @Autowired
    private EstadisticaMMSERepository estadisticaMMSERepository;

    @Autowired
    private EstadisticaMMSEMapper estadisticaMMSEMapper;

    @Autowired
    private EstadisticaMMSEService estadisticaMMSEService;

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

    private MockMvc restEstadisticaMMSEMockMvc;

    private EstadisticaMMSE estadisticaMMSE;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EstadisticaMMSEResource estadisticaMMSEResource = new EstadisticaMMSEResource(estadisticaMMSEService);
        this.restEstadisticaMMSEMockMvc = MockMvcBuilders.standaloneSetup(estadisticaMMSEResource)
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
    public static EstadisticaMMSE createEntity(EntityManager em) {
        EstadisticaMMSE estadisticaMMSE = new EstadisticaMMSE()
            .media(DEFAULT_MEDIA)
            .desviacionTipica(DEFAULT_DESVIACION_TIPICA);
        // Add required entity
        CodigoEstudio codigoEstudio;
        if (TestUtil.findAll(em, CodigoEstudio.class).isEmpty()) {
            codigoEstudio = CodigoEstudioResourceIT.createEntity(em);
            em.persist(codigoEstudio);
            em.flush();
        } else {
            codigoEstudio = TestUtil.findAll(em, CodigoEstudio.class).get(0);
        }
        estadisticaMMSE.setCodigoEstudio(codigoEstudio);
        // Add required entity
        EdadTipoPrueba edadTipoPrueba;
        if (TestUtil.findAll(em, EdadTipoPrueba.class).isEmpty()) {
            edadTipoPrueba = EdadTipoPruebaResourceIT.createEntity(em);
            em.persist(edadTipoPrueba);
            em.flush();
        } else {
            edadTipoPrueba = TestUtil.findAll(em, EdadTipoPrueba.class).get(0);
        }
        estadisticaMMSE.setEdadTipoPrueba(edadTipoPrueba);
        return estadisticaMMSE;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EstadisticaMMSE createUpdatedEntity(EntityManager em) {
        EstadisticaMMSE estadisticaMMSE = new EstadisticaMMSE()
            .media(UPDATED_MEDIA)
            .desviacionTipica(UPDATED_DESVIACION_TIPICA);
        // Add required entity
        CodigoEstudio codigoEstudio;
        if (TestUtil.findAll(em, CodigoEstudio.class).isEmpty()) {
            codigoEstudio = CodigoEstudioResourceIT.createUpdatedEntity(em);
            em.persist(codigoEstudio);
            em.flush();
        } else {
            codigoEstudio = TestUtil.findAll(em, CodigoEstudio.class).get(0);
        }
        estadisticaMMSE.setCodigoEstudio(codigoEstudio);
        // Add required entity
        EdadTipoPrueba edadTipoPrueba;
        if (TestUtil.findAll(em, EdadTipoPrueba.class).isEmpty()) {
            edadTipoPrueba = EdadTipoPruebaResourceIT.createUpdatedEntity(em);
            em.persist(edadTipoPrueba);
            em.flush();
        } else {
            edadTipoPrueba = TestUtil.findAll(em, EdadTipoPrueba.class).get(0);
        }
        estadisticaMMSE.setEdadTipoPrueba(edadTipoPrueba);
        return estadisticaMMSE;
    }

    @BeforeEach
    public void initTest() {
        estadisticaMMSE = createEntity(em);
    }

    @Test
    @Transactional
    public void createEstadisticaMMSE() throws Exception {
        int databaseSizeBeforeCreate = estadisticaMMSERepository.findAll().size();

        // Create the EstadisticaMMSE
        EstadisticaMMSEDTO estadisticaMMSEDTO = estadisticaMMSEMapper.toDto(estadisticaMMSE);
        restEstadisticaMMSEMockMvc.perform(post("/api/estadistica-mmses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(estadisticaMMSEDTO)))
            .andExpect(status().isCreated());

        // Validate the EstadisticaMMSE in the database
        List<EstadisticaMMSE> estadisticaMMSEList = estadisticaMMSERepository.findAll();
        assertThat(estadisticaMMSEList).hasSize(databaseSizeBeforeCreate + 1);
        EstadisticaMMSE testEstadisticaMMSE = estadisticaMMSEList.get(estadisticaMMSEList.size() - 1);
        assertThat(testEstadisticaMMSE.getMedia()).isEqualTo(DEFAULT_MEDIA);
        assertThat(testEstadisticaMMSE.getDesviacionTipica()).isEqualTo(DEFAULT_DESVIACION_TIPICA);
    }

    @Test
    @Transactional
    public void createEstadisticaMMSEWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = estadisticaMMSERepository.findAll().size();

        // Create the EstadisticaMMSE with an existing ID
        estadisticaMMSE.setId(1L);
        EstadisticaMMSEDTO estadisticaMMSEDTO = estadisticaMMSEMapper.toDto(estadisticaMMSE);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEstadisticaMMSEMockMvc.perform(post("/api/estadistica-mmses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(estadisticaMMSEDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EstadisticaMMSE in the database
        List<EstadisticaMMSE> estadisticaMMSEList = estadisticaMMSERepository.findAll();
        assertThat(estadisticaMMSEList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkMediaIsRequired() throws Exception {
        int databaseSizeBeforeTest = estadisticaMMSERepository.findAll().size();
        // set the field null
        estadisticaMMSE.setMedia(null);

        // Create the EstadisticaMMSE, which fails.
        EstadisticaMMSEDTO estadisticaMMSEDTO = estadisticaMMSEMapper.toDto(estadisticaMMSE);

        restEstadisticaMMSEMockMvc.perform(post("/api/estadistica-mmses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(estadisticaMMSEDTO)))
            .andExpect(status().isBadRequest());

        List<EstadisticaMMSE> estadisticaMMSEList = estadisticaMMSERepository.findAll();
        assertThat(estadisticaMMSEList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDesviacionTipicaIsRequired() throws Exception {
        int databaseSizeBeforeTest = estadisticaMMSERepository.findAll().size();
        // set the field null
        estadisticaMMSE.setDesviacionTipica(null);

        // Create the EstadisticaMMSE, which fails.
        EstadisticaMMSEDTO estadisticaMMSEDTO = estadisticaMMSEMapper.toDto(estadisticaMMSE);

        restEstadisticaMMSEMockMvc.perform(post("/api/estadistica-mmses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(estadisticaMMSEDTO)))
            .andExpect(status().isBadRequest());

        List<EstadisticaMMSE> estadisticaMMSEList = estadisticaMMSERepository.findAll();
        assertThat(estadisticaMMSEList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllEstadisticaMMSES() throws Exception {
        // Initialize the database
        estadisticaMMSERepository.saveAndFlush(estadisticaMMSE);

        // Get all the estadisticaMMSEList
        restEstadisticaMMSEMockMvc.perform(get("/api/estadistica-mmses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(estadisticaMMSE.getId().intValue())))
            .andExpect(jsonPath("$.[*].media").value(hasItem(DEFAULT_MEDIA.doubleValue())))
            .andExpect(jsonPath("$.[*].desviacionTipica").value(hasItem(DEFAULT_DESVIACION_TIPICA.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getEstadisticaMMSE() throws Exception {
        // Initialize the database
        estadisticaMMSERepository.saveAndFlush(estadisticaMMSE);

        // Get the estadisticaMMSE
        restEstadisticaMMSEMockMvc.perform(get("/api/estadistica-mmses/{id}", estadisticaMMSE.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(estadisticaMMSE.getId().intValue()))
            .andExpect(jsonPath("$.media").value(DEFAULT_MEDIA.doubleValue()))
            .andExpect(jsonPath("$.desviacionTipica").value(DEFAULT_DESVIACION_TIPICA.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingEstadisticaMMSE() throws Exception {
        // Get the estadisticaMMSE
        restEstadisticaMMSEMockMvc.perform(get("/api/estadistica-mmses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEstadisticaMMSE() throws Exception {
        // Initialize the database
        estadisticaMMSERepository.saveAndFlush(estadisticaMMSE);

        int databaseSizeBeforeUpdate = estadisticaMMSERepository.findAll().size();

        // Update the estadisticaMMSE
        EstadisticaMMSE updatedEstadisticaMMSE = estadisticaMMSERepository.findById(estadisticaMMSE.getId()).get();
        // Disconnect from session so that the updates on updatedEstadisticaMMSE are not directly saved in db
        em.detach(updatedEstadisticaMMSE);
        updatedEstadisticaMMSE
            .media(UPDATED_MEDIA)
            .desviacionTipica(UPDATED_DESVIACION_TIPICA);
        EstadisticaMMSEDTO estadisticaMMSEDTO = estadisticaMMSEMapper.toDto(updatedEstadisticaMMSE);

        restEstadisticaMMSEMockMvc.perform(put("/api/estadistica-mmses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(estadisticaMMSEDTO)))
            .andExpect(status().isOk());

        // Validate the EstadisticaMMSE in the database
        List<EstadisticaMMSE> estadisticaMMSEList = estadisticaMMSERepository.findAll();
        assertThat(estadisticaMMSEList).hasSize(databaseSizeBeforeUpdate);
        EstadisticaMMSE testEstadisticaMMSE = estadisticaMMSEList.get(estadisticaMMSEList.size() - 1);
        assertThat(testEstadisticaMMSE.getMedia()).isEqualTo(UPDATED_MEDIA);
        assertThat(testEstadisticaMMSE.getDesviacionTipica()).isEqualTo(UPDATED_DESVIACION_TIPICA);
    }

    @Test
    @Transactional
    public void updateNonExistingEstadisticaMMSE() throws Exception {
        int databaseSizeBeforeUpdate = estadisticaMMSERepository.findAll().size();

        // Create the EstadisticaMMSE
        EstadisticaMMSEDTO estadisticaMMSEDTO = estadisticaMMSEMapper.toDto(estadisticaMMSE);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEstadisticaMMSEMockMvc.perform(put("/api/estadistica-mmses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(estadisticaMMSEDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EstadisticaMMSE in the database
        List<EstadisticaMMSE> estadisticaMMSEList = estadisticaMMSERepository.findAll();
        assertThat(estadisticaMMSEList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEstadisticaMMSE() throws Exception {
        // Initialize the database
        estadisticaMMSERepository.saveAndFlush(estadisticaMMSE);

        int databaseSizeBeforeDelete = estadisticaMMSERepository.findAll().size();

        // Delete the estadisticaMMSE
        restEstadisticaMMSEMockMvc.perform(delete("/api/estadistica-mmses/{id}", estadisticaMMSE.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EstadisticaMMSE> estadisticaMMSEList = estadisticaMMSERepository.findAll();
        assertThat(estadisticaMMSEList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
