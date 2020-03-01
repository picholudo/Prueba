package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.Application;
import com.mycompany.myapp.domain.Evaluacion;
import com.mycompany.myapp.domain.Paciente;
import com.mycompany.myapp.repository.EvaluacionRepository;
import com.mycompany.myapp.service.EvaluacionService;
import com.mycompany.myapp.service.dto.EvaluacionDTO;
import com.mycompany.myapp.service.mapper.EvaluacionMapper;
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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static com.mycompany.myapp.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.domain.enumeration.Sospecha;
/**
 * Integration tests for the {@link EvaluacionResource} REST controller.
 */
@SpringBootTest(classes = Application.class)
public class EvaluacionResourceIT {

    private static final LocalDate DEFAULT_FECHA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA = LocalDate.now(ZoneId.systemDefault());

    private static final Sospecha DEFAULT_VALORACION = Sospecha.Depresion;
    private static final Sospecha UPDATED_VALORACION = Sospecha.Deterioro_cognitivo_leve_con_depresion;

    @Autowired
    private EvaluacionRepository evaluacionRepository;

    @Autowired
    private EvaluacionMapper evaluacionMapper;

    @Autowired
    private EvaluacionService evaluacionService;

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

    private MockMvc restEvaluacionMockMvc;

    private Evaluacion evaluacion;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EvaluacionResource evaluacionResource = new EvaluacionResource(evaluacionService);
        this.restEvaluacionMockMvc = MockMvcBuilders.standaloneSetup(evaluacionResource)
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
    public static Evaluacion createEntity(EntityManager em) {
        Evaluacion evaluacion = new Evaluacion()
            .fecha(DEFAULT_FECHA)
            .valoracion(DEFAULT_VALORACION);
        // Add required entity
        Paciente paciente;
        if (TestUtil.findAll(em, Paciente.class).isEmpty()) {
            paciente = PacienteResourceIT.createEntity(em);
            em.persist(paciente);
            em.flush();
        } else {
            paciente = TestUtil.findAll(em, Paciente.class).get(0);
        }
        evaluacion.setPaciente(paciente);
        return evaluacion;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Evaluacion createUpdatedEntity(EntityManager em) {
        Evaluacion evaluacion = new Evaluacion()
            .fecha(UPDATED_FECHA)
            .valoracion(UPDATED_VALORACION);
        // Add required entity
        Paciente paciente;
        if (TestUtil.findAll(em, Paciente.class).isEmpty()) {
            paciente = PacienteResourceIT.createUpdatedEntity(em);
            em.persist(paciente);
            em.flush();
        } else {
            paciente = TestUtil.findAll(em, Paciente.class).get(0);
        }
        evaluacion.setPaciente(paciente);
        return evaluacion;
    }

    @BeforeEach
    public void initTest() {
        evaluacion = createEntity(em);
    }

    @Test
    @Transactional
    public void createEvaluacion() throws Exception {
        int databaseSizeBeforeCreate = evaluacionRepository.findAll().size();

        // Create the Evaluacion
        EvaluacionDTO evaluacionDTO = evaluacionMapper.toDto(evaluacion);
        restEvaluacionMockMvc.perform(post("/api/evaluacions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(evaluacionDTO)))
            .andExpect(status().isCreated());

        // Validate the Evaluacion in the database
        List<Evaluacion> evaluacionList = evaluacionRepository.findAll();
        assertThat(evaluacionList).hasSize(databaseSizeBeforeCreate + 1);
        Evaluacion testEvaluacion = evaluacionList.get(evaluacionList.size() - 1);
        assertThat(testEvaluacion.getFecha()).isEqualTo(DEFAULT_FECHA);
        assertThat(testEvaluacion.getValoracion()).isEqualTo(DEFAULT_VALORACION);
    }

    @Test
    @Transactional
    public void createEvaluacionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = evaluacionRepository.findAll().size();

        // Create the Evaluacion with an existing ID
        evaluacion.setId(1L);
        EvaluacionDTO evaluacionDTO = evaluacionMapper.toDto(evaluacion);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEvaluacionMockMvc.perform(post("/api/evaluacions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(evaluacionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Evaluacion in the database
        List<Evaluacion> evaluacionList = evaluacionRepository.findAll();
        assertThat(evaluacionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkFechaIsRequired() throws Exception {
        int databaseSizeBeforeTest = evaluacionRepository.findAll().size();
        // set the field null
        evaluacion.setFecha(null);

        // Create the Evaluacion, which fails.
        EvaluacionDTO evaluacionDTO = evaluacionMapper.toDto(evaluacion);

        restEvaluacionMockMvc.perform(post("/api/evaluacions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(evaluacionDTO)))
            .andExpect(status().isBadRequest());

        List<Evaluacion> evaluacionList = evaluacionRepository.findAll();
        assertThat(evaluacionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkValoracionIsRequired() throws Exception {
        int databaseSizeBeforeTest = evaluacionRepository.findAll().size();
        // set the field null
        evaluacion.setValoracion(null);

        // Create the Evaluacion, which fails.
        EvaluacionDTO evaluacionDTO = evaluacionMapper.toDto(evaluacion);

        restEvaluacionMockMvc.perform(post("/api/evaluacions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(evaluacionDTO)))
            .andExpect(status().isBadRequest());

        List<Evaluacion> evaluacionList = evaluacionRepository.findAll();
        assertThat(evaluacionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllEvaluacions() throws Exception {
        // Initialize the database
        evaluacionRepository.saveAndFlush(evaluacion);

        // Get all the evaluacionList
        restEvaluacionMockMvc.perform(get("/api/evaluacions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(evaluacion.getId().intValue())))
            .andExpect(jsonPath("$.[*].fecha").value(hasItem(DEFAULT_FECHA.toString())))
            .andExpect(jsonPath("$.[*].valoracion").value(hasItem(DEFAULT_VALORACION.toString())));
    }
    
    @Test
    @Transactional
    public void getEvaluacion() throws Exception {
        // Initialize the database
        evaluacionRepository.saveAndFlush(evaluacion);

        // Get the evaluacion
        restEvaluacionMockMvc.perform(get("/api/evaluacions/{id}", evaluacion.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(evaluacion.getId().intValue()))
            .andExpect(jsonPath("$.fecha").value(DEFAULT_FECHA.toString()))
            .andExpect(jsonPath("$.valoracion").value(DEFAULT_VALORACION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingEvaluacion() throws Exception {
        // Get the evaluacion
        restEvaluacionMockMvc.perform(get("/api/evaluacions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEvaluacion() throws Exception {
        // Initialize the database
        evaluacionRepository.saveAndFlush(evaluacion);

        int databaseSizeBeforeUpdate = evaluacionRepository.findAll().size();

        // Update the evaluacion
        Evaluacion updatedEvaluacion = evaluacionRepository.findById(evaluacion.getId()).get();
        // Disconnect from session so that the updates on updatedEvaluacion are not directly saved in db
        em.detach(updatedEvaluacion);
        updatedEvaluacion
            .fecha(UPDATED_FECHA)
            .valoracion(UPDATED_VALORACION);
        EvaluacionDTO evaluacionDTO = evaluacionMapper.toDto(updatedEvaluacion);

        restEvaluacionMockMvc.perform(put("/api/evaluacions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(evaluacionDTO)))
            .andExpect(status().isOk());

        // Validate the Evaluacion in the database
        List<Evaluacion> evaluacionList = evaluacionRepository.findAll();
        assertThat(evaluacionList).hasSize(databaseSizeBeforeUpdate);
        Evaluacion testEvaluacion = evaluacionList.get(evaluacionList.size() - 1);
        assertThat(testEvaluacion.getFecha()).isEqualTo(UPDATED_FECHA);
        assertThat(testEvaluacion.getValoracion()).isEqualTo(UPDATED_VALORACION);
    }

    @Test
    @Transactional
    public void updateNonExistingEvaluacion() throws Exception {
        int databaseSizeBeforeUpdate = evaluacionRepository.findAll().size();

        // Create the Evaluacion
        EvaluacionDTO evaluacionDTO = evaluacionMapper.toDto(evaluacion);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEvaluacionMockMvc.perform(put("/api/evaluacions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(evaluacionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Evaluacion in the database
        List<Evaluacion> evaluacionList = evaluacionRepository.findAll();
        assertThat(evaluacionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEvaluacion() throws Exception {
        // Initialize the database
        evaluacionRepository.saveAndFlush(evaluacion);

        int databaseSizeBeforeDelete = evaluacionRepository.findAll().size();

        // Delete the evaluacion
        restEvaluacionMockMvc.perform(delete("/api/evaluacions/{id}", evaluacion.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Evaluacion> evaluacionList = evaluacionRepository.findAll();
        assertThat(evaluacionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
