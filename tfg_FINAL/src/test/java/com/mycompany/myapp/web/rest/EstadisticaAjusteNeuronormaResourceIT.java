package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.Application;
import com.mycompany.myapp.domain.EstadisticaAjusteNeuronorma;
import com.mycompany.myapp.domain.Prueba;
import com.mycompany.myapp.domain.CodigoEstudio;
import com.mycompany.myapp.repository.EstadisticaAjusteNeuronormaRepository;
import com.mycompany.myapp.service.EstadisticaAjusteNeuronormaService;
import com.mycompany.myapp.service.dto.EstadisticaAjusteNeuronormaDTO;
import com.mycompany.myapp.service.mapper.EstadisticaAjusteNeuronormaMapper;
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
 * Integration tests for the {@link EstadisticaAjusteNeuronormaResource} REST controller.
 */
@SpringBootTest(classes = Application.class)
public class EstadisticaAjusteNeuronormaResourceIT {

    private static final Integer DEFAULT_SCALED_SCORE = 0;
    private static final Integer UPDATED_SCALED_SCORE = 1;

    private static final Integer DEFAULT_AJUSTE_ESTUDIOS = 0;
    private static final Integer UPDATED_AJUSTE_ESTUDIOS = 1;

    @Autowired
    private EstadisticaAjusteNeuronormaRepository estadisticaAjusteNeuronormaRepository;

    @Autowired
    private EstadisticaAjusteNeuronormaMapper estadisticaAjusteNeuronormaMapper;

    @Autowired
    private EstadisticaAjusteNeuronormaService estadisticaAjusteNeuronormaService;

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

    private MockMvc restEstadisticaAjusteNeuronormaMockMvc;

    private EstadisticaAjusteNeuronorma estadisticaAjusteNeuronorma;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EstadisticaAjusteNeuronormaResource estadisticaAjusteNeuronormaResource = new EstadisticaAjusteNeuronormaResource(estadisticaAjusteNeuronormaService);
        this.restEstadisticaAjusteNeuronormaMockMvc = MockMvcBuilders.standaloneSetup(estadisticaAjusteNeuronormaResource)
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
    public static EstadisticaAjusteNeuronorma createEntity(EntityManager em) {
        EstadisticaAjusteNeuronorma estadisticaAjusteNeuronorma = new EstadisticaAjusteNeuronorma()
            .scaledScore(DEFAULT_SCALED_SCORE)
            .ajusteEstudios(DEFAULT_AJUSTE_ESTUDIOS);
        // Add required entity
        Prueba prueba;
        if (TestUtil.findAll(em, Prueba.class).isEmpty()) {
            prueba = PruebaResourceIT.createEntity(em);
            em.persist(prueba);
            em.flush();
        } else {
            prueba = TestUtil.findAll(em, Prueba.class).get(0);
        }
        estadisticaAjusteNeuronorma.setPrueba(prueba);
        // Add required entity
        CodigoEstudio codigoEstudio;
        if (TestUtil.findAll(em, CodigoEstudio.class).isEmpty()) {
            codigoEstudio = CodigoEstudioResourceIT.createEntity(em);
            em.persist(codigoEstudio);
            em.flush();
        } else {
            codigoEstudio = TestUtil.findAll(em, CodigoEstudio.class).get(0);
        }
        estadisticaAjusteNeuronorma.setCodigoEstudio(codigoEstudio);
        return estadisticaAjusteNeuronorma;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EstadisticaAjusteNeuronorma createUpdatedEntity(EntityManager em) {
        EstadisticaAjusteNeuronorma estadisticaAjusteNeuronorma = new EstadisticaAjusteNeuronorma()
            .scaledScore(UPDATED_SCALED_SCORE)
            .ajusteEstudios(UPDATED_AJUSTE_ESTUDIOS);
        // Add required entity
        Prueba prueba;
        if (TestUtil.findAll(em, Prueba.class).isEmpty()) {
            prueba = PruebaResourceIT.createUpdatedEntity(em);
            em.persist(prueba);
            em.flush();
        } else {
            prueba = TestUtil.findAll(em, Prueba.class).get(0);
        }
        estadisticaAjusteNeuronorma.setPrueba(prueba);
        // Add required entity
        CodigoEstudio codigoEstudio;
        if (TestUtil.findAll(em, CodigoEstudio.class).isEmpty()) {
            codigoEstudio = CodigoEstudioResourceIT.createUpdatedEntity(em);
            em.persist(codigoEstudio);
            em.flush();
        } else {
            codigoEstudio = TestUtil.findAll(em, CodigoEstudio.class).get(0);
        }
        estadisticaAjusteNeuronorma.setCodigoEstudio(codigoEstudio);
        return estadisticaAjusteNeuronorma;
    }

    @BeforeEach
    public void initTest() {
        estadisticaAjusteNeuronorma = createEntity(em);
    }

    @Test
    @Transactional
    public void createEstadisticaAjusteNeuronorma() throws Exception {
        int databaseSizeBeforeCreate = estadisticaAjusteNeuronormaRepository.findAll().size();

        // Create the EstadisticaAjusteNeuronorma
        EstadisticaAjusteNeuronormaDTO estadisticaAjusteNeuronormaDTO = estadisticaAjusteNeuronormaMapper.toDto(estadisticaAjusteNeuronorma);
        restEstadisticaAjusteNeuronormaMockMvc.perform(post("/api/estadistica-ajuste-neuronormas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(estadisticaAjusteNeuronormaDTO)))
            .andExpect(status().isCreated());

        // Validate the EstadisticaAjusteNeuronorma in the database
        List<EstadisticaAjusteNeuronorma> estadisticaAjusteNeuronormaList = estadisticaAjusteNeuronormaRepository.findAll();
        assertThat(estadisticaAjusteNeuronormaList).hasSize(databaseSizeBeforeCreate + 1);
        EstadisticaAjusteNeuronorma testEstadisticaAjusteNeuronorma = estadisticaAjusteNeuronormaList.get(estadisticaAjusteNeuronormaList.size() - 1);
        assertThat(testEstadisticaAjusteNeuronorma.getScaledScore()).isEqualTo(DEFAULT_SCALED_SCORE);
        assertThat(testEstadisticaAjusteNeuronorma.getAjusteEstudios()).isEqualTo(DEFAULT_AJUSTE_ESTUDIOS);
    }

    @Test
    @Transactional
    public void createEstadisticaAjusteNeuronormaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = estadisticaAjusteNeuronormaRepository.findAll().size();

        // Create the EstadisticaAjusteNeuronorma with an existing ID
        estadisticaAjusteNeuronorma.setId(1L);
        EstadisticaAjusteNeuronormaDTO estadisticaAjusteNeuronormaDTO = estadisticaAjusteNeuronormaMapper.toDto(estadisticaAjusteNeuronorma);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEstadisticaAjusteNeuronormaMockMvc.perform(post("/api/estadistica-ajuste-neuronormas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(estadisticaAjusteNeuronormaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EstadisticaAjusteNeuronorma in the database
        List<EstadisticaAjusteNeuronorma> estadisticaAjusteNeuronormaList = estadisticaAjusteNeuronormaRepository.findAll();
        assertThat(estadisticaAjusteNeuronormaList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkScaledScoreIsRequired() throws Exception {
        int databaseSizeBeforeTest = estadisticaAjusteNeuronormaRepository.findAll().size();
        // set the field null
        estadisticaAjusteNeuronorma.setScaledScore(null);

        // Create the EstadisticaAjusteNeuronorma, which fails.
        EstadisticaAjusteNeuronormaDTO estadisticaAjusteNeuronormaDTO = estadisticaAjusteNeuronormaMapper.toDto(estadisticaAjusteNeuronorma);

        restEstadisticaAjusteNeuronormaMockMvc.perform(post("/api/estadistica-ajuste-neuronormas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(estadisticaAjusteNeuronormaDTO)))
            .andExpect(status().isBadRequest());

        List<EstadisticaAjusteNeuronorma> estadisticaAjusteNeuronormaList = estadisticaAjusteNeuronormaRepository.findAll();
        assertThat(estadisticaAjusteNeuronormaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAjusteEstudiosIsRequired() throws Exception {
        int databaseSizeBeforeTest = estadisticaAjusteNeuronormaRepository.findAll().size();
        // set the field null
        estadisticaAjusteNeuronorma.setAjusteEstudios(null);

        // Create the EstadisticaAjusteNeuronorma, which fails.
        EstadisticaAjusteNeuronormaDTO estadisticaAjusteNeuronormaDTO = estadisticaAjusteNeuronormaMapper.toDto(estadisticaAjusteNeuronorma);

        restEstadisticaAjusteNeuronormaMockMvc.perform(post("/api/estadistica-ajuste-neuronormas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(estadisticaAjusteNeuronormaDTO)))
            .andExpect(status().isBadRequest());

        List<EstadisticaAjusteNeuronorma> estadisticaAjusteNeuronormaList = estadisticaAjusteNeuronormaRepository.findAll();
        assertThat(estadisticaAjusteNeuronormaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllEstadisticaAjusteNeuronormas() throws Exception {
        // Initialize the database
        estadisticaAjusteNeuronormaRepository.saveAndFlush(estadisticaAjusteNeuronorma);

        // Get all the estadisticaAjusteNeuronormaList
        restEstadisticaAjusteNeuronormaMockMvc.perform(get("/api/estadistica-ajuste-neuronormas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(estadisticaAjusteNeuronorma.getId().intValue())))
            .andExpect(jsonPath("$.[*].scaledScore").value(hasItem(DEFAULT_SCALED_SCORE)))
            .andExpect(jsonPath("$.[*].ajusteEstudios").value(hasItem(DEFAULT_AJUSTE_ESTUDIOS)));
    }
    
    @Test
    @Transactional
    public void getEstadisticaAjusteNeuronorma() throws Exception {
        // Initialize the database
        estadisticaAjusteNeuronormaRepository.saveAndFlush(estadisticaAjusteNeuronorma);

        // Get the estadisticaAjusteNeuronorma
        restEstadisticaAjusteNeuronormaMockMvc.perform(get("/api/estadistica-ajuste-neuronormas/{id}", estadisticaAjusteNeuronorma.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(estadisticaAjusteNeuronorma.getId().intValue()))
            .andExpect(jsonPath("$.scaledScore").value(DEFAULT_SCALED_SCORE))
            .andExpect(jsonPath("$.ajusteEstudios").value(DEFAULT_AJUSTE_ESTUDIOS));
    }

    @Test
    @Transactional
    public void getNonExistingEstadisticaAjusteNeuronorma() throws Exception {
        // Get the estadisticaAjusteNeuronorma
        restEstadisticaAjusteNeuronormaMockMvc.perform(get("/api/estadistica-ajuste-neuronormas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEstadisticaAjusteNeuronorma() throws Exception {
        // Initialize the database
        estadisticaAjusteNeuronormaRepository.saveAndFlush(estadisticaAjusteNeuronorma);

        int databaseSizeBeforeUpdate = estadisticaAjusteNeuronormaRepository.findAll().size();

        // Update the estadisticaAjusteNeuronorma
        EstadisticaAjusteNeuronorma updatedEstadisticaAjusteNeuronorma = estadisticaAjusteNeuronormaRepository.findById(estadisticaAjusteNeuronorma.getId()).get();
        // Disconnect from session so that the updates on updatedEstadisticaAjusteNeuronorma are not directly saved in db
        em.detach(updatedEstadisticaAjusteNeuronorma);
        updatedEstadisticaAjusteNeuronorma
            .scaledScore(UPDATED_SCALED_SCORE)
            .ajusteEstudios(UPDATED_AJUSTE_ESTUDIOS);
        EstadisticaAjusteNeuronormaDTO estadisticaAjusteNeuronormaDTO = estadisticaAjusteNeuronormaMapper.toDto(updatedEstadisticaAjusteNeuronorma);

        restEstadisticaAjusteNeuronormaMockMvc.perform(put("/api/estadistica-ajuste-neuronormas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(estadisticaAjusteNeuronormaDTO)))
            .andExpect(status().isOk());

        // Validate the EstadisticaAjusteNeuronorma in the database
        List<EstadisticaAjusteNeuronorma> estadisticaAjusteNeuronormaList = estadisticaAjusteNeuronormaRepository.findAll();
        assertThat(estadisticaAjusteNeuronormaList).hasSize(databaseSizeBeforeUpdate);
        EstadisticaAjusteNeuronorma testEstadisticaAjusteNeuronorma = estadisticaAjusteNeuronormaList.get(estadisticaAjusteNeuronormaList.size() - 1);
        assertThat(testEstadisticaAjusteNeuronorma.getScaledScore()).isEqualTo(UPDATED_SCALED_SCORE);
        assertThat(testEstadisticaAjusteNeuronorma.getAjusteEstudios()).isEqualTo(UPDATED_AJUSTE_ESTUDIOS);
    }

    @Test
    @Transactional
    public void updateNonExistingEstadisticaAjusteNeuronorma() throws Exception {
        int databaseSizeBeforeUpdate = estadisticaAjusteNeuronormaRepository.findAll().size();

        // Create the EstadisticaAjusteNeuronorma
        EstadisticaAjusteNeuronormaDTO estadisticaAjusteNeuronormaDTO = estadisticaAjusteNeuronormaMapper.toDto(estadisticaAjusteNeuronorma);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEstadisticaAjusteNeuronormaMockMvc.perform(put("/api/estadistica-ajuste-neuronormas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(estadisticaAjusteNeuronormaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EstadisticaAjusteNeuronorma in the database
        List<EstadisticaAjusteNeuronorma> estadisticaAjusteNeuronormaList = estadisticaAjusteNeuronormaRepository.findAll();
        assertThat(estadisticaAjusteNeuronormaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEstadisticaAjusteNeuronorma() throws Exception {
        // Initialize the database
        estadisticaAjusteNeuronormaRepository.saveAndFlush(estadisticaAjusteNeuronorma);

        int databaseSizeBeforeDelete = estadisticaAjusteNeuronormaRepository.findAll().size();

        // Delete the estadisticaAjusteNeuronorma
        restEstadisticaAjusteNeuronormaMockMvc.perform(delete("/api/estadistica-ajuste-neuronormas/{id}", estadisticaAjusteNeuronorma.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EstadisticaAjusteNeuronorma> estadisticaAjusteNeuronormaList = estadisticaAjusteNeuronormaRepository.findAll();
        assertThat(estadisticaAjusteNeuronormaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
