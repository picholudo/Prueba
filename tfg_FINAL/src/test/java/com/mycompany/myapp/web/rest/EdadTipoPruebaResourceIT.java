package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.Application;
import com.mycompany.myapp.domain.EdadTipoPrueba;
import com.mycompany.myapp.repository.EdadTipoPruebaRepository;
import com.mycompany.myapp.service.EdadTipoPruebaService;
import com.mycompany.myapp.service.dto.EdadTipoPruebaDTO;
import com.mycompany.myapp.service.mapper.EdadTipoPruebaMapper;
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

import com.mycompany.myapp.domain.enumeration.TipoPrueba;
/**
 * Integration tests for the {@link EdadTipoPruebaResource} REST controller.
 */
@SpringBootTest(classes = Application.class)
public class EdadTipoPruebaResourceIT {

    private static final String DEFAULT_CODIGO = "AAAAAAAAAA";
    private static final String UPDATED_CODIGO = "BBBBBBBBBB";

    private static final Integer DEFAULT_EDAD_MINIMA = 1;
    private static final Integer UPDATED_EDAD_MINIMA = 2;

    private static final Integer DEFAULT_EDAD_MAXIMA = 1;
    private static final Integer UPDATED_EDAD_MAXIMA = 2;

    private static final TipoPrueba DEFAULT_TIPO_PRUEBA = TipoPrueba.MMSE;
    private static final TipoPrueba UPDATED_TIPO_PRUEBA = TipoPrueba.NEURONORMA;

    @Autowired
    private EdadTipoPruebaRepository edadTipoPruebaRepository;

    @Autowired
    private EdadTipoPruebaMapper edadTipoPruebaMapper;

    @Autowired
    private EdadTipoPruebaService edadTipoPruebaService;

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

    private MockMvc restEdadTipoPruebaMockMvc;

    private EdadTipoPrueba edadTipoPrueba;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EdadTipoPruebaResource edadTipoPruebaResource = new EdadTipoPruebaResource(edadTipoPruebaService);
        this.restEdadTipoPruebaMockMvc = MockMvcBuilders.standaloneSetup(edadTipoPruebaResource)
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
    public static EdadTipoPrueba createEntity(EntityManager em) {
        EdadTipoPrueba edadTipoPrueba = new EdadTipoPrueba()
            .codigo(DEFAULT_CODIGO)
            .edadMinima(DEFAULT_EDAD_MINIMA)
            .edadMaxima(DEFAULT_EDAD_MAXIMA)
            .tipoPrueba(DEFAULT_TIPO_PRUEBA);
        return edadTipoPrueba;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EdadTipoPrueba createUpdatedEntity(EntityManager em) {
        EdadTipoPrueba edadTipoPrueba = new EdadTipoPrueba()
            .codigo(UPDATED_CODIGO)
            .edadMinima(UPDATED_EDAD_MINIMA)
            .edadMaxima(UPDATED_EDAD_MAXIMA)
            .tipoPrueba(UPDATED_TIPO_PRUEBA);
        return edadTipoPrueba;
    }

    @BeforeEach
    public void initTest() {
        edadTipoPrueba = createEntity(em);
    }

    @Test
    @Transactional
    public void createEdadTipoPrueba() throws Exception {
        int databaseSizeBeforeCreate = edadTipoPruebaRepository.findAll().size();

        // Create the EdadTipoPrueba
        EdadTipoPruebaDTO edadTipoPruebaDTO = edadTipoPruebaMapper.toDto(edadTipoPrueba);
        restEdadTipoPruebaMockMvc.perform(post("/api/edad-tipo-pruebas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(edadTipoPruebaDTO)))
            .andExpect(status().isCreated());

        // Validate the EdadTipoPrueba in the database
        List<EdadTipoPrueba> edadTipoPruebaList = edadTipoPruebaRepository.findAll();
        assertThat(edadTipoPruebaList).hasSize(databaseSizeBeforeCreate + 1);
        EdadTipoPrueba testEdadTipoPrueba = edadTipoPruebaList.get(edadTipoPruebaList.size() - 1);
        assertThat(testEdadTipoPrueba.getCodigo()).isEqualTo(DEFAULT_CODIGO);
        assertThat(testEdadTipoPrueba.getEdadMinima()).isEqualTo(DEFAULT_EDAD_MINIMA);
        assertThat(testEdadTipoPrueba.getEdadMaxima()).isEqualTo(DEFAULT_EDAD_MAXIMA);
        assertThat(testEdadTipoPrueba.getTipoPrueba()).isEqualTo(DEFAULT_TIPO_PRUEBA);
    }

    @Test
    @Transactional
    public void createEdadTipoPruebaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = edadTipoPruebaRepository.findAll().size();

        // Create the EdadTipoPrueba with an existing ID
        edadTipoPrueba.setId(1L);
        EdadTipoPruebaDTO edadTipoPruebaDTO = edadTipoPruebaMapper.toDto(edadTipoPrueba);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEdadTipoPruebaMockMvc.perform(post("/api/edad-tipo-pruebas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(edadTipoPruebaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EdadTipoPrueba in the database
        List<EdadTipoPrueba> edadTipoPruebaList = edadTipoPruebaRepository.findAll();
        assertThat(edadTipoPruebaList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodigoIsRequired() throws Exception {
        int databaseSizeBeforeTest = edadTipoPruebaRepository.findAll().size();
        // set the field null
        edadTipoPrueba.setCodigo(null);

        // Create the EdadTipoPrueba, which fails.
        EdadTipoPruebaDTO edadTipoPruebaDTO = edadTipoPruebaMapper.toDto(edadTipoPrueba);

        restEdadTipoPruebaMockMvc.perform(post("/api/edad-tipo-pruebas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(edadTipoPruebaDTO)))
            .andExpect(status().isBadRequest());

        List<EdadTipoPrueba> edadTipoPruebaList = edadTipoPruebaRepository.findAll();
        assertThat(edadTipoPruebaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEdadMinimaIsRequired() throws Exception {
        int databaseSizeBeforeTest = edadTipoPruebaRepository.findAll().size();
        // set the field null
        edadTipoPrueba.setEdadMinima(null);

        // Create the EdadTipoPrueba, which fails.
        EdadTipoPruebaDTO edadTipoPruebaDTO = edadTipoPruebaMapper.toDto(edadTipoPrueba);

        restEdadTipoPruebaMockMvc.perform(post("/api/edad-tipo-pruebas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(edadTipoPruebaDTO)))
            .andExpect(status().isBadRequest());

        List<EdadTipoPrueba> edadTipoPruebaList = edadTipoPruebaRepository.findAll();
        assertThat(edadTipoPruebaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEdadMaximaIsRequired() throws Exception {
        int databaseSizeBeforeTest = edadTipoPruebaRepository.findAll().size();
        // set the field null
        edadTipoPrueba.setEdadMaxima(null);

        // Create the EdadTipoPrueba, which fails.
        EdadTipoPruebaDTO edadTipoPruebaDTO = edadTipoPruebaMapper.toDto(edadTipoPrueba);

        restEdadTipoPruebaMockMvc.perform(post("/api/edad-tipo-pruebas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(edadTipoPruebaDTO)))
            .andExpect(status().isBadRequest());

        List<EdadTipoPrueba> edadTipoPruebaList = edadTipoPruebaRepository.findAll();
        assertThat(edadTipoPruebaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTipoPruebaIsRequired() throws Exception {
        int databaseSizeBeforeTest = edadTipoPruebaRepository.findAll().size();
        // set the field null
        edadTipoPrueba.setTipoPrueba(null);

        // Create the EdadTipoPrueba, which fails.
        EdadTipoPruebaDTO edadTipoPruebaDTO = edadTipoPruebaMapper.toDto(edadTipoPrueba);

        restEdadTipoPruebaMockMvc.perform(post("/api/edad-tipo-pruebas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(edadTipoPruebaDTO)))
            .andExpect(status().isBadRequest());

        List<EdadTipoPrueba> edadTipoPruebaList = edadTipoPruebaRepository.findAll();
        assertThat(edadTipoPruebaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllEdadTipoPruebas() throws Exception {
        // Initialize the database
        edadTipoPruebaRepository.saveAndFlush(edadTipoPrueba);

        // Get all the edadTipoPruebaList
        restEdadTipoPruebaMockMvc.perform(get("/api/edad-tipo-pruebas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(edadTipoPrueba.getId().intValue())))
            .andExpect(jsonPath("$.[*].codigo").value(hasItem(DEFAULT_CODIGO)))
            .andExpect(jsonPath("$.[*].edadMinima").value(hasItem(DEFAULT_EDAD_MINIMA)))
            .andExpect(jsonPath("$.[*].edadMaxima").value(hasItem(DEFAULT_EDAD_MAXIMA)))
            .andExpect(jsonPath("$.[*].tipoPrueba").value(hasItem(DEFAULT_TIPO_PRUEBA.toString())));
    }
    
    @Test
    @Transactional
    public void getEdadTipoPrueba() throws Exception {
        // Initialize the database
        edadTipoPruebaRepository.saveAndFlush(edadTipoPrueba);

        // Get the edadTipoPrueba
        restEdadTipoPruebaMockMvc.perform(get("/api/edad-tipo-pruebas/{id}", edadTipoPrueba.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(edadTipoPrueba.getId().intValue()))
            .andExpect(jsonPath("$.codigo").value(DEFAULT_CODIGO))
            .andExpect(jsonPath("$.edadMinima").value(DEFAULT_EDAD_MINIMA))
            .andExpect(jsonPath("$.edadMaxima").value(DEFAULT_EDAD_MAXIMA))
            .andExpect(jsonPath("$.tipoPrueba").value(DEFAULT_TIPO_PRUEBA.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingEdadTipoPrueba() throws Exception {
        // Get the edadTipoPrueba
        restEdadTipoPruebaMockMvc.perform(get("/api/edad-tipo-pruebas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEdadTipoPrueba() throws Exception {
        // Initialize the database
        edadTipoPruebaRepository.saveAndFlush(edadTipoPrueba);

        int databaseSizeBeforeUpdate = edadTipoPruebaRepository.findAll().size();

        // Update the edadTipoPrueba
        EdadTipoPrueba updatedEdadTipoPrueba = edadTipoPruebaRepository.findById(edadTipoPrueba.getId()).get();
        // Disconnect from session so that the updates on updatedEdadTipoPrueba are not directly saved in db
        em.detach(updatedEdadTipoPrueba);
        updatedEdadTipoPrueba
            .codigo(UPDATED_CODIGO)
            .edadMinima(UPDATED_EDAD_MINIMA)
            .edadMaxima(UPDATED_EDAD_MAXIMA)
            .tipoPrueba(UPDATED_TIPO_PRUEBA);
        EdadTipoPruebaDTO edadTipoPruebaDTO = edadTipoPruebaMapper.toDto(updatedEdadTipoPrueba);

        restEdadTipoPruebaMockMvc.perform(put("/api/edad-tipo-pruebas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(edadTipoPruebaDTO)))
            .andExpect(status().isOk());

        // Validate the EdadTipoPrueba in the database
        List<EdadTipoPrueba> edadTipoPruebaList = edadTipoPruebaRepository.findAll();
        assertThat(edadTipoPruebaList).hasSize(databaseSizeBeforeUpdate);
        EdadTipoPrueba testEdadTipoPrueba = edadTipoPruebaList.get(edadTipoPruebaList.size() - 1);
        assertThat(testEdadTipoPrueba.getCodigo()).isEqualTo(UPDATED_CODIGO);
        assertThat(testEdadTipoPrueba.getEdadMinima()).isEqualTo(UPDATED_EDAD_MINIMA);
        assertThat(testEdadTipoPrueba.getEdadMaxima()).isEqualTo(UPDATED_EDAD_MAXIMA);
        assertThat(testEdadTipoPrueba.getTipoPrueba()).isEqualTo(UPDATED_TIPO_PRUEBA);
    }

    @Test
    @Transactional
    public void updateNonExistingEdadTipoPrueba() throws Exception {
        int databaseSizeBeforeUpdate = edadTipoPruebaRepository.findAll().size();

        // Create the EdadTipoPrueba
        EdadTipoPruebaDTO edadTipoPruebaDTO = edadTipoPruebaMapper.toDto(edadTipoPrueba);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEdadTipoPruebaMockMvc.perform(put("/api/edad-tipo-pruebas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(edadTipoPruebaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EdadTipoPrueba in the database
        List<EdadTipoPrueba> edadTipoPruebaList = edadTipoPruebaRepository.findAll();
        assertThat(edadTipoPruebaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEdadTipoPrueba() throws Exception {
        // Initialize the database
        edadTipoPruebaRepository.saveAndFlush(edadTipoPrueba);

        int databaseSizeBeforeDelete = edadTipoPruebaRepository.findAll().size();

        // Delete the edadTipoPrueba
        restEdadTipoPruebaMockMvc.perform(delete("/api/edad-tipo-pruebas/{id}", edadTipoPrueba.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EdadTipoPrueba> edadTipoPruebaList = edadTipoPruebaRepository.findAll();
        assertThat(edadTipoPruebaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
