package com.mycompany.myapp.web.rest;

import static com.mycompany.myapp.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import com.mycompany.myapp.Application;
import com.mycompany.myapp.domain.Informe;
import com.mycompany.myapp.domain.Paciente;
import com.mycompany.myapp.domain.User;
import com.mycompany.myapp.domain.enumeration.SospechaClinica;
import com.mycompany.myapp.domain.enumeration.SospechaClinicaSugerida;
import com.mycompany.myapp.repository.InformeRepository;
import com.mycompany.myapp.service.InformeService;
import com.mycompany.myapp.service.dto.InformeDTO;
import com.mycompany.myapp.service.mapper.InformeMapper;
import com.mycompany.myapp.web.rest.errors.ExceptionTranslator;
/**
 * Integration tests for the {@link InformeResource} REST controller.
 */
@SpringBootTest(classes = Application.class)
public class InformeResourceIT {

    private static final SospechaClinica DEFAULT_SOSPECHA_CLINICA = SospechaClinica.DEPRESION;
    private static final SospechaClinica UPDATED_SOSPECHA_CLINICA = SospechaClinica.DETERIORO_COGNITIVO_LEVE_CON_DEPRESION;

    private static final SospechaClinicaSugerida DEFAULT_SOSPECHA_CLINICA_SUGERIDA = SospechaClinicaSugerida.ALZHEIMER;
    private static final SospechaClinicaSugerida UPDATED_SOSPECHA_CLINICA_SUGERIDA = SospechaClinicaSugerida.DETERIORO_COGNITIVO_LEVE_CON_DEPRESION;

    private static final LocalDate DEFAULT_FECHA_EVALUACION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA_EVALUACION = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_MOTIVO_CONSULTA = "AAAAAAAAAA";
    private static final String UPDATED_MOTIVO_CONSULTA = "BBBBBBBBBB";

    private static final String DEFAULT_ORIENTACION = "AAAAAAAAAA";
    private static final String UPDATED_ORIENTACION = "BBBBBBBBBB";

    private static final String DEFAULT_MEMORIA = "AAAAAAAAAA";
    private static final String UPDATED_MEMORIA = "BBBBBBBBBB";

    private static final String DEFAULT_PRAXIAS = "AAAAAAAAAA";
    private static final String UPDATED_PRAXIAS = "BBBBBBBBBB";

    private static final String DEFAULT_LENGUAJE = "AAAAAAAAAA";
    private static final String UPDATED_LENGUAJE = "BBBBBBBBBB";

    private static final String DEFAULT_FUNCIONES_EJECUTIVAS = "AAAAAAAAAA";
    private static final String UPDATED_FUNCIONES_EJECUTIVAS = "BBBBBBBBBB";

    private static final String DEFAULT_CONDUCTA = "AAAAAAAAAA";
    private static final String UPDATED_CONDUCTA = "BBBBBBBBBB";

    private static final String DEFAULT_ACTIVIDADES_DIARIAS = "AAAAAAAAAA";
    private static final String UPDATED_ACTIVIDADES_DIARIAS = "BBBBBBBBBB";

    private static final String DEFAULT_RESUMEN = "AAAAAAAAAA";
    private static final String UPDATED_RESUMEN = "BBBBBBBBBB";

    @Autowired
    private InformeRepository informeRepository;

    @Autowired
    private InformeMapper informeMapper;

    @Autowired
    private InformeService informeService;

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

    private MockMvc restInformeMockMvc;

    private Informe informe;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final InformeResource informeResource = new InformeResource(informeService);
        this.restInformeMockMvc = MockMvcBuilders.standaloneSetup(informeResource)
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
    public static Informe createEntity(EntityManager em) {
        Informe informe = new Informe()
            .sospechaClinica(DEFAULT_SOSPECHA_CLINICA)
            .sospechaClinicaSugerida(DEFAULT_SOSPECHA_CLINICA_SUGERIDA)
            .fechaEvaluacion(DEFAULT_FECHA_EVALUACION)
            .motivoConsulta(DEFAULT_MOTIVO_CONSULTA)
            .orientacion(DEFAULT_ORIENTACION)
            .memoria(DEFAULT_MEMORIA)
            .praxias(DEFAULT_PRAXIAS)
            .lenguaje(DEFAULT_LENGUAJE)
            .funcionesEjecutivas(DEFAULT_FUNCIONES_EJECUTIVAS)
            .conducta(DEFAULT_CONDUCTA)
            .actividadesDiarias(DEFAULT_ACTIVIDADES_DIARIAS)
            .resumen(DEFAULT_RESUMEN);
        // Add required entity
        User user = UserResourceIT.createEntity(em);
        em.persist(user);
        em.flush();
        informe.setUser(user);
        // Add required entity
        Paciente paciente;
        if (TestUtil.findAll(em, Paciente.class).isEmpty()) {
            paciente = PacienteResourceIT.createEntity(em);
            em.persist(paciente);
            em.flush();
        } else {
            paciente = TestUtil.findAll(em, Paciente.class).get(0);
        }
        informe.setPaciente(paciente);
        return informe;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Informe createUpdatedEntity(EntityManager em) {
        Informe informe = new Informe()
            .sospechaClinica(UPDATED_SOSPECHA_CLINICA)
            .sospechaClinicaSugerida(UPDATED_SOSPECHA_CLINICA_SUGERIDA)
            .fechaEvaluacion(UPDATED_FECHA_EVALUACION)
            .motivoConsulta(UPDATED_MOTIVO_CONSULTA)
            .orientacion(UPDATED_ORIENTACION)
            .memoria(UPDATED_MEMORIA)
            .praxias(UPDATED_PRAXIAS)
            .lenguaje(UPDATED_LENGUAJE)
            .funcionesEjecutivas(UPDATED_FUNCIONES_EJECUTIVAS)
            .conducta(UPDATED_CONDUCTA)
            .actividadesDiarias(UPDATED_ACTIVIDADES_DIARIAS)
            .resumen(UPDATED_RESUMEN);
        // Add required entity
        User user = UserResourceIT.createEntity(em);
        em.persist(user);
        em.flush();
        informe.setUser(user);
        // Add required entity
        Paciente paciente;
        if (TestUtil.findAll(em, Paciente.class).isEmpty()) {
            paciente = PacienteResourceIT.createUpdatedEntity(em);
            em.persist(paciente);
            em.flush();
        } else {
            paciente = TestUtil.findAll(em, Paciente.class).get(0);
        }
        informe.setPaciente(paciente);
        return informe;
    }

    @BeforeEach
    public void initTest() {
        informe = createEntity(em);
    }

    @Test
    @Transactional
    public void createInforme() throws Exception {
        int databaseSizeBeforeCreate = informeRepository.findAll().size();

        // Create the Informe
        InformeDTO informeDTO = informeMapper.toDto(informe);
        restInformeMockMvc.perform(post("/api/informes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(informeDTO)))
            .andExpect(status().isCreated());

        // Validate the Informe in the database
        List<Informe> informeList = informeRepository.findAll();
        assertThat(informeList).hasSize(databaseSizeBeforeCreate + 1);
        Informe testInforme = informeList.get(informeList.size() - 1);
        assertThat(testInforme.getSospechaClinica()).isEqualTo(DEFAULT_SOSPECHA_CLINICA);
        assertThat(testInforme.getSospechaClinicaSugerida()).isEqualTo(DEFAULT_SOSPECHA_CLINICA_SUGERIDA);
        assertThat(testInforme.getFechaEvaluacion()).isEqualTo(DEFAULT_FECHA_EVALUACION);
        assertThat(testInforme.getMotivoConsulta()).isEqualTo(DEFAULT_MOTIVO_CONSULTA);
        assertThat(testInforme.getOrientacion()).isEqualTo(DEFAULT_ORIENTACION);
        assertThat(testInforme.getMemoria()).isEqualTo(DEFAULT_MEMORIA);
        assertThat(testInforme.getPraxias()).isEqualTo(DEFAULT_PRAXIAS);
        assertThat(testInforme.getLenguaje()).isEqualTo(DEFAULT_LENGUAJE);
        assertThat(testInforme.getFuncionesEjecutivas()).isEqualTo(DEFAULT_FUNCIONES_EJECUTIVAS);
        assertThat(testInforme.getConducta()).isEqualTo(DEFAULT_CONDUCTA);
        assertThat(testInforme.getActividadesDiarias()).isEqualTo(DEFAULT_ACTIVIDADES_DIARIAS);
        assertThat(testInforme.getResumen()).isEqualTo(DEFAULT_RESUMEN);
    }

    @Test
    @Transactional
    public void createInformeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = informeRepository.findAll().size();

        // Create the Informe with an existing ID
        informe.setId(1L);
        InformeDTO informeDTO = informeMapper.toDto(informe);

        // An entity with an existing ID cannot be created, so this API call must fail
        restInformeMockMvc.perform(post("/api/informes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(informeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Informe in the database
        List<Informe> informeList = informeRepository.findAll();
        assertThat(informeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkFechaEvaluacionIsRequired() throws Exception {
        int databaseSizeBeforeTest = informeRepository.findAll().size();
        // set the field null
        informe.setFechaEvaluacion(null);

        // Create the Informe, which fails.
        InformeDTO informeDTO = informeMapper.toDto(informe);

        restInformeMockMvc.perform(post("/api/informes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(informeDTO)))
            .andExpect(status().isBadRequest());

        List<Informe> informeList = informeRepository.findAll();
        assertThat(informeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    @WithUserDetails("admin")
    public void getAllInformes() throws Exception {
        // Initialize the database
        informeRepository.saveAndFlush(informe);

        // Get all the informeList
        restInformeMockMvc.perform(get("/api/informes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(informe.getId().intValue())))
            .andExpect(jsonPath("$.[*].sospechaClinica").value(hasItem(DEFAULT_SOSPECHA_CLINICA.toString())))
            .andExpect(jsonPath("$.[*].sospechaClinicaSugerida").value(hasItem(DEFAULT_SOSPECHA_CLINICA_SUGERIDA.toString())))
            .andExpect(jsonPath("$.[*].fechaEvaluacion").value(hasItem(DEFAULT_FECHA_EVALUACION.toString())))
            .andExpect(jsonPath("$.[*].motivoConsulta").value(hasItem(DEFAULT_MOTIVO_CONSULTA)))
            .andExpect(jsonPath("$.[*].orientacion").value(hasItem(DEFAULT_ORIENTACION)))
            .andExpect(jsonPath("$.[*].memoria").value(hasItem(DEFAULT_MEMORIA)))
            .andExpect(jsonPath("$.[*].praxias").value(hasItem(DEFAULT_PRAXIAS)))
            .andExpect(jsonPath("$.[*].lenguaje").value(hasItem(DEFAULT_LENGUAJE)))
            .andExpect(jsonPath("$.[*].funcionesEjecutivas").value(hasItem(DEFAULT_FUNCIONES_EJECUTIVAS)))
            .andExpect(jsonPath("$.[*].conducta").value(hasItem(DEFAULT_CONDUCTA)))
            .andExpect(jsonPath("$.[*].actividadesDiarias").value(hasItem(DEFAULT_ACTIVIDADES_DIARIAS)))
            .andExpect(jsonPath("$.[*].resumen").value(hasItem(DEFAULT_RESUMEN)));
    }
    
    @Test
    @Transactional
    public void getInforme() throws Exception {
        // Initialize the database
        informeRepository.saveAndFlush(informe);

        // Get the informe
        restInformeMockMvc.perform(get("/api/informes/{id}", informe.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(informe.getId().intValue()))
            .andExpect(jsonPath("$.sospechaClinica").value(DEFAULT_SOSPECHA_CLINICA.toString()))
            .andExpect(jsonPath("$.sospechaClinicaSugerida").value(DEFAULT_SOSPECHA_CLINICA_SUGERIDA.toString()))
            .andExpect(jsonPath("$.fechaEvaluacion").value(DEFAULT_FECHA_EVALUACION.toString()))
            .andExpect(jsonPath("$.motivoConsulta").value(DEFAULT_MOTIVO_CONSULTA))
            .andExpect(jsonPath("$.orientacion").value(DEFAULT_ORIENTACION))
            .andExpect(jsonPath("$.memoria").value(DEFAULT_MEMORIA))
            .andExpect(jsonPath("$.praxias").value(DEFAULT_PRAXIAS))
            .andExpect(jsonPath("$.lenguaje").value(DEFAULT_LENGUAJE))
            .andExpect(jsonPath("$.funcionesEjecutivas").value(DEFAULT_FUNCIONES_EJECUTIVAS))
            .andExpect(jsonPath("$.conducta").value(DEFAULT_CONDUCTA))
            .andExpect(jsonPath("$.actividadesDiarias").value(DEFAULT_ACTIVIDADES_DIARIAS))
            .andExpect(jsonPath("$.resumen").value(DEFAULT_RESUMEN));
    }

    @Test
    @Transactional
    public void getNonExistingInforme() throws Exception {
        // Get the informe
        restInformeMockMvc.perform(get("/api/informes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateInforme() throws Exception {
        // Initialize the database
        informeRepository.saveAndFlush(informe);

        int databaseSizeBeforeUpdate = informeRepository.findAll().size();

        // Update the informe
        Informe updatedInforme = informeRepository.findById(informe.getId()).get();
        // Disconnect from session so that the updates on updatedInforme are not directly saved in db
        em.detach(updatedInforme);
        updatedInforme
            .sospechaClinica(UPDATED_SOSPECHA_CLINICA)
            .sospechaClinicaSugerida(UPDATED_SOSPECHA_CLINICA_SUGERIDA)
            .fechaEvaluacion(UPDATED_FECHA_EVALUACION)
            .motivoConsulta(UPDATED_MOTIVO_CONSULTA)
            .orientacion(UPDATED_ORIENTACION)
            .memoria(UPDATED_MEMORIA)
            .praxias(UPDATED_PRAXIAS)
            .lenguaje(UPDATED_LENGUAJE)
            .funcionesEjecutivas(UPDATED_FUNCIONES_EJECUTIVAS)
            .conducta(UPDATED_CONDUCTA)
            .actividadesDiarias(UPDATED_ACTIVIDADES_DIARIAS)
            .resumen(UPDATED_RESUMEN);
        InformeDTO informeDTO = informeMapper.toDto(updatedInforme);

        restInformeMockMvc.perform(put("/api/informes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(informeDTO)))
            .andExpect(status().isOk());

        // Validate the Informe in the database
        List<Informe> informeList = informeRepository.findAll();
        assertThat(informeList).hasSize(databaseSizeBeforeUpdate);
        Informe testInforme = informeList.get(informeList.size() - 1);
        assertThat(testInforme.getSospechaClinica()).isEqualTo(UPDATED_SOSPECHA_CLINICA);
        assertThat(testInforme.getSospechaClinicaSugerida()).isEqualTo(UPDATED_SOSPECHA_CLINICA_SUGERIDA);
        assertThat(testInforme.getFechaEvaluacion()).isEqualTo(UPDATED_FECHA_EVALUACION);
        assertThat(testInforme.getMotivoConsulta()).isEqualTo(UPDATED_MOTIVO_CONSULTA);
        assertThat(testInforme.getOrientacion()).isEqualTo(UPDATED_ORIENTACION);
        assertThat(testInforme.getMemoria()).isEqualTo(UPDATED_MEMORIA);
        assertThat(testInforme.getPraxias()).isEqualTo(UPDATED_PRAXIAS);
        assertThat(testInforme.getLenguaje()).isEqualTo(UPDATED_LENGUAJE);
        assertThat(testInforme.getFuncionesEjecutivas()).isEqualTo(UPDATED_FUNCIONES_EJECUTIVAS);
        assertThat(testInforme.getConducta()).isEqualTo(UPDATED_CONDUCTA);
        assertThat(testInforme.getActividadesDiarias()).isEqualTo(UPDATED_ACTIVIDADES_DIARIAS);
        assertThat(testInforme.getResumen()).isEqualTo(UPDATED_RESUMEN);
    }

    @Test
    @Transactional
    public void updateNonExistingInforme() throws Exception {
        int databaseSizeBeforeUpdate = informeRepository.findAll().size();

        // Create the Informe
        InformeDTO informeDTO = informeMapper.toDto(informe);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInformeMockMvc.perform(put("/api/informes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(informeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Informe in the database
        List<Informe> informeList = informeRepository.findAll();
        assertThat(informeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteInforme() throws Exception {
        // Initialize the database
        informeRepository.saveAndFlush(informe);

        int databaseSizeBeforeDelete = informeRepository.findAll().size();

        // Delete the informe
        restInformeMockMvc.perform(delete("/api/informes/{id}", informe.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Informe> informeList = informeRepository.findAll();
        assertThat(informeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
