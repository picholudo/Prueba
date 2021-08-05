package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.Application;
import com.mycompany.myapp.domain.ResultadoPrueba;
import com.mycompany.myapp.domain.Prueba;
import com.mycompany.myapp.repository.ResultadoPruebaRepository;
import com.mycompany.myapp.service.ResultadoPruebaService;
import com.mycompany.myapp.service.dto.ResultadoPruebaDTO;
import com.mycompany.myapp.service.mapper.ResultadoPruebaMapper;
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
 * Integration tests for the {@link ResultadoPruebaResource} REST controller.
 */
@SpringBootTest(classes = Application.class)
public class ResultadoPruebaResourceIT {

    private static final Integer DEFAULT_PD = 0;
    private static final Integer UPDATED_PD = 1;

    private static final Float DEFAULT_PZ = 1F;
    private static final Float UPDATED_PZ = 2F;

    @Autowired
    private ResultadoPruebaRepository resultadoPruebaRepository;

    @Autowired
    private ResultadoPruebaMapper resultadoPruebaMapper;

    @Autowired
    private ResultadoPruebaService resultadoPruebaService;

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

    private MockMvc restResultadoPruebaMockMvc;

    private ResultadoPrueba resultadoPrueba;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ResultadoPruebaResource resultadoPruebaResource = new ResultadoPruebaResource(resultadoPruebaService);
        this.restResultadoPruebaMockMvc = MockMvcBuilders.standaloneSetup(resultadoPruebaResource)
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
    public static ResultadoPrueba createEntity(EntityManager em) {
        ResultadoPrueba resultadoPrueba = new ResultadoPrueba()
            .pd(DEFAULT_PD)
            .pz(DEFAULT_PZ);
        // Add required entity
        Prueba prueba;
        if (TestUtil.findAll(em, Prueba.class).isEmpty()) {
            prueba = PruebaResourceIT.createEntity(em);
            em.persist(prueba);
            em.flush();
        } else {
            prueba = TestUtil.findAll(em, Prueba.class).get(0);
        }
        resultadoPrueba.setPrueba(prueba);
        return resultadoPrueba;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ResultadoPrueba createUpdatedEntity(EntityManager em) {
        ResultadoPrueba resultadoPrueba = new ResultadoPrueba()
            .pd(UPDATED_PD)
            .pz(UPDATED_PZ);
        // Add required entity
        Prueba prueba;
        if (TestUtil.findAll(em, Prueba.class).isEmpty()) {
            prueba = PruebaResourceIT.createUpdatedEntity(em);
            em.persist(prueba);
            em.flush();
        } else {
            prueba = TestUtil.findAll(em, Prueba.class).get(0);
        }
        resultadoPrueba.setPrueba(prueba);
        return resultadoPrueba;
    }

    @BeforeEach
    public void initTest() {
        resultadoPrueba = createEntity(em);
    }

    @Test
    @Transactional
    public void createResultadoPrueba() throws Exception {
        int databaseSizeBeforeCreate = resultadoPruebaRepository.findAll().size();

        // Create the ResultadoPrueba
        ResultadoPruebaDTO resultadoPruebaDTO = resultadoPruebaMapper.toDto(resultadoPrueba);
        restResultadoPruebaMockMvc.perform(post("/api/resultado-pruebas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(resultadoPruebaDTO)))
            .andExpect(status().isCreated());

        // Validate the ResultadoPrueba in the database
        List<ResultadoPrueba> resultadoPruebaList = resultadoPruebaRepository.findAll();
        assertThat(resultadoPruebaList).hasSize(databaseSizeBeforeCreate + 1);
        ResultadoPrueba testResultadoPrueba = resultadoPruebaList.get(resultadoPruebaList.size() - 1);
        assertThat(testResultadoPrueba.getPd()).isEqualTo(DEFAULT_PD);
        assertThat(testResultadoPrueba.getPz()).isEqualTo(DEFAULT_PZ);
    }

    @Test
    @Transactional
    public void createResultadoPruebaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = resultadoPruebaRepository.findAll().size();

        // Create the ResultadoPrueba with an existing ID
        resultadoPrueba.setId(1L);
        ResultadoPruebaDTO resultadoPruebaDTO = resultadoPruebaMapper.toDto(resultadoPrueba);

        // An entity with an existing ID cannot be created, so this API call must fail
        restResultadoPruebaMockMvc.perform(post("/api/resultado-pruebas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(resultadoPruebaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ResultadoPrueba in the database
        List<ResultadoPrueba> resultadoPruebaList = resultadoPruebaRepository.findAll();
        assertThat(resultadoPruebaList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllResultadoPruebas() throws Exception {
        // Initialize the database
        resultadoPruebaRepository.saveAndFlush(resultadoPrueba);

        // Get all the resultadoPruebaList
        restResultadoPruebaMockMvc.perform(get("/api/resultado-pruebas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(resultadoPrueba.getId().intValue())))
            .andExpect(jsonPath("$.[*].pd").value(hasItem(DEFAULT_PD)))
            .andExpect(jsonPath("$.[*].pz").value(hasItem(DEFAULT_PZ.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getResultadoPrueba() throws Exception {
        // Initialize the database
        resultadoPruebaRepository.saveAndFlush(resultadoPrueba);

        // Get the resultadoPrueba
        restResultadoPruebaMockMvc.perform(get("/api/resultado-pruebas/{id}", resultadoPrueba.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(resultadoPrueba.getId().intValue()))
            .andExpect(jsonPath("$.pd").value(DEFAULT_PD))
            .andExpect(jsonPath("$.pz").value(DEFAULT_PZ.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingResultadoPrueba() throws Exception {
        // Get the resultadoPrueba
        restResultadoPruebaMockMvc.perform(get("/api/resultado-pruebas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateResultadoPrueba() throws Exception {
        // Initialize the database
        resultadoPruebaRepository.saveAndFlush(resultadoPrueba);

        int databaseSizeBeforeUpdate = resultadoPruebaRepository.findAll().size();

        // Update the resultadoPrueba
        ResultadoPrueba updatedResultadoPrueba = resultadoPruebaRepository.findById(resultadoPrueba.getId()).get();
        // Disconnect from session so that the updates on updatedResultadoPrueba are not directly saved in db
        em.detach(updatedResultadoPrueba);
        updatedResultadoPrueba
            .pd(UPDATED_PD)
            .pz(UPDATED_PZ);
        ResultadoPruebaDTO resultadoPruebaDTO = resultadoPruebaMapper.toDto(updatedResultadoPrueba);

        restResultadoPruebaMockMvc.perform(put("/api/resultado-pruebas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(resultadoPruebaDTO)))
            .andExpect(status().isOk());

        // Validate the ResultadoPrueba in the database
        List<ResultadoPrueba> resultadoPruebaList = resultadoPruebaRepository.findAll();
        assertThat(resultadoPruebaList).hasSize(databaseSizeBeforeUpdate);
        ResultadoPrueba testResultadoPrueba = resultadoPruebaList.get(resultadoPruebaList.size() - 1);
        assertThat(testResultadoPrueba.getPd()).isEqualTo(UPDATED_PD);
        assertThat(testResultadoPrueba.getPz()).isEqualTo(UPDATED_PZ);
    }

    @Test
    @Transactional
    public void updateNonExistingResultadoPrueba() throws Exception {
        int databaseSizeBeforeUpdate = resultadoPruebaRepository.findAll().size();

        // Create the ResultadoPrueba
        ResultadoPruebaDTO resultadoPruebaDTO = resultadoPruebaMapper.toDto(resultadoPrueba);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restResultadoPruebaMockMvc.perform(put("/api/resultado-pruebas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(resultadoPruebaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ResultadoPrueba in the database
        List<ResultadoPrueba> resultadoPruebaList = resultadoPruebaRepository.findAll();
        assertThat(resultadoPruebaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteResultadoPrueba() throws Exception {
        // Initialize the database
        resultadoPruebaRepository.saveAndFlush(resultadoPrueba);

        int databaseSizeBeforeDelete = resultadoPruebaRepository.findAll().size();

        // Delete the resultadoPrueba
        restResultadoPruebaMockMvc.perform(delete("/api/resultado-pruebas/{id}", resultadoPrueba.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ResultadoPrueba> resultadoPruebaList = resultadoPruebaRepository.findAll();
        assertThat(resultadoPruebaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
