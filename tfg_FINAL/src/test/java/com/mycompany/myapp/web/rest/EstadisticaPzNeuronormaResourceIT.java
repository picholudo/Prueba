package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.Application;
import com.mycompany.myapp.domain.EstadisticaPzNeuronorma;
import com.mycompany.myapp.repository.EstadisticaPzNeuronormaRepository;
import com.mycompany.myapp.service.EstadisticaPzNeuronormaService;
import com.mycompany.myapp.service.dto.EstadisticaPzNeuronormaDTO;
import com.mycompany.myapp.service.mapper.EstadisticaPzNeuronormaMapper;
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
 * Integration tests for the {@link EstadisticaPzNeuronormaResource} REST controller.
 */
@SpringBootTest(classes = Application.class)
public class EstadisticaPzNeuronormaResourceIT {

    private static final Integer DEFAULT_AJUSTE_ESTUDIOS = 0;
    private static final Integer UPDATED_AJUSTE_ESTUDIOS = 1;

    private static final Float DEFAULT_PZ = 1F;
    private static final Float UPDATED_PZ = 2F;

    @Autowired
    private EstadisticaPzNeuronormaRepository estadisticaPzNeuronormaRepository;

    @Autowired
    private EstadisticaPzNeuronormaMapper estadisticaPzNeuronormaMapper;

    @Autowired
    private EstadisticaPzNeuronormaService estadisticaPzNeuronormaService;

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

    private MockMvc restEstadisticaPzNeuronormaMockMvc;

    private EstadisticaPzNeuronorma estadisticaPzNeuronorma;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EstadisticaPzNeuronormaResource estadisticaPzNeuronormaResource = new EstadisticaPzNeuronormaResource(estadisticaPzNeuronormaService);
        this.restEstadisticaPzNeuronormaMockMvc = MockMvcBuilders.standaloneSetup(estadisticaPzNeuronormaResource)
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
    public static EstadisticaPzNeuronorma createEntity(EntityManager em) {
        EstadisticaPzNeuronorma estadisticaPzNeuronorma = new EstadisticaPzNeuronorma()
            .ajusteEstudios(DEFAULT_AJUSTE_ESTUDIOS)
            .pz(DEFAULT_PZ);
        return estadisticaPzNeuronorma;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EstadisticaPzNeuronorma createUpdatedEntity(EntityManager em) {
        EstadisticaPzNeuronorma estadisticaPzNeuronorma = new EstadisticaPzNeuronorma()
            .ajusteEstudios(UPDATED_AJUSTE_ESTUDIOS)
            .pz(UPDATED_PZ);
        return estadisticaPzNeuronorma;
    }

    @BeforeEach
    public void initTest() {
        estadisticaPzNeuronorma = createEntity(em);
    }

    @Test
    @Transactional
    public void createEstadisticaPzNeuronorma() throws Exception {
        int databaseSizeBeforeCreate = estadisticaPzNeuronormaRepository.findAll().size();

        // Create the EstadisticaPzNeuronorma
        EstadisticaPzNeuronormaDTO estadisticaPzNeuronormaDTO = estadisticaPzNeuronormaMapper.toDto(estadisticaPzNeuronorma);
        restEstadisticaPzNeuronormaMockMvc.perform(post("/api/estadistica-pz-neuronormas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(estadisticaPzNeuronormaDTO)))
            .andExpect(status().isCreated());

        // Validate the EstadisticaPzNeuronorma in the database
        List<EstadisticaPzNeuronorma> estadisticaPzNeuronormaList = estadisticaPzNeuronormaRepository.findAll();
        assertThat(estadisticaPzNeuronormaList).hasSize(databaseSizeBeforeCreate + 1);
        EstadisticaPzNeuronorma testEstadisticaPzNeuronorma = estadisticaPzNeuronormaList.get(estadisticaPzNeuronormaList.size() - 1);
        assertThat(testEstadisticaPzNeuronorma.getAjusteEstudios()).isEqualTo(DEFAULT_AJUSTE_ESTUDIOS);
        assertThat(testEstadisticaPzNeuronorma.getPz()).isEqualTo(DEFAULT_PZ);
    }

    @Test
    @Transactional
    public void createEstadisticaPzNeuronormaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = estadisticaPzNeuronormaRepository.findAll().size();

        // Create the EstadisticaPzNeuronorma with an existing ID
        estadisticaPzNeuronorma.setId(1L);
        EstadisticaPzNeuronormaDTO estadisticaPzNeuronormaDTO = estadisticaPzNeuronormaMapper.toDto(estadisticaPzNeuronorma);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEstadisticaPzNeuronormaMockMvc.perform(post("/api/estadistica-pz-neuronormas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(estadisticaPzNeuronormaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EstadisticaPzNeuronorma in the database
        List<EstadisticaPzNeuronorma> estadisticaPzNeuronormaList = estadisticaPzNeuronormaRepository.findAll();
        assertThat(estadisticaPzNeuronormaList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkAjusteEstudiosIsRequired() throws Exception {
        int databaseSizeBeforeTest = estadisticaPzNeuronormaRepository.findAll().size();
        // set the field null
        estadisticaPzNeuronorma.setAjusteEstudios(null);

        // Create the EstadisticaPzNeuronorma, which fails.
        EstadisticaPzNeuronormaDTO estadisticaPzNeuronormaDTO = estadisticaPzNeuronormaMapper.toDto(estadisticaPzNeuronorma);

        restEstadisticaPzNeuronormaMockMvc.perform(post("/api/estadistica-pz-neuronormas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(estadisticaPzNeuronormaDTO)))
            .andExpect(status().isBadRequest());

        List<EstadisticaPzNeuronorma> estadisticaPzNeuronormaList = estadisticaPzNeuronormaRepository.findAll();
        assertThat(estadisticaPzNeuronormaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPzIsRequired() throws Exception {
        int databaseSizeBeforeTest = estadisticaPzNeuronormaRepository.findAll().size();
        // set the field null
        estadisticaPzNeuronorma.setPz(null);

        // Create the EstadisticaPzNeuronorma, which fails.
        EstadisticaPzNeuronormaDTO estadisticaPzNeuronormaDTO = estadisticaPzNeuronormaMapper.toDto(estadisticaPzNeuronorma);

        restEstadisticaPzNeuronormaMockMvc.perform(post("/api/estadistica-pz-neuronormas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(estadisticaPzNeuronormaDTO)))
            .andExpect(status().isBadRequest());

        List<EstadisticaPzNeuronorma> estadisticaPzNeuronormaList = estadisticaPzNeuronormaRepository.findAll();
        assertThat(estadisticaPzNeuronormaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllEstadisticaPzNeuronormas() throws Exception {
        // Initialize the database
        estadisticaPzNeuronormaRepository.saveAndFlush(estadisticaPzNeuronorma);

        // Get all the estadisticaPzNeuronormaList
        restEstadisticaPzNeuronormaMockMvc.perform(get("/api/estadistica-pz-neuronormas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(estadisticaPzNeuronorma.getId().intValue())))
            .andExpect(jsonPath("$.[*].ajusteEstudios").value(hasItem(DEFAULT_AJUSTE_ESTUDIOS)))
            .andExpect(jsonPath("$.[*].pz").value(hasItem(DEFAULT_PZ.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getEstadisticaPzNeuronorma() throws Exception {
        // Initialize the database
        estadisticaPzNeuronormaRepository.saveAndFlush(estadisticaPzNeuronorma);

        // Get the estadisticaPzNeuronorma
        restEstadisticaPzNeuronormaMockMvc.perform(get("/api/estadistica-pz-neuronormas/{id}", estadisticaPzNeuronorma.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(estadisticaPzNeuronorma.getId().intValue()))
            .andExpect(jsonPath("$.ajusteEstudios").value(DEFAULT_AJUSTE_ESTUDIOS))
            .andExpect(jsonPath("$.pz").value(DEFAULT_PZ.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingEstadisticaPzNeuronorma() throws Exception {
        // Get the estadisticaPzNeuronorma
        restEstadisticaPzNeuronormaMockMvc.perform(get("/api/estadistica-pz-neuronormas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEstadisticaPzNeuronorma() throws Exception {
        // Initialize the database
        estadisticaPzNeuronormaRepository.saveAndFlush(estadisticaPzNeuronorma);

        int databaseSizeBeforeUpdate = estadisticaPzNeuronormaRepository.findAll().size();

        // Update the estadisticaPzNeuronorma
        EstadisticaPzNeuronorma updatedEstadisticaPzNeuronorma = estadisticaPzNeuronormaRepository.findById(estadisticaPzNeuronorma.getId()).get();
        // Disconnect from session so that the updates on updatedEstadisticaPzNeuronorma are not directly saved in db
        em.detach(updatedEstadisticaPzNeuronorma);
        updatedEstadisticaPzNeuronorma
            .ajusteEstudios(UPDATED_AJUSTE_ESTUDIOS)
            .pz(UPDATED_PZ);
        EstadisticaPzNeuronormaDTO estadisticaPzNeuronormaDTO = estadisticaPzNeuronormaMapper.toDto(updatedEstadisticaPzNeuronorma);

        restEstadisticaPzNeuronormaMockMvc.perform(put("/api/estadistica-pz-neuronormas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(estadisticaPzNeuronormaDTO)))
            .andExpect(status().isOk());

        // Validate the EstadisticaPzNeuronorma in the database
        List<EstadisticaPzNeuronorma> estadisticaPzNeuronormaList = estadisticaPzNeuronormaRepository.findAll();
        assertThat(estadisticaPzNeuronormaList).hasSize(databaseSizeBeforeUpdate);
        EstadisticaPzNeuronorma testEstadisticaPzNeuronorma = estadisticaPzNeuronormaList.get(estadisticaPzNeuronormaList.size() - 1);
        assertThat(testEstadisticaPzNeuronorma.getAjusteEstudios()).isEqualTo(UPDATED_AJUSTE_ESTUDIOS);
        assertThat(testEstadisticaPzNeuronorma.getPz()).isEqualTo(UPDATED_PZ);
    }

    @Test
    @Transactional
    public void updateNonExistingEstadisticaPzNeuronorma() throws Exception {
        int databaseSizeBeforeUpdate = estadisticaPzNeuronormaRepository.findAll().size();

        // Create the EstadisticaPzNeuronorma
        EstadisticaPzNeuronormaDTO estadisticaPzNeuronormaDTO = estadisticaPzNeuronormaMapper.toDto(estadisticaPzNeuronorma);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEstadisticaPzNeuronormaMockMvc.perform(put("/api/estadistica-pz-neuronormas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(estadisticaPzNeuronormaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EstadisticaPzNeuronorma in the database
        List<EstadisticaPzNeuronorma> estadisticaPzNeuronormaList = estadisticaPzNeuronormaRepository.findAll();
        assertThat(estadisticaPzNeuronormaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEstadisticaPzNeuronorma() throws Exception {
        // Initialize the database
        estadisticaPzNeuronormaRepository.saveAndFlush(estadisticaPzNeuronorma);

        int databaseSizeBeforeDelete = estadisticaPzNeuronormaRepository.findAll().size();

        // Delete the estadisticaPzNeuronorma
        restEstadisticaPzNeuronormaMockMvc.perform(delete("/api/estadistica-pz-neuronormas/{id}", estadisticaPzNeuronorma.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EstadisticaPzNeuronorma> estadisticaPzNeuronormaList = estadisticaPzNeuronormaRepository.findAll();
        assertThat(estadisticaPzNeuronormaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
