package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.Application;
import com.mycompany.myapp.domain.ZScore;
import com.mycompany.myapp.repository.ZScoreRepository;
import com.mycompany.myapp.service.ZScoreService;
import com.mycompany.myapp.service.dto.ZScoreDTO;
import com.mycompany.myapp.service.mapper.ZScoreMapper;
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
 * Integration tests for the {@link ZScoreResource} REST controller.
 */
@SpringBootTest(classes = Application.class)
public class ZScoreResourceIT {

    private static final String DEFAULT_NOMBRE = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE = "BBBBBBBBBB";

    @Autowired
    private ZScoreRepository zScoreRepository;

    @Autowired
    private ZScoreMapper zScoreMapper;

    @Autowired
    private ZScoreService zScoreService;

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

    private MockMvc restZScoreMockMvc;

    private ZScore zScore;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ZScoreResource zScoreResource = new ZScoreResource(zScoreService);
        this.restZScoreMockMvc = MockMvcBuilders.standaloneSetup(zScoreResource)
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
    public static ZScore createEntity(EntityManager em) {
        ZScore zScore = new ZScore()
            .nombre(DEFAULT_NOMBRE);
        return zScore;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ZScore createUpdatedEntity(EntityManager em) {
        ZScore zScore = new ZScore()
            .nombre(UPDATED_NOMBRE);
        return zScore;
    }

    @BeforeEach
    public void initTest() {
        zScore = createEntity(em);
    }

    @Test
    @Transactional
    public void createZScore() throws Exception {
        int databaseSizeBeforeCreate = zScoreRepository.findAll().size();

        // Create the ZScore
        ZScoreDTO zScoreDTO = zScoreMapper.toDto(zScore);
        restZScoreMockMvc.perform(post("/api/z-scores")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(zScoreDTO)))
            .andExpect(status().isCreated());

        // Validate the ZScore in the database
        List<ZScore> zScoreList = zScoreRepository.findAll();
        assertThat(zScoreList).hasSize(databaseSizeBeforeCreate + 1);
        ZScore testZScore = zScoreList.get(zScoreList.size() - 1);
        assertThat(testZScore.getNombre()).isEqualTo(DEFAULT_NOMBRE);
    }

    @Test
    @Transactional
    public void createZScoreWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = zScoreRepository.findAll().size();

        // Create the ZScore with an existing ID
        zScore.setId(1L);
        ZScoreDTO zScoreDTO = zScoreMapper.toDto(zScore);

        // An entity with an existing ID cannot be created, so this API call must fail
        restZScoreMockMvc.perform(post("/api/z-scores")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(zScoreDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ZScore in the database
        List<ZScore> zScoreList = zScoreRepository.findAll();
        assertThat(zScoreList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNombreIsRequired() throws Exception {
        int databaseSizeBeforeTest = zScoreRepository.findAll().size();
        // set the field null
        zScore.setNombre(null);

        // Create the ZScore, which fails.
        ZScoreDTO zScoreDTO = zScoreMapper.toDto(zScore);

        restZScoreMockMvc.perform(post("/api/z-scores")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(zScoreDTO)))
            .andExpect(status().isBadRequest());

        List<ZScore> zScoreList = zScoreRepository.findAll();
        assertThat(zScoreList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllZScores() throws Exception {
        // Initialize the database
        zScoreRepository.saveAndFlush(zScore);

        // Get all the zScoreList
        restZScoreMockMvc.perform(get("/api/z-scores?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(zScore.getId().intValue())))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE)));
    }
    
    @Test
    @Transactional
    public void getZScore() throws Exception {
        // Initialize the database
        zScoreRepository.saveAndFlush(zScore);

        // Get the zScore
        restZScoreMockMvc.perform(get("/api/z-scores/{id}", zScore.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(zScore.getId().intValue()))
            .andExpect(jsonPath("$.nombre").value(DEFAULT_NOMBRE));
    }

    @Test
    @Transactional
    public void getNonExistingZScore() throws Exception {
        // Get the zScore
        restZScoreMockMvc.perform(get("/api/z-scores/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateZScore() throws Exception {
        // Initialize the database
        zScoreRepository.saveAndFlush(zScore);

        int databaseSizeBeforeUpdate = zScoreRepository.findAll().size();

        // Update the zScore
        ZScore updatedZScore = zScoreRepository.findById(zScore.getId()).get();
        // Disconnect from session so that the updates on updatedZScore are not directly saved in db
        em.detach(updatedZScore);
        updatedZScore
            .nombre(UPDATED_NOMBRE);
        ZScoreDTO zScoreDTO = zScoreMapper.toDto(updatedZScore);

        restZScoreMockMvc.perform(put("/api/z-scores")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(zScoreDTO)))
            .andExpect(status().isOk());

        // Validate the ZScore in the database
        List<ZScore> zScoreList = zScoreRepository.findAll();
        assertThat(zScoreList).hasSize(databaseSizeBeforeUpdate);
        ZScore testZScore = zScoreList.get(zScoreList.size() - 1);
        assertThat(testZScore.getNombre()).isEqualTo(UPDATED_NOMBRE);
    }

    @Test
    @Transactional
    public void updateNonExistingZScore() throws Exception {
        int databaseSizeBeforeUpdate = zScoreRepository.findAll().size();

        // Create the ZScore
        ZScoreDTO zScoreDTO = zScoreMapper.toDto(zScore);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restZScoreMockMvc.perform(put("/api/z-scores")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(zScoreDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ZScore in the database
        List<ZScore> zScoreList = zScoreRepository.findAll();
        assertThat(zScoreList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteZScore() throws Exception {
        // Initialize the database
        zScoreRepository.saveAndFlush(zScore);

        int databaseSizeBeforeDelete = zScoreRepository.findAll().size();

        // Delete the zScore
        restZScoreMockMvc.perform(delete("/api/z-scores/{id}", zScore.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ZScore> zScoreList = zScoreRepository.findAll();
        assertThat(zScoreList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
