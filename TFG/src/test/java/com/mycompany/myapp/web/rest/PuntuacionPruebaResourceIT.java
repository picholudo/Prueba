package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.Application;
import com.mycompany.myapp.domain.PuntuacionPrueba;
import com.mycompany.myapp.domain.ZScore;
import com.mycompany.myapp.domain.Evaluacion;
import com.mycompany.myapp.repository.PuntuacionPruebaRepository;
import com.mycompany.myapp.service.PuntuacionPruebaService;
import com.mycompany.myapp.service.dto.PuntuacionPruebaDTO;
import com.mycompany.myapp.service.mapper.PuntuacionPruebaMapper;
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
 * Integration tests for the {@link PuntuacionPruebaResource} REST controller.
 */
@SpringBootTest(classes = Application.class)
public class PuntuacionPruebaResourceIT {

    private static final Long DEFAULT_VALOR = 1L;
    private static final Long UPDATED_VALOR = 2L;

    @Autowired
    private PuntuacionPruebaRepository puntuacionPruebaRepository;

    @Autowired
    private PuntuacionPruebaMapper puntuacionPruebaMapper;

    @Autowired
    private PuntuacionPruebaService puntuacionPruebaService;

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

    private MockMvc restPuntuacionPruebaMockMvc;

    private PuntuacionPrueba puntuacionPrueba;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PuntuacionPruebaResource puntuacionPruebaResource = new PuntuacionPruebaResource(puntuacionPruebaService);
        this.restPuntuacionPruebaMockMvc = MockMvcBuilders.standaloneSetup(puntuacionPruebaResource)
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
    public static PuntuacionPrueba createEntity(EntityManager em) {
        PuntuacionPrueba puntuacionPrueba = new PuntuacionPrueba()
            .valor(DEFAULT_VALOR);
        // Add required entity
        ZScore zScore;
        if (TestUtil.findAll(em, ZScore.class).isEmpty()) {
            zScore = ZScoreResourceIT.createEntity(em);
            em.persist(zScore);
            em.flush();
        } else {
            zScore = TestUtil.findAll(em, ZScore.class).get(0);
        }
        puntuacionPrueba.setZscore(zScore);
        // Add required entity
        Evaluacion evaluacion;
        if (TestUtil.findAll(em, Evaluacion.class).isEmpty()) {
            evaluacion = EvaluacionResourceIT.createEntity(em);
            em.persist(evaluacion);
            em.flush();
        } else {
            evaluacion = TestUtil.findAll(em, Evaluacion.class).get(0);
        }
        puntuacionPrueba.setPaciente(evaluacion);
        return puntuacionPrueba;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PuntuacionPrueba createUpdatedEntity(EntityManager em) {
        PuntuacionPrueba puntuacionPrueba = new PuntuacionPrueba()
            .valor(UPDATED_VALOR);
        // Add required entity
        ZScore zScore;
        if (TestUtil.findAll(em, ZScore.class).isEmpty()) {
            zScore = ZScoreResourceIT.createUpdatedEntity(em);
            em.persist(zScore);
            em.flush();
        } else {
            zScore = TestUtil.findAll(em, ZScore.class).get(0);
        }
        puntuacionPrueba.setZscore(zScore);
        // Add required entity
        Evaluacion evaluacion;
        if (TestUtil.findAll(em, Evaluacion.class).isEmpty()) {
            evaluacion = EvaluacionResourceIT.createUpdatedEntity(em);
            em.persist(evaluacion);
            em.flush();
        } else {
            evaluacion = TestUtil.findAll(em, Evaluacion.class).get(0);
        }
        puntuacionPrueba.setPaciente(evaluacion);
        return puntuacionPrueba;
    }

    @BeforeEach
    public void initTest() {
        puntuacionPrueba = createEntity(em);
    }

    @Test
    @Transactional
    public void createPuntuacionPrueba() throws Exception {
        int databaseSizeBeforeCreate = puntuacionPruebaRepository.findAll().size();

        // Create the PuntuacionPrueba
        PuntuacionPruebaDTO puntuacionPruebaDTO = puntuacionPruebaMapper.toDto(puntuacionPrueba);
        restPuntuacionPruebaMockMvc.perform(post("/api/puntuacion-pruebas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(puntuacionPruebaDTO)))
            .andExpect(status().isCreated());

        // Validate the PuntuacionPrueba in the database
        List<PuntuacionPrueba> puntuacionPruebaList = puntuacionPruebaRepository.findAll();
        assertThat(puntuacionPruebaList).hasSize(databaseSizeBeforeCreate + 1);
        PuntuacionPrueba testPuntuacionPrueba = puntuacionPruebaList.get(puntuacionPruebaList.size() - 1);
        assertThat(testPuntuacionPrueba.getValor()).isEqualTo(DEFAULT_VALOR);
    }

    @Test
    @Transactional
    public void createPuntuacionPruebaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = puntuacionPruebaRepository.findAll().size();

        // Create the PuntuacionPrueba with an existing ID
        puntuacionPrueba.setId(1L);
        PuntuacionPruebaDTO puntuacionPruebaDTO = puntuacionPruebaMapper.toDto(puntuacionPrueba);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPuntuacionPruebaMockMvc.perform(post("/api/puntuacion-pruebas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(puntuacionPruebaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PuntuacionPrueba in the database
        List<PuntuacionPrueba> puntuacionPruebaList = puntuacionPruebaRepository.findAll();
        assertThat(puntuacionPruebaList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllPuntuacionPruebas() throws Exception {
        // Initialize the database
        puntuacionPruebaRepository.saveAndFlush(puntuacionPrueba);

        // Get all the puntuacionPruebaList
        restPuntuacionPruebaMockMvc.perform(get("/api/puntuacion-pruebas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(puntuacionPrueba.getId().intValue())))
            .andExpect(jsonPath("$.[*].valor").value(hasItem(DEFAULT_VALOR.intValue())));
    }
    
    @Test
    @Transactional
    public void getPuntuacionPrueba() throws Exception {
        // Initialize the database
        puntuacionPruebaRepository.saveAndFlush(puntuacionPrueba);

        // Get the puntuacionPrueba
        restPuntuacionPruebaMockMvc.perform(get("/api/puntuacion-pruebas/{id}", puntuacionPrueba.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(puntuacionPrueba.getId().intValue()))
            .andExpect(jsonPath("$.valor").value(DEFAULT_VALOR.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingPuntuacionPrueba() throws Exception {
        // Get the puntuacionPrueba
        restPuntuacionPruebaMockMvc.perform(get("/api/puntuacion-pruebas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePuntuacionPrueba() throws Exception {
        // Initialize the database
        puntuacionPruebaRepository.saveAndFlush(puntuacionPrueba);

        int databaseSizeBeforeUpdate = puntuacionPruebaRepository.findAll().size();

        // Update the puntuacionPrueba
        PuntuacionPrueba updatedPuntuacionPrueba = puntuacionPruebaRepository.findById(puntuacionPrueba.getId()).get();
        // Disconnect from session so that the updates on updatedPuntuacionPrueba are not directly saved in db
        em.detach(updatedPuntuacionPrueba);
        updatedPuntuacionPrueba
            .valor(UPDATED_VALOR);
        PuntuacionPruebaDTO puntuacionPruebaDTO = puntuacionPruebaMapper.toDto(updatedPuntuacionPrueba);

        restPuntuacionPruebaMockMvc.perform(put("/api/puntuacion-pruebas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(puntuacionPruebaDTO)))
            .andExpect(status().isOk());

        // Validate the PuntuacionPrueba in the database
        List<PuntuacionPrueba> puntuacionPruebaList = puntuacionPruebaRepository.findAll();
        assertThat(puntuacionPruebaList).hasSize(databaseSizeBeforeUpdate);
        PuntuacionPrueba testPuntuacionPrueba = puntuacionPruebaList.get(puntuacionPruebaList.size() - 1);
        assertThat(testPuntuacionPrueba.getValor()).isEqualTo(UPDATED_VALOR);
    }

    @Test
    @Transactional
    public void updateNonExistingPuntuacionPrueba() throws Exception {
        int databaseSizeBeforeUpdate = puntuacionPruebaRepository.findAll().size();

        // Create the PuntuacionPrueba
        PuntuacionPruebaDTO puntuacionPruebaDTO = puntuacionPruebaMapper.toDto(puntuacionPrueba);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPuntuacionPruebaMockMvc.perform(put("/api/puntuacion-pruebas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(puntuacionPruebaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PuntuacionPrueba in the database
        List<PuntuacionPrueba> puntuacionPruebaList = puntuacionPruebaRepository.findAll();
        assertThat(puntuacionPruebaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePuntuacionPrueba() throws Exception {
        // Initialize the database
        puntuacionPruebaRepository.saveAndFlush(puntuacionPrueba);

        int databaseSizeBeforeDelete = puntuacionPruebaRepository.findAll().size();

        // Delete the puntuacionPrueba
        restPuntuacionPruebaMockMvc.perform(delete("/api/puntuacion-pruebas/{id}", puntuacionPrueba.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PuntuacionPrueba> puntuacionPruebaList = puntuacionPruebaRepository.findAll();
        assertThat(puntuacionPruebaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
