package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.Application;
import com.mycompany.myapp.domain.EstadisticaTAVEC;
import com.mycompany.myapp.domain.Prueba;
import com.mycompany.myapp.domain.EdadTipoPrueba;
import com.mycompany.myapp.repository.EstadisticaTAVECRepository;
import com.mycompany.myapp.service.EstadisticaTAVECService;
import com.mycompany.myapp.service.dto.EstadisticaTAVECDTO;
import com.mycompany.myapp.service.mapper.EstadisticaTAVECMapper;
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
 * Integration tests for the {@link EstadisticaTAVECResource} REST controller.
 */
@SpringBootTest(classes = Application.class)
public class EstadisticaTAVECResourceIT {

    private static final Float DEFAULT_MEDIA = 0F;
    private static final Float UPDATED_MEDIA = 1F;

    private static final Float DEFAULT_DESVIACION_TIPICA = 0F;
    private static final Float UPDATED_DESVIACION_TIPICA = 1F;

    @Autowired
    private EstadisticaTAVECRepository estadisticaTAVECRepository;

    @Autowired
    private EstadisticaTAVECMapper estadisticaTAVECMapper;

    @Autowired
    private EstadisticaTAVECService estadisticaTAVECService;

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

    private MockMvc restEstadisticaTAVECMockMvc;

    private EstadisticaTAVEC estadisticaTAVEC;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EstadisticaTAVECResource estadisticaTAVECResource = new EstadisticaTAVECResource(estadisticaTAVECService);
        this.restEstadisticaTAVECMockMvc = MockMvcBuilders.standaloneSetup(estadisticaTAVECResource)
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
    public static EstadisticaTAVEC createEntity(EntityManager em) {
        EstadisticaTAVEC estadisticaTAVEC = new EstadisticaTAVEC()
            .media(DEFAULT_MEDIA)
            .desviacionTipica(DEFAULT_DESVIACION_TIPICA);
        // Add required entity
        Prueba prueba;
        if (TestUtil.findAll(em, Prueba.class).isEmpty()) {
            prueba = PruebaResourceIT.createEntity(em);
            em.persist(prueba);
            em.flush();
        } else {
            prueba = TestUtil.findAll(em, Prueba.class).get(0);
        }
        estadisticaTAVEC.setPrueba(prueba);
        // Add required entity
        EdadTipoPrueba edadTipoPrueba;
        if (TestUtil.findAll(em, EdadTipoPrueba.class).isEmpty()) {
            edadTipoPrueba = EdadTipoPruebaResourceIT.createEntity(em);
            em.persist(edadTipoPrueba);
            em.flush();
        } else {
            edadTipoPrueba = TestUtil.findAll(em, EdadTipoPrueba.class).get(0);
        }
        estadisticaTAVEC.setEdadTipoPrueba(edadTipoPrueba);
        return estadisticaTAVEC;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EstadisticaTAVEC createUpdatedEntity(EntityManager em) {
        EstadisticaTAVEC estadisticaTAVEC = new EstadisticaTAVEC()
            .media(UPDATED_MEDIA)
            .desviacionTipica(UPDATED_DESVIACION_TIPICA);
        // Add required entity
        Prueba prueba;
        if (TestUtil.findAll(em, Prueba.class).isEmpty()) {
            prueba = PruebaResourceIT.createUpdatedEntity(em);
            em.persist(prueba);
            em.flush();
        } else {
            prueba = TestUtil.findAll(em, Prueba.class).get(0);
        }
        estadisticaTAVEC.setPrueba(prueba);
        // Add required entity
        EdadTipoPrueba edadTipoPrueba;
        if (TestUtil.findAll(em, EdadTipoPrueba.class).isEmpty()) {
            edadTipoPrueba = EdadTipoPruebaResourceIT.createUpdatedEntity(em);
            em.persist(edadTipoPrueba);
            em.flush();
        } else {
            edadTipoPrueba = TestUtil.findAll(em, EdadTipoPrueba.class).get(0);
        }
        estadisticaTAVEC.setEdadTipoPrueba(edadTipoPrueba);
        return estadisticaTAVEC;
    }

    @BeforeEach
    public void initTest() {
        estadisticaTAVEC = createEntity(em);
    }

    @Test
    @Transactional
    public void createEstadisticaTAVEC() throws Exception {
        int databaseSizeBeforeCreate = estadisticaTAVECRepository.findAll().size();

        // Create the EstadisticaTAVEC
        EstadisticaTAVECDTO estadisticaTAVECDTO = estadisticaTAVECMapper.toDto(estadisticaTAVEC);
        restEstadisticaTAVECMockMvc.perform(post("/api/estadistica-tavecs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(estadisticaTAVECDTO)))
            .andExpect(status().isCreated());

        // Validate the EstadisticaTAVEC in the database
        List<EstadisticaTAVEC> estadisticaTAVECList = estadisticaTAVECRepository.findAll();
        assertThat(estadisticaTAVECList).hasSize(databaseSizeBeforeCreate + 1);
        EstadisticaTAVEC testEstadisticaTAVEC = estadisticaTAVECList.get(estadisticaTAVECList.size() - 1);
        assertThat(testEstadisticaTAVEC.getMedia()).isEqualTo(DEFAULT_MEDIA);
        assertThat(testEstadisticaTAVEC.getDesviacionTipica()).isEqualTo(DEFAULT_DESVIACION_TIPICA);
    }

    @Test
    @Transactional
    public void createEstadisticaTAVECWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = estadisticaTAVECRepository.findAll().size();

        // Create the EstadisticaTAVEC with an existing ID
        estadisticaTAVEC.setId(1L);
        EstadisticaTAVECDTO estadisticaTAVECDTO = estadisticaTAVECMapper.toDto(estadisticaTAVEC);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEstadisticaTAVECMockMvc.perform(post("/api/estadistica-tavecs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(estadisticaTAVECDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EstadisticaTAVEC in the database
        List<EstadisticaTAVEC> estadisticaTAVECList = estadisticaTAVECRepository.findAll();
        assertThat(estadisticaTAVECList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkMediaIsRequired() throws Exception {
        int databaseSizeBeforeTest = estadisticaTAVECRepository.findAll().size();
        // set the field null
        estadisticaTAVEC.setMedia(null);

        // Create the EstadisticaTAVEC, which fails.
        EstadisticaTAVECDTO estadisticaTAVECDTO = estadisticaTAVECMapper.toDto(estadisticaTAVEC);

        restEstadisticaTAVECMockMvc.perform(post("/api/estadistica-tavecs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(estadisticaTAVECDTO)))
            .andExpect(status().isBadRequest());

        List<EstadisticaTAVEC> estadisticaTAVECList = estadisticaTAVECRepository.findAll();
        assertThat(estadisticaTAVECList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDesviacionTipicaIsRequired() throws Exception {
        int databaseSizeBeforeTest = estadisticaTAVECRepository.findAll().size();
        // set the field null
        estadisticaTAVEC.setDesviacionTipica(null);

        // Create the EstadisticaTAVEC, which fails.
        EstadisticaTAVECDTO estadisticaTAVECDTO = estadisticaTAVECMapper.toDto(estadisticaTAVEC);

        restEstadisticaTAVECMockMvc.perform(post("/api/estadistica-tavecs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(estadisticaTAVECDTO)))
            .andExpect(status().isBadRequest());

        List<EstadisticaTAVEC> estadisticaTAVECList = estadisticaTAVECRepository.findAll();
        assertThat(estadisticaTAVECList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllEstadisticaTAVECS() throws Exception {
        // Initialize the database
        estadisticaTAVECRepository.saveAndFlush(estadisticaTAVEC);

        // Get all the estadisticaTAVECList
        restEstadisticaTAVECMockMvc.perform(get("/api/estadistica-tavecs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(estadisticaTAVEC.getId().intValue())))
            .andExpect(jsonPath("$.[*].media").value(hasItem(DEFAULT_MEDIA.doubleValue())))
            .andExpect(jsonPath("$.[*].desviacionTipica").value(hasItem(DEFAULT_DESVIACION_TIPICA.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getEstadisticaTAVEC() throws Exception {
        // Initialize the database
        estadisticaTAVECRepository.saveAndFlush(estadisticaTAVEC);

        // Get the estadisticaTAVEC
        restEstadisticaTAVECMockMvc.perform(get("/api/estadistica-tavecs/{id}", estadisticaTAVEC.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(estadisticaTAVEC.getId().intValue()))
            .andExpect(jsonPath("$.media").value(DEFAULT_MEDIA.doubleValue()))
            .andExpect(jsonPath("$.desviacionTipica").value(DEFAULT_DESVIACION_TIPICA.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingEstadisticaTAVEC() throws Exception {
        // Get the estadisticaTAVEC
        restEstadisticaTAVECMockMvc.perform(get("/api/estadistica-tavecs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEstadisticaTAVEC() throws Exception {
        // Initialize the database
        estadisticaTAVECRepository.saveAndFlush(estadisticaTAVEC);

        int databaseSizeBeforeUpdate = estadisticaTAVECRepository.findAll().size();

        // Update the estadisticaTAVEC
        EstadisticaTAVEC updatedEstadisticaTAVEC = estadisticaTAVECRepository.findById(estadisticaTAVEC.getId()).get();
        // Disconnect from session so that the updates on updatedEstadisticaTAVEC are not directly saved in db
        em.detach(updatedEstadisticaTAVEC);
        updatedEstadisticaTAVEC
            .media(UPDATED_MEDIA)
            .desviacionTipica(UPDATED_DESVIACION_TIPICA);
        EstadisticaTAVECDTO estadisticaTAVECDTO = estadisticaTAVECMapper.toDto(updatedEstadisticaTAVEC);

        restEstadisticaTAVECMockMvc.perform(put("/api/estadistica-tavecs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(estadisticaTAVECDTO)))
            .andExpect(status().isOk());

        // Validate the EstadisticaTAVEC in the database
        List<EstadisticaTAVEC> estadisticaTAVECList = estadisticaTAVECRepository.findAll();
        assertThat(estadisticaTAVECList).hasSize(databaseSizeBeforeUpdate);
        EstadisticaTAVEC testEstadisticaTAVEC = estadisticaTAVECList.get(estadisticaTAVECList.size() - 1);
        assertThat(testEstadisticaTAVEC.getMedia()).isEqualTo(UPDATED_MEDIA);
        assertThat(testEstadisticaTAVEC.getDesviacionTipica()).isEqualTo(UPDATED_DESVIACION_TIPICA);
    }

    @Test
    @Transactional
    public void updateNonExistingEstadisticaTAVEC() throws Exception {
        int databaseSizeBeforeUpdate = estadisticaTAVECRepository.findAll().size();

        // Create the EstadisticaTAVEC
        EstadisticaTAVECDTO estadisticaTAVECDTO = estadisticaTAVECMapper.toDto(estadisticaTAVEC);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEstadisticaTAVECMockMvc.perform(put("/api/estadistica-tavecs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(estadisticaTAVECDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EstadisticaTAVEC in the database
        List<EstadisticaTAVEC> estadisticaTAVECList = estadisticaTAVECRepository.findAll();
        assertThat(estadisticaTAVECList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEstadisticaTAVEC() throws Exception {
        // Initialize the database
        estadisticaTAVECRepository.saveAndFlush(estadisticaTAVEC);

        int databaseSizeBeforeDelete = estadisticaTAVECRepository.findAll().size();

        // Delete the estadisticaTAVEC
        restEstadisticaTAVECMockMvc.perform(delete("/api/estadistica-tavecs/{id}", estadisticaTAVEC.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EstadisticaTAVEC> estadisticaTAVECList = estadisticaTAVECRepository.findAll();
        assertThat(estadisticaTAVECList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
