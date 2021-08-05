package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.Application;
import com.mycompany.myapp.domain.EstadisticaSSNeuronorma;
import com.mycompany.myapp.domain.Prueba;
import com.mycompany.myapp.domain.EdadTipoPrueba;
import com.mycompany.myapp.repository.EstadisticaSSNeuronormaRepository;
import com.mycompany.myapp.service.EstadisticaSSNeuronormaService;
import com.mycompany.myapp.service.dto.EstadisticaSSNeuronormaDTO;
import com.mycompany.myapp.service.mapper.EstadisticaSSNeuronormaMapper;
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
 * Integration tests for the {@link EstadisticaSSNeuronormaResource} REST controller.
 */
@SpringBootTest(classes = Application.class)
public class EstadisticaSSNeuronormaResourceIT {

    private static final Integer DEFAULT_PD = 0;
    private static final Integer UPDATED_PD = 1;

    private static final Integer DEFAULT_SCALED_SCORE = 0;
    private static final Integer UPDATED_SCALED_SCORE = 1;

    @Autowired
    private EstadisticaSSNeuronormaRepository estadisticaSSNeuronormaRepository;

    @Autowired
    private EstadisticaSSNeuronormaMapper estadisticaSSNeuronormaMapper;

    @Autowired
    private EstadisticaSSNeuronormaService estadisticaSSNeuronormaService;

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

    private MockMvc restEstadisticaSSNeuronormaMockMvc;

    private EstadisticaSSNeuronorma estadisticaSSNeuronorma;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EstadisticaSSNeuronormaResource estadisticaSSNeuronormaResource = new EstadisticaSSNeuronormaResource(estadisticaSSNeuronormaService);
        this.restEstadisticaSSNeuronormaMockMvc = MockMvcBuilders.standaloneSetup(estadisticaSSNeuronormaResource)
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
    public static EstadisticaSSNeuronorma createEntity(EntityManager em) {
        EstadisticaSSNeuronorma estadisticaSSNeuronorma = new EstadisticaSSNeuronorma()
            .pd(DEFAULT_PD)
            .scaledScore(DEFAULT_SCALED_SCORE);
        // Add required entity
        Prueba prueba;
        if (TestUtil.findAll(em, Prueba.class).isEmpty()) {
            prueba = PruebaResourceIT.createEntity(em);
            em.persist(prueba);
            em.flush();
        } else {
            prueba = TestUtil.findAll(em, Prueba.class).get(0);
        }
        estadisticaSSNeuronorma.setPrueba(prueba);
        // Add required entity
        EdadTipoPrueba edadTipoPrueba;
        if (TestUtil.findAll(em, EdadTipoPrueba.class).isEmpty()) {
            edadTipoPrueba = EdadTipoPruebaResourceIT.createEntity(em);
            em.persist(edadTipoPrueba);
            em.flush();
        } else {
            edadTipoPrueba = TestUtil.findAll(em, EdadTipoPrueba.class).get(0);
        }
        estadisticaSSNeuronorma.setEdadTipoPrueba(edadTipoPrueba);
        return estadisticaSSNeuronorma;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EstadisticaSSNeuronorma createUpdatedEntity(EntityManager em) {
        EstadisticaSSNeuronorma estadisticaSSNeuronorma = new EstadisticaSSNeuronorma()
            .pd(UPDATED_PD)
            .scaledScore(UPDATED_SCALED_SCORE);
        // Add required entity
        Prueba prueba;
        if (TestUtil.findAll(em, Prueba.class).isEmpty()) {
            prueba = PruebaResourceIT.createUpdatedEntity(em);
            em.persist(prueba);
            em.flush();
        } else {
            prueba = TestUtil.findAll(em, Prueba.class).get(0);
        }
        estadisticaSSNeuronorma.setPrueba(prueba);
        // Add required entity
        EdadTipoPrueba edadTipoPrueba;
        if (TestUtil.findAll(em, EdadTipoPrueba.class).isEmpty()) {
            edadTipoPrueba = EdadTipoPruebaResourceIT.createUpdatedEntity(em);
            em.persist(edadTipoPrueba);
            em.flush();
        } else {
            edadTipoPrueba = TestUtil.findAll(em, EdadTipoPrueba.class).get(0);
        }
        estadisticaSSNeuronorma.setEdadTipoPrueba(edadTipoPrueba);
        return estadisticaSSNeuronorma;
    }

    @BeforeEach
    public void initTest() {
        estadisticaSSNeuronorma = createEntity(em);
    }

    @Test
    @Transactional
    public void createEstadisticaSSNeuronorma() throws Exception {
        int databaseSizeBeforeCreate = estadisticaSSNeuronormaRepository.findAll().size();

        // Create the EstadisticaSSNeuronorma
        EstadisticaSSNeuronormaDTO estadisticaSSNeuronormaDTO = estadisticaSSNeuronormaMapper.toDto(estadisticaSSNeuronorma);
        restEstadisticaSSNeuronormaMockMvc.perform(post("/api/estadistica-ss-neuronormas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(estadisticaSSNeuronormaDTO)))
            .andExpect(status().isCreated());

        // Validate the EstadisticaSSNeuronorma in the database
        List<EstadisticaSSNeuronorma> estadisticaSSNeuronormaList = estadisticaSSNeuronormaRepository.findAll();
        assertThat(estadisticaSSNeuronormaList).hasSize(databaseSizeBeforeCreate + 1);
        EstadisticaSSNeuronorma testEstadisticaSSNeuronorma = estadisticaSSNeuronormaList.get(estadisticaSSNeuronormaList.size() - 1);
        assertThat(testEstadisticaSSNeuronorma.getPd()).isEqualTo(DEFAULT_PD);
        assertThat(testEstadisticaSSNeuronorma.getScaledScore()).isEqualTo(DEFAULT_SCALED_SCORE);
    }

    @Test
    @Transactional
    public void createEstadisticaSSNeuronormaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = estadisticaSSNeuronormaRepository.findAll().size();

        // Create the EstadisticaSSNeuronorma with an existing ID
        estadisticaSSNeuronorma.setId(1L);
        EstadisticaSSNeuronormaDTO estadisticaSSNeuronormaDTO = estadisticaSSNeuronormaMapper.toDto(estadisticaSSNeuronorma);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEstadisticaSSNeuronormaMockMvc.perform(post("/api/estadistica-ss-neuronormas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(estadisticaSSNeuronormaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EstadisticaSSNeuronorma in the database
        List<EstadisticaSSNeuronorma> estadisticaSSNeuronormaList = estadisticaSSNeuronormaRepository.findAll();
        assertThat(estadisticaSSNeuronormaList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkPdIsRequired() throws Exception {
        int databaseSizeBeforeTest = estadisticaSSNeuronormaRepository.findAll().size();
        // set the field null
        estadisticaSSNeuronorma.setPd(null);

        // Create the EstadisticaSSNeuronorma, which fails.
        EstadisticaSSNeuronormaDTO estadisticaSSNeuronormaDTO = estadisticaSSNeuronormaMapper.toDto(estadisticaSSNeuronorma);

        restEstadisticaSSNeuronormaMockMvc.perform(post("/api/estadistica-ss-neuronormas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(estadisticaSSNeuronormaDTO)))
            .andExpect(status().isBadRequest());

        List<EstadisticaSSNeuronorma> estadisticaSSNeuronormaList = estadisticaSSNeuronormaRepository.findAll();
        assertThat(estadisticaSSNeuronormaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkScaledScoreIsRequired() throws Exception {
        int databaseSizeBeforeTest = estadisticaSSNeuronormaRepository.findAll().size();
        // set the field null
        estadisticaSSNeuronorma.setScaledScore(null);

        // Create the EstadisticaSSNeuronorma, which fails.
        EstadisticaSSNeuronormaDTO estadisticaSSNeuronormaDTO = estadisticaSSNeuronormaMapper.toDto(estadisticaSSNeuronorma);

        restEstadisticaSSNeuronormaMockMvc.perform(post("/api/estadistica-ss-neuronormas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(estadisticaSSNeuronormaDTO)))
            .andExpect(status().isBadRequest());

        List<EstadisticaSSNeuronorma> estadisticaSSNeuronormaList = estadisticaSSNeuronormaRepository.findAll();
        assertThat(estadisticaSSNeuronormaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllEstadisticaSSNeuronormas() throws Exception {
        // Initialize the database
        estadisticaSSNeuronormaRepository.saveAndFlush(estadisticaSSNeuronorma);

        // Get all the estadisticaSSNeuronormaList
        restEstadisticaSSNeuronormaMockMvc.perform(get("/api/estadistica-ss-neuronormas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(estadisticaSSNeuronorma.getId().intValue())))
            .andExpect(jsonPath("$.[*].pd").value(hasItem(DEFAULT_PD)))
            .andExpect(jsonPath("$.[*].scaledScore").value(hasItem(DEFAULT_SCALED_SCORE)));
    }
    
    @Test
    @Transactional
    public void getEstadisticaSSNeuronorma() throws Exception {
        // Initialize the database
        estadisticaSSNeuronormaRepository.saveAndFlush(estadisticaSSNeuronorma);

        // Get the estadisticaSSNeuronorma
        restEstadisticaSSNeuronormaMockMvc.perform(get("/api/estadistica-ss-neuronormas/{id}", estadisticaSSNeuronorma.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(estadisticaSSNeuronorma.getId().intValue()))
            .andExpect(jsonPath("$.pd").value(DEFAULT_PD))
            .andExpect(jsonPath("$.scaledScore").value(DEFAULT_SCALED_SCORE));
    }

    @Test
    @Transactional
    public void getNonExistingEstadisticaSSNeuronorma() throws Exception {
        // Get the estadisticaSSNeuronorma
        restEstadisticaSSNeuronormaMockMvc.perform(get("/api/estadistica-ss-neuronormas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEstadisticaSSNeuronorma() throws Exception {
        // Initialize the database
        estadisticaSSNeuronormaRepository.saveAndFlush(estadisticaSSNeuronorma);

        int databaseSizeBeforeUpdate = estadisticaSSNeuronormaRepository.findAll().size();

        // Update the estadisticaSSNeuronorma
        EstadisticaSSNeuronorma updatedEstadisticaSSNeuronorma = estadisticaSSNeuronormaRepository.findById(estadisticaSSNeuronorma.getId()).get();
        // Disconnect from session so that the updates on updatedEstadisticaSSNeuronorma are not directly saved in db
        em.detach(updatedEstadisticaSSNeuronorma);
        updatedEstadisticaSSNeuronorma
            .pd(UPDATED_PD)
            .scaledScore(UPDATED_SCALED_SCORE);
        EstadisticaSSNeuronormaDTO estadisticaSSNeuronormaDTO = estadisticaSSNeuronormaMapper.toDto(updatedEstadisticaSSNeuronorma);

        restEstadisticaSSNeuronormaMockMvc.perform(put("/api/estadistica-ss-neuronormas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(estadisticaSSNeuronormaDTO)))
            .andExpect(status().isOk());

        // Validate the EstadisticaSSNeuronorma in the database
        List<EstadisticaSSNeuronorma> estadisticaSSNeuronormaList = estadisticaSSNeuronormaRepository.findAll();
        assertThat(estadisticaSSNeuronormaList).hasSize(databaseSizeBeforeUpdate);
        EstadisticaSSNeuronorma testEstadisticaSSNeuronorma = estadisticaSSNeuronormaList.get(estadisticaSSNeuronormaList.size() - 1);
        assertThat(testEstadisticaSSNeuronorma.getPd()).isEqualTo(UPDATED_PD);
        assertThat(testEstadisticaSSNeuronorma.getScaledScore()).isEqualTo(UPDATED_SCALED_SCORE);
    }

    @Test
    @Transactional
    public void updateNonExistingEstadisticaSSNeuronorma() throws Exception {
        int databaseSizeBeforeUpdate = estadisticaSSNeuronormaRepository.findAll().size();

        // Create the EstadisticaSSNeuronorma
        EstadisticaSSNeuronormaDTO estadisticaSSNeuronormaDTO = estadisticaSSNeuronormaMapper.toDto(estadisticaSSNeuronorma);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEstadisticaSSNeuronormaMockMvc.perform(put("/api/estadistica-ss-neuronormas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(estadisticaSSNeuronormaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EstadisticaSSNeuronorma in the database
        List<EstadisticaSSNeuronorma> estadisticaSSNeuronormaList = estadisticaSSNeuronormaRepository.findAll();
        assertThat(estadisticaSSNeuronormaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEstadisticaSSNeuronorma() throws Exception {
        // Initialize the database
        estadisticaSSNeuronormaRepository.saveAndFlush(estadisticaSSNeuronorma);

        int databaseSizeBeforeDelete = estadisticaSSNeuronormaRepository.findAll().size();

        // Delete the estadisticaSSNeuronorma
        restEstadisticaSSNeuronormaMockMvc.perform(delete("/api/estadistica-ss-neuronormas/{id}", estadisticaSSNeuronorma.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EstadisticaSSNeuronorma> estadisticaSSNeuronormaList = estadisticaSSNeuronormaRepository.findAll();
        assertThat(estadisticaSSNeuronormaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
