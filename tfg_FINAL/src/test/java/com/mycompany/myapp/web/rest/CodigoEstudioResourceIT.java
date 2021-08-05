package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.Application;
import com.mycompany.myapp.domain.CodigoEstudio;
import com.mycompany.myapp.repository.CodigoEstudioRepository;
import com.mycompany.myapp.service.CodigoEstudioService;
import com.mycompany.myapp.service.dto.CodigoEstudioDTO;
import com.mycompany.myapp.service.mapper.CodigoEstudioMapper;
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

import com.mycompany.myapp.domain.enumeration.NivelEstudios;
/**
 * Integration tests for the {@link CodigoEstudioResource} REST controller.
 */
@SpringBootTest(classes = Application.class)
public class CodigoEstudioResourceIT {

    private static final NivelEstudios DEFAULT_NIVEL_ESTUDIOS = NivelEstudios.ILETRADO;
    private static final NivelEstudios UPDATED_NIVEL_ESTUDIOS = NivelEstudios.LEER_Y_ESCRIBIR;

    private static final String DEFAULT_CODIGO = "AAAAAAAAAA";
    private static final String UPDATED_CODIGO = "BBBBBBBBBB";

    @Autowired
    private CodigoEstudioRepository codigoEstudioRepository;

    @Autowired
    private CodigoEstudioMapper codigoEstudioMapper;

    @Autowired
    private CodigoEstudioService codigoEstudioService;

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

    private MockMvc restCodigoEstudioMockMvc;

    private CodigoEstudio codigoEstudio;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CodigoEstudioResource codigoEstudioResource = new CodigoEstudioResource(codigoEstudioService);
        this.restCodigoEstudioMockMvc = MockMvcBuilders.standaloneSetup(codigoEstudioResource)
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
    public static CodigoEstudio createEntity(EntityManager em) {
        CodigoEstudio codigoEstudio = new CodigoEstudio()
            .nivelEstudios(DEFAULT_NIVEL_ESTUDIOS)
            .codigo(DEFAULT_CODIGO);
        return codigoEstudio;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CodigoEstudio createUpdatedEntity(EntityManager em) {
        CodigoEstudio codigoEstudio = new CodigoEstudio()
            .nivelEstudios(UPDATED_NIVEL_ESTUDIOS)
            .codigo(UPDATED_CODIGO);
        return codigoEstudio;
    }

    @BeforeEach
    public void initTest() {
        codigoEstudio = createEntity(em);
    }

    @Test
    @Transactional
    public void createCodigoEstudio() throws Exception {
        int databaseSizeBeforeCreate = codigoEstudioRepository.findAll().size();

        // Create the CodigoEstudio
        CodigoEstudioDTO codigoEstudioDTO = codigoEstudioMapper.toDto(codigoEstudio);
        restCodigoEstudioMockMvc.perform(post("/api/codigo-estudios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(codigoEstudioDTO)))
            .andExpect(status().isCreated());

        // Validate the CodigoEstudio in the database
        List<CodigoEstudio> codigoEstudioList = codigoEstudioRepository.findAll();
        assertThat(codigoEstudioList).hasSize(databaseSizeBeforeCreate + 1);
        CodigoEstudio testCodigoEstudio = codigoEstudioList.get(codigoEstudioList.size() - 1);
        assertThat(testCodigoEstudio.getNivelEstudios()).isEqualTo(DEFAULT_NIVEL_ESTUDIOS);
        assertThat(testCodigoEstudio.getCodigo()).isEqualTo(DEFAULT_CODIGO);
    }

    @Test
    @Transactional
    public void createCodigoEstudioWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = codigoEstudioRepository.findAll().size();

        // Create the CodigoEstudio with an existing ID
        codigoEstudio.setId(1L);
        CodigoEstudioDTO codigoEstudioDTO = codigoEstudioMapper.toDto(codigoEstudio);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCodigoEstudioMockMvc.perform(post("/api/codigo-estudios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(codigoEstudioDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CodigoEstudio in the database
        List<CodigoEstudio> codigoEstudioList = codigoEstudioRepository.findAll();
        assertThat(codigoEstudioList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNivelEstudiosIsRequired() throws Exception {
        int databaseSizeBeforeTest = codigoEstudioRepository.findAll().size();
        // set the field null
        codigoEstudio.setNivelEstudios(null);

        // Create the CodigoEstudio, which fails.
        CodigoEstudioDTO codigoEstudioDTO = codigoEstudioMapper.toDto(codigoEstudio);

        restCodigoEstudioMockMvc.perform(post("/api/codigo-estudios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(codigoEstudioDTO)))
            .andExpect(status().isBadRequest());

        List<CodigoEstudio> codigoEstudioList = codigoEstudioRepository.findAll();
        assertThat(codigoEstudioList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCodigoIsRequired() throws Exception {
        int databaseSizeBeforeTest = codigoEstudioRepository.findAll().size();
        // set the field null
        codigoEstudio.setCodigo(null);

        // Create the CodigoEstudio, which fails.
        CodigoEstudioDTO codigoEstudioDTO = codigoEstudioMapper.toDto(codigoEstudio);

        restCodigoEstudioMockMvc.perform(post("/api/codigo-estudios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(codigoEstudioDTO)))
            .andExpect(status().isBadRequest());

        List<CodigoEstudio> codigoEstudioList = codigoEstudioRepository.findAll();
        assertThat(codigoEstudioList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCodigoEstudios() throws Exception {
        // Initialize the database
        codigoEstudioRepository.saveAndFlush(codigoEstudio);

        // Get all the codigoEstudioList
        restCodigoEstudioMockMvc.perform(get("/api/codigo-estudios?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(codigoEstudio.getId().intValue())))
            .andExpect(jsonPath("$.[*].nivelEstudios").value(hasItem(DEFAULT_NIVEL_ESTUDIOS.toString())))
            .andExpect(jsonPath("$.[*].codigo").value(hasItem(DEFAULT_CODIGO)));
    }
    
    @Test
    @Transactional
    public void getCodigoEstudio() throws Exception {
        // Initialize the database
        codigoEstudioRepository.saveAndFlush(codigoEstudio);

        // Get the codigoEstudio
        restCodigoEstudioMockMvc.perform(get("/api/codigo-estudios/{id}", codigoEstudio.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(codigoEstudio.getId().intValue()))
            .andExpect(jsonPath("$.nivelEstudios").value(DEFAULT_NIVEL_ESTUDIOS.toString()))
            .andExpect(jsonPath("$.codigo").value(DEFAULT_CODIGO));
    }

    @Test
    @Transactional
    public void getNonExistingCodigoEstudio() throws Exception {
        // Get the codigoEstudio
        restCodigoEstudioMockMvc.perform(get("/api/codigo-estudios/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCodigoEstudio() throws Exception {
        // Initialize the database
        codigoEstudioRepository.saveAndFlush(codigoEstudio);

        int databaseSizeBeforeUpdate = codigoEstudioRepository.findAll().size();

        // Update the codigoEstudio
        CodigoEstudio updatedCodigoEstudio = codigoEstudioRepository.findById(codigoEstudio.getId()).get();
        // Disconnect from session so that the updates on updatedCodigoEstudio are not directly saved in db
        em.detach(updatedCodigoEstudio);
        updatedCodigoEstudio
            .nivelEstudios(UPDATED_NIVEL_ESTUDIOS)
            .codigo(UPDATED_CODIGO);
        CodigoEstudioDTO codigoEstudioDTO = codigoEstudioMapper.toDto(updatedCodigoEstudio);

        restCodigoEstudioMockMvc.perform(put("/api/codigo-estudios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(codigoEstudioDTO)))
            .andExpect(status().isOk());

        // Validate the CodigoEstudio in the database
        List<CodigoEstudio> codigoEstudioList = codigoEstudioRepository.findAll();
        assertThat(codigoEstudioList).hasSize(databaseSizeBeforeUpdate);
        CodigoEstudio testCodigoEstudio = codigoEstudioList.get(codigoEstudioList.size() - 1);
        assertThat(testCodigoEstudio.getNivelEstudios()).isEqualTo(UPDATED_NIVEL_ESTUDIOS);
        assertThat(testCodigoEstudio.getCodigo()).isEqualTo(UPDATED_CODIGO);
    }

    @Test
    @Transactional
    public void updateNonExistingCodigoEstudio() throws Exception {
        int databaseSizeBeforeUpdate = codigoEstudioRepository.findAll().size();

        // Create the CodigoEstudio
        CodigoEstudioDTO codigoEstudioDTO = codigoEstudioMapper.toDto(codigoEstudio);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCodigoEstudioMockMvc.perform(put("/api/codigo-estudios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(codigoEstudioDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CodigoEstudio in the database
        List<CodigoEstudio> codigoEstudioList = codigoEstudioRepository.findAll();
        assertThat(codigoEstudioList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCodigoEstudio() throws Exception {
        // Initialize the database
        codigoEstudioRepository.saveAndFlush(codigoEstudio);

        int databaseSizeBeforeDelete = codigoEstudioRepository.findAll().size();

        // Delete the codigoEstudio
        restCodigoEstudioMockMvc.perform(delete("/api/codigo-estudios/{id}", codigoEstudio.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CodigoEstudio> codigoEstudioList = codigoEstudioRepository.findAll();
        assertThat(codigoEstudioList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
