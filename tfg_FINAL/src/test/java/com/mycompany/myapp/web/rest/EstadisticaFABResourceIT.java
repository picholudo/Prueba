package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.Application;
import com.mycompany.myapp.domain.EstadisticaFAB;
import com.mycompany.myapp.domain.CodigoEstudio;
import com.mycompany.myapp.domain.EdadTipoPrueba;
import com.mycompany.myapp.repository.EstadisticaFABRepository;
import com.mycompany.myapp.service.EstadisticaFABService;
import com.mycompany.myapp.service.dto.EstadisticaFABDTO;
import com.mycompany.myapp.service.mapper.EstadisticaFABMapper;
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
 * Integration tests for the {@link EstadisticaFABResource} REST controller.
 */
@SpringBootTest(classes = Application.class)
public class EstadisticaFABResourceIT {

    private static final Float DEFAULT_MEDIA = 0F;
    private static final Float UPDATED_MEDIA = 1F;

    private static final Float DEFAULT_DESVIACION_TIPICA = 0F;
    private static final Float UPDATED_DESVIACION_TIPICA = 1F;

    @Autowired
    private EstadisticaFABRepository estadisticaFABRepository;

    @Autowired
    private EstadisticaFABMapper estadisticaFABMapper;

    @Autowired
    private EstadisticaFABService estadisticaFABService;

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

    private MockMvc restEstadisticaFABMockMvc;

    private EstadisticaFAB estadisticaFAB;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EstadisticaFABResource estadisticaFABResource = new EstadisticaFABResource(estadisticaFABService);
        this.restEstadisticaFABMockMvc = MockMvcBuilders.standaloneSetup(estadisticaFABResource)
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
    public static EstadisticaFAB createEntity(EntityManager em) {
        EstadisticaFAB estadisticaFAB = new EstadisticaFAB()
            .media(DEFAULT_MEDIA)
            .desviacionTipica(DEFAULT_DESVIACION_TIPICA);
        // Add required entity
        CodigoEstudio codigoEstudio;
        if (TestUtil.findAll(em, CodigoEstudio.class).isEmpty()) {
            codigoEstudio = CodigoEstudioResourceIT.createEntity(em);
            em.persist(codigoEstudio);
            em.flush();
        } else {
            codigoEstudio = TestUtil.findAll(em, CodigoEstudio.class).get(0);
        }
        estadisticaFAB.setCodigoEstudio(codigoEstudio);
        // Add required entity
        EdadTipoPrueba edadTipoPrueba;
        if (TestUtil.findAll(em, EdadTipoPrueba.class).isEmpty()) {
            edadTipoPrueba = EdadTipoPruebaResourceIT.createEntity(em);
            em.persist(edadTipoPrueba);
            em.flush();
        } else {
            edadTipoPrueba = TestUtil.findAll(em, EdadTipoPrueba.class).get(0);
        }
        estadisticaFAB.setEdadTipoPrueba(edadTipoPrueba);
        return estadisticaFAB;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EstadisticaFAB createUpdatedEntity(EntityManager em) {
        EstadisticaFAB estadisticaFAB = new EstadisticaFAB()
            .media(UPDATED_MEDIA)
            .desviacionTipica(UPDATED_DESVIACION_TIPICA);
        // Add required entity
        CodigoEstudio codigoEstudio;
        if (TestUtil.findAll(em, CodigoEstudio.class).isEmpty()) {
            codigoEstudio = CodigoEstudioResourceIT.createUpdatedEntity(em);
            em.persist(codigoEstudio);
            em.flush();
        } else {
            codigoEstudio = TestUtil.findAll(em, CodigoEstudio.class).get(0);
        }
        estadisticaFAB.setCodigoEstudio(codigoEstudio);
        // Add required entity
        EdadTipoPrueba edadTipoPrueba;
        if (TestUtil.findAll(em, EdadTipoPrueba.class).isEmpty()) {
            edadTipoPrueba = EdadTipoPruebaResourceIT.createUpdatedEntity(em);
            em.persist(edadTipoPrueba);
            em.flush();
        } else {
            edadTipoPrueba = TestUtil.findAll(em, EdadTipoPrueba.class).get(0);
        }
        estadisticaFAB.setEdadTipoPrueba(edadTipoPrueba);
        return estadisticaFAB;
    }

    @BeforeEach
    public void initTest() {
        estadisticaFAB = createEntity(em);
    }

    @Test
    @Transactional
    public void createEstadisticaFAB() throws Exception {
        int databaseSizeBeforeCreate = estadisticaFABRepository.findAll().size();

        // Create the EstadisticaFAB
        EstadisticaFABDTO estadisticaFABDTO = estadisticaFABMapper.toDto(estadisticaFAB);
        restEstadisticaFABMockMvc.perform(post("/api/estadistica-fabs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(estadisticaFABDTO)))
            .andExpect(status().isCreated());

        // Validate the EstadisticaFAB in the database
        List<EstadisticaFAB> estadisticaFABList = estadisticaFABRepository.findAll();
        assertThat(estadisticaFABList).hasSize(databaseSizeBeforeCreate + 1);
        EstadisticaFAB testEstadisticaFAB = estadisticaFABList.get(estadisticaFABList.size() - 1);
        assertThat(testEstadisticaFAB.getMedia()).isEqualTo(DEFAULT_MEDIA);
        assertThat(testEstadisticaFAB.getDesviacionTipica()).isEqualTo(DEFAULT_DESVIACION_TIPICA);
    }

    @Test
    @Transactional
    public void createEstadisticaFABWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = estadisticaFABRepository.findAll().size();

        // Create the EstadisticaFAB with an existing ID
        estadisticaFAB.setId(1L);
        EstadisticaFABDTO estadisticaFABDTO = estadisticaFABMapper.toDto(estadisticaFAB);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEstadisticaFABMockMvc.perform(post("/api/estadistica-fabs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(estadisticaFABDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EstadisticaFAB in the database
        List<EstadisticaFAB> estadisticaFABList = estadisticaFABRepository.findAll();
        assertThat(estadisticaFABList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkMediaIsRequired() throws Exception {
        int databaseSizeBeforeTest = estadisticaFABRepository.findAll().size();
        // set the field null
        estadisticaFAB.setMedia(null);

        // Create the EstadisticaFAB, which fails.
        EstadisticaFABDTO estadisticaFABDTO = estadisticaFABMapper.toDto(estadisticaFAB);

        restEstadisticaFABMockMvc.perform(post("/api/estadistica-fabs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(estadisticaFABDTO)))
            .andExpect(status().isBadRequest());

        List<EstadisticaFAB> estadisticaFABList = estadisticaFABRepository.findAll();
        assertThat(estadisticaFABList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDesviacionTipicaIsRequired() throws Exception {
        int databaseSizeBeforeTest = estadisticaFABRepository.findAll().size();
        // set the field null
        estadisticaFAB.setDesviacionTipica(null);

        // Create the EstadisticaFAB, which fails.
        EstadisticaFABDTO estadisticaFABDTO = estadisticaFABMapper.toDto(estadisticaFAB);

        restEstadisticaFABMockMvc.perform(post("/api/estadistica-fabs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(estadisticaFABDTO)))
            .andExpect(status().isBadRequest());

        List<EstadisticaFAB> estadisticaFABList = estadisticaFABRepository.findAll();
        assertThat(estadisticaFABList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllEstadisticaFABS() throws Exception {
        // Initialize the database
        estadisticaFABRepository.saveAndFlush(estadisticaFAB);

        // Get all the estadisticaFABList
        restEstadisticaFABMockMvc.perform(get("/api/estadistica-fabs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(estadisticaFAB.getId().intValue())))
            .andExpect(jsonPath("$.[*].media").value(hasItem(DEFAULT_MEDIA.doubleValue())))
            .andExpect(jsonPath("$.[*].desviacionTipica").value(hasItem(DEFAULT_DESVIACION_TIPICA.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getEstadisticaFAB() throws Exception {
        // Initialize the database
        estadisticaFABRepository.saveAndFlush(estadisticaFAB);

        // Get the estadisticaFAB
        restEstadisticaFABMockMvc.perform(get("/api/estadistica-fabs/{id}", estadisticaFAB.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(estadisticaFAB.getId().intValue()))
            .andExpect(jsonPath("$.media").value(DEFAULT_MEDIA.doubleValue()))
            .andExpect(jsonPath("$.desviacionTipica").value(DEFAULT_DESVIACION_TIPICA.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingEstadisticaFAB() throws Exception {
        // Get the estadisticaFAB
        restEstadisticaFABMockMvc.perform(get("/api/estadistica-fabs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEstadisticaFAB() throws Exception {
        // Initialize the database
        estadisticaFABRepository.saveAndFlush(estadisticaFAB);

        int databaseSizeBeforeUpdate = estadisticaFABRepository.findAll().size();

        // Update the estadisticaFAB
        EstadisticaFAB updatedEstadisticaFAB = estadisticaFABRepository.findById(estadisticaFAB.getId()).get();
        // Disconnect from session so that the updates on updatedEstadisticaFAB are not directly saved in db
        em.detach(updatedEstadisticaFAB);
        updatedEstadisticaFAB
            .media(UPDATED_MEDIA)
            .desviacionTipica(UPDATED_DESVIACION_TIPICA);
        EstadisticaFABDTO estadisticaFABDTO = estadisticaFABMapper.toDto(updatedEstadisticaFAB);

        restEstadisticaFABMockMvc.perform(put("/api/estadistica-fabs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(estadisticaFABDTO)))
            .andExpect(status().isOk());

        // Validate the EstadisticaFAB in the database
        List<EstadisticaFAB> estadisticaFABList = estadisticaFABRepository.findAll();
        assertThat(estadisticaFABList).hasSize(databaseSizeBeforeUpdate);
        EstadisticaFAB testEstadisticaFAB = estadisticaFABList.get(estadisticaFABList.size() - 1);
        assertThat(testEstadisticaFAB.getMedia()).isEqualTo(UPDATED_MEDIA);
        assertThat(testEstadisticaFAB.getDesviacionTipica()).isEqualTo(UPDATED_DESVIACION_TIPICA);
    }

    @Test
    @Transactional
    public void updateNonExistingEstadisticaFAB() throws Exception {
        int databaseSizeBeforeUpdate = estadisticaFABRepository.findAll().size();

        // Create the EstadisticaFAB
        EstadisticaFABDTO estadisticaFABDTO = estadisticaFABMapper.toDto(estadisticaFAB);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEstadisticaFABMockMvc.perform(put("/api/estadistica-fabs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(estadisticaFABDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EstadisticaFAB in the database
        List<EstadisticaFAB> estadisticaFABList = estadisticaFABRepository.findAll();
        assertThat(estadisticaFABList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEstadisticaFAB() throws Exception {
        // Initialize the database
        estadisticaFABRepository.saveAndFlush(estadisticaFAB);

        int databaseSizeBeforeDelete = estadisticaFABRepository.findAll().size();

        // Delete the estadisticaFAB
        restEstadisticaFABMockMvc.perform(delete("/api/estadistica-fabs/{id}", estadisticaFAB.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EstadisticaFAB> estadisticaFABList = estadisticaFABRepository.findAll();
        assertThat(estadisticaFABList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
