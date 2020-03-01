package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.Application;
import com.mycompany.myapp.domain.Prueba;
import com.mycompany.myapp.repository.PruebaRepository;
import com.mycompany.myapp.service.PruebaService;
import com.mycompany.myapp.service.dto.PruebaDTO;
import com.mycompany.myapp.service.mapper.PruebaMapper;
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
 * Integration tests for the {@link PruebaResource} REST controller.
 */
@SpringBootTest(classes = Application.class)
public class PruebaResourceIT {

    private static final String DEFAULT_NOMBRE = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE = "BBBBBBBBBB";

    @Autowired
    private PruebaRepository pruebaRepository;

    @Autowired
    private PruebaMapper pruebaMapper;

    @Autowired
    private PruebaService pruebaService;

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

    private MockMvc restPruebaMockMvc;

    private Prueba prueba;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PruebaResource pruebaResource = new PruebaResource(pruebaService);
        this.restPruebaMockMvc = MockMvcBuilders.standaloneSetup(pruebaResource)
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
    public static Prueba createEntity(EntityManager em) {
        Prueba prueba = new Prueba()
            .nombre(DEFAULT_NOMBRE);
        return prueba;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Prueba createUpdatedEntity(EntityManager em) {
        Prueba prueba = new Prueba()
            .nombre(UPDATED_NOMBRE);
        return prueba;
    }

    @BeforeEach
    public void initTest() {
        prueba = createEntity(em);
    }

    @Test
    @Transactional
    public void createPrueba() throws Exception {
        int databaseSizeBeforeCreate = pruebaRepository.findAll().size();

        // Create the Prueba
        PruebaDTO pruebaDTO = pruebaMapper.toDto(prueba);
        restPruebaMockMvc.perform(post("/api/pruebas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pruebaDTO)))
            .andExpect(status().isCreated());

        // Validate the Prueba in the database
        List<Prueba> pruebaList = pruebaRepository.findAll();
        assertThat(pruebaList).hasSize(databaseSizeBeforeCreate + 1);
        Prueba testPrueba = pruebaList.get(pruebaList.size() - 1);
        assertThat(testPrueba.getNombre()).isEqualTo(DEFAULT_NOMBRE);
    }

    @Test
    @Transactional
    public void createPruebaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = pruebaRepository.findAll().size();

        // Create the Prueba with an existing ID
        prueba.setId(1L);
        PruebaDTO pruebaDTO = pruebaMapper.toDto(prueba);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPruebaMockMvc.perform(post("/api/pruebas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pruebaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Prueba in the database
        List<Prueba> pruebaList = pruebaRepository.findAll();
        assertThat(pruebaList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNombreIsRequired() throws Exception {
        int databaseSizeBeforeTest = pruebaRepository.findAll().size();
        // set the field null
        prueba.setNombre(null);

        // Create the Prueba, which fails.
        PruebaDTO pruebaDTO = pruebaMapper.toDto(prueba);

        restPruebaMockMvc.perform(post("/api/pruebas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pruebaDTO)))
            .andExpect(status().isBadRequest());

        List<Prueba> pruebaList = pruebaRepository.findAll();
        assertThat(pruebaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPruebas() throws Exception {
        // Initialize the database
        pruebaRepository.saveAndFlush(prueba);

        // Get all the pruebaList
        restPruebaMockMvc.perform(get("/api/pruebas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(prueba.getId().intValue())))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE)));
    }
    
    @Test
    @Transactional
    public void getPrueba() throws Exception {
        // Initialize the database
        pruebaRepository.saveAndFlush(prueba);

        // Get the prueba
        restPruebaMockMvc.perform(get("/api/pruebas/{id}", prueba.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(prueba.getId().intValue()))
            .andExpect(jsonPath("$.nombre").value(DEFAULT_NOMBRE));
    }

    @Test
    @Transactional
    public void getNonExistingPrueba() throws Exception {
        // Get the prueba
        restPruebaMockMvc.perform(get("/api/pruebas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePrueba() throws Exception {
        // Initialize the database
        pruebaRepository.saveAndFlush(prueba);

        int databaseSizeBeforeUpdate = pruebaRepository.findAll().size();

        // Update the prueba
        Prueba updatedPrueba = pruebaRepository.findById(prueba.getId()).get();
        // Disconnect from session so that the updates on updatedPrueba are not directly saved in db
        em.detach(updatedPrueba);
        updatedPrueba
            .nombre(UPDATED_NOMBRE);
        PruebaDTO pruebaDTO = pruebaMapper.toDto(updatedPrueba);

        restPruebaMockMvc.perform(put("/api/pruebas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pruebaDTO)))
            .andExpect(status().isOk());

        // Validate the Prueba in the database
        List<Prueba> pruebaList = pruebaRepository.findAll();
        assertThat(pruebaList).hasSize(databaseSizeBeforeUpdate);
        Prueba testPrueba = pruebaList.get(pruebaList.size() - 1);
        assertThat(testPrueba.getNombre()).isEqualTo(UPDATED_NOMBRE);
    }

    @Test
    @Transactional
    public void updateNonExistingPrueba() throws Exception {
        int databaseSizeBeforeUpdate = pruebaRepository.findAll().size();

        // Create the Prueba
        PruebaDTO pruebaDTO = pruebaMapper.toDto(prueba);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPruebaMockMvc.perform(put("/api/pruebas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pruebaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Prueba in the database
        List<Prueba> pruebaList = pruebaRepository.findAll();
        assertThat(pruebaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePrueba() throws Exception {
        // Initialize the database
        pruebaRepository.saveAndFlush(prueba);

        int databaseSizeBeforeDelete = pruebaRepository.findAll().size();

        // Delete the prueba
        restPruebaMockMvc.perform(delete("/api/pruebas/{id}", prueba.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Prueba> pruebaList = pruebaRepository.findAll();
        assertThat(pruebaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
