package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.Application;
import com.mycompany.myapp.domain.PuntosCorte;
import com.mycompany.myapp.repository.PuntosCorteRepository;
import com.mycompany.myapp.service.PuntosCorteService;
import com.mycompany.myapp.service.dto.PuntosCorteDTO;
import com.mycompany.myapp.service.mapper.PuntosCorteMapper;
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
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static com.mycompany.myapp.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link PuntosCorteResource} REST controller.
 */
@SpringBootTest(classes = Application.class)
public class PuntosCorteResourceIT {

    private static final String DEFAULT_NOMBRE = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE = "BBBBBBBBBB";

    private static final Instant DEFAULT_LIMITE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LIMITE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Boolean DEFAULT_SUPERARLO = false;
    private static final Boolean UPDATED_SUPERARLO = true;

    @Autowired
    private PuntosCorteRepository puntosCorteRepository;

    @Autowired
    private PuntosCorteMapper puntosCorteMapper;

    @Autowired
    private PuntosCorteService puntosCorteService;

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

    private MockMvc restPuntosCorteMockMvc;

    private PuntosCorte puntosCorte;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PuntosCorteResource puntosCorteResource = new PuntosCorteResource(puntosCorteService);
        this.restPuntosCorteMockMvc = MockMvcBuilders.standaloneSetup(puntosCorteResource)
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
    public static PuntosCorte createEntity(EntityManager em) {
        PuntosCorte puntosCorte = new PuntosCorte()
            .nombre(DEFAULT_NOMBRE)
            .limite(DEFAULT_LIMITE)
            .superarlo(DEFAULT_SUPERARLO);
        return puntosCorte;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PuntosCorte createUpdatedEntity(EntityManager em) {
        PuntosCorte puntosCorte = new PuntosCorte()
            .nombre(UPDATED_NOMBRE)
            .limite(UPDATED_LIMITE)
            .superarlo(UPDATED_SUPERARLO);
        return puntosCorte;
    }

    @BeforeEach
    public void initTest() {
        puntosCorte = createEntity(em);
    }

    @Test
    @Transactional
    public void createPuntosCorte() throws Exception {
        int databaseSizeBeforeCreate = puntosCorteRepository.findAll().size();

        // Create the PuntosCorte
        PuntosCorteDTO puntosCorteDTO = puntosCorteMapper.toDto(puntosCorte);
        restPuntosCorteMockMvc.perform(post("/api/puntos-cortes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(puntosCorteDTO)))
            .andExpect(status().isCreated());

        // Validate the PuntosCorte in the database
        List<PuntosCorte> puntosCorteList = puntosCorteRepository.findAll();
        assertThat(puntosCorteList).hasSize(databaseSizeBeforeCreate + 1);
        PuntosCorte testPuntosCorte = puntosCorteList.get(puntosCorteList.size() - 1);
        assertThat(testPuntosCorte.getNombre()).isEqualTo(DEFAULT_NOMBRE);
        assertThat(testPuntosCorte.getLimite()).isEqualTo(DEFAULT_LIMITE);
        assertThat(testPuntosCorte.isSuperarlo()).isEqualTo(DEFAULT_SUPERARLO);
    }

    @Test
    @Transactional
    public void createPuntosCorteWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = puntosCorteRepository.findAll().size();

        // Create the PuntosCorte with an existing ID
        puntosCorte.setId(1L);
        PuntosCorteDTO puntosCorteDTO = puntosCorteMapper.toDto(puntosCorte);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPuntosCorteMockMvc.perform(post("/api/puntos-cortes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(puntosCorteDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PuntosCorte in the database
        List<PuntosCorte> puntosCorteList = puntosCorteRepository.findAll();
        assertThat(puntosCorteList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNombreIsRequired() throws Exception {
        int databaseSizeBeforeTest = puntosCorteRepository.findAll().size();
        // set the field null
        puntosCorte.setNombre(null);

        // Create the PuntosCorte, which fails.
        PuntosCorteDTO puntosCorteDTO = puntosCorteMapper.toDto(puntosCorte);

        restPuntosCorteMockMvc.perform(post("/api/puntos-cortes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(puntosCorteDTO)))
            .andExpect(status().isBadRequest());

        List<PuntosCorte> puntosCorteList = puntosCorteRepository.findAll();
        assertThat(puntosCorteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLimiteIsRequired() throws Exception {
        int databaseSizeBeforeTest = puntosCorteRepository.findAll().size();
        // set the field null
        puntosCorte.setLimite(null);

        // Create the PuntosCorte, which fails.
        PuntosCorteDTO puntosCorteDTO = puntosCorteMapper.toDto(puntosCorte);

        restPuntosCorteMockMvc.perform(post("/api/puntos-cortes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(puntosCorteDTO)))
            .andExpect(status().isBadRequest());

        List<PuntosCorte> puntosCorteList = puntosCorteRepository.findAll();
        assertThat(puntosCorteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSuperarloIsRequired() throws Exception {
        int databaseSizeBeforeTest = puntosCorteRepository.findAll().size();
        // set the field null
        puntosCorte.setSuperarlo(null);

        // Create the PuntosCorte, which fails.
        PuntosCorteDTO puntosCorteDTO = puntosCorteMapper.toDto(puntosCorte);

        restPuntosCorteMockMvc.perform(post("/api/puntos-cortes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(puntosCorteDTO)))
            .andExpect(status().isBadRequest());

        List<PuntosCorte> puntosCorteList = puntosCorteRepository.findAll();
        assertThat(puntosCorteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPuntosCortes() throws Exception {
        // Initialize the database
        puntosCorteRepository.saveAndFlush(puntosCorte);

        // Get all the puntosCorteList
        restPuntosCorteMockMvc.perform(get("/api/puntos-cortes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(puntosCorte.getId().intValue())))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE)))
            .andExpect(jsonPath("$.[*].limite").value(hasItem(DEFAULT_LIMITE.toString())))
            .andExpect(jsonPath("$.[*].superarlo").value(hasItem(DEFAULT_SUPERARLO.booleanValue())));
    }

    @Test
    @Transactional
    public void getPuntosCorte() throws Exception {
        // Initialize the database
        puntosCorteRepository.saveAndFlush(puntosCorte);

        // Get the puntosCorte
        restPuntosCorteMockMvc.perform(get("/api/puntos-cortes/{id}", puntosCorte.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(puntosCorte.getId().intValue()))
            .andExpect(jsonPath("$.nombre").value(DEFAULT_NOMBRE))
            .andExpect(jsonPath("$.limite").value(DEFAULT_LIMITE.toString()))
            .andExpect(jsonPath("$.superarlo").value(DEFAULT_SUPERARLO.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingPuntosCorte() throws Exception {
        // Get the puntosCorte
        restPuntosCorteMockMvc.perform(get("/api/puntos-cortes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePuntosCorte() throws Exception {
        // Initialize the database
        puntosCorteRepository.saveAndFlush(puntosCorte);

        int databaseSizeBeforeUpdate = puntosCorteRepository.findAll().size();

        // Update the puntosCorte
        PuntosCorte updatedPuntosCorte = puntosCorteRepository.findById(puntosCorte.getId()).get();
        // Disconnect from session so that the updates on updatedPuntosCorte are not directly saved in db
        em.detach(updatedPuntosCorte);
        updatedPuntosCorte
            .nombre(UPDATED_NOMBRE)
            .limite(UPDATED_LIMITE)
            .superarlo(UPDATED_SUPERARLO);
        PuntosCorteDTO puntosCorteDTO = puntosCorteMapper.toDto(updatedPuntosCorte);

        restPuntosCorteMockMvc.perform(put("/api/puntos-cortes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(puntosCorteDTO)))
            .andExpect(status().isOk());

        // Validate the PuntosCorte in the database
        List<PuntosCorte> puntosCorteList = puntosCorteRepository.findAll();
        assertThat(puntosCorteList).hasSize(databaseSizeBeforeUpdate);
        PuntosCorte testPuntosCorte = puntosCorteList.get(puntosCorteList.size() - 1);
        assertThat(testPuntosCorte.getNombre()).isEqualTo(UPDATED_NOMBRE);
        assertThat(testPuntosCorte.getLimite()).isEqualTo(UPDATED_LIMITE);
        assertThat(testPuntosCorte.isSuperarlo()).isEqualTo(UPDATED_SUPERARLO);
    }

    @Test
    @Transactional
    public void updateNonExistingPuntosCorte() throws Exception {
        int databaseSizeBeforeUpdate = puntosCorteRepository.findAll().size();

        // Create the PuntosCorte
        PuntosCorteDTO puntosCorteDTO = puntosCorteMapper.toDto(puntosCorte);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPuntosCorteMockMvc.perform(put("/api/puntos-cortes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(puntosCorteDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PuntosCorte in the database
        List<PuntosCorte> puntosCorteList = puntosCorteRepository.findAll();
        assertThat(puntosCorteList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePuntosCorte() throws Exception {
        // Initialize the database
        puntosCorteRepository.saveAndFlush(puntosCorte);

        int databaseSizeBeforeDelete = puntosCorteRepository.findAll().size();

        // Delete the puntosCorte
        restPuntosCorteMockMvc.perform(delete("/api/puntos-cortes/{id}", puntosCorte.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PuntosCorte> puntosCorteList = puntosCorteRepository.findAll();
        assertThat(puntosCorteList).hasSize(databaseSizeBeforeDelete - 1);
    }

}
