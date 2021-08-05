package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.Application;
import com.mycompany.myapp.domain.EstadisticaPuntoCorte;
import com.mycompany.myapp.domain.Prueba;
import com.mycompany.myapp.repository.EstadisticaPuntoCorteRepository;
import com.mycompany.myapp.service.EstadisticaPuntoCorteService;
import com.mycompany.myapp.service.dto.EstadisticaPuntoCorteDTO;
import com.mycompany.myapp.service.mapper.EstadisticaPuntoCorteMapper;
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
 * Integration tests for the {@link EstadisticaPuntoCorteResource} REST controller.
 */
@SpringBootTest(classes = Application.class)
public class EstadisticaPuntoCorteResourceIT {

    private static final Integer DEFAULT_PUNTO_CORTE = 0;
    private static final Integer UPDATED_PUNTO_CORTE = 1;

    @Autowired
    private EstadisticaPuntoCorteRepository estadisticaPuntoCorteRepository;

    @Autowired
    private EstadisticaPuntoCorteMapper estadisticaPuntoCorteMapper;

    @Autowired
    private EstadisticaPuntoCorteService estadisticaPuntoCorteService;

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

    private MockMvc restEstadisticaPuntoCorteMockMvc;

    private EstadisticaPuntoCorte estadisticaPuntoCorte;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EstadisticaPuntoCorteResource estadisticaPuntoCorteResource = new EstadisticaPuntoCorteResource(estadisticaPuntoCorteService);
        this.restEstadisticaPuntoCorteMockMvc = MockMvcBuilders.standaloneSetup(estadisticaPuntoCorteResource)
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
    public static EstadisticaPuntoCorte createEntity(EntityManager em) {
        EstadisticaPuntoCorte estadisticaPuntoCorte = new EstadisticaPuntoCorte()
            .puntoCorte(DEFAULT_PUNTO_CORTE);
        // Add required entity
        Prueba prueba;
        if (TestUtil.findAll(em, Prueba.class).isEmpty()) {
            prueba = PruebaResourceIT.createEntity(em);
            em.persist(prueba);
            em.flush();
        } else {
            prueba = TestUtil.findAll(em, Prueba.class).get(0);
        }
        estadisticaPuntoCorte.setPrueba(prueba);
        return estadisticaPuntoCorte;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EstadisticaPuntoCorte createUpdatedEntity(EntityManager em) {
        EstadisticaPuntoCorte estadisticaPuntoCorte = new EstadisticaPuntoCorte()
            .puntoCorte(UPDATED_PUNTO_CORTE);
        // Add required entity
        Prueba prueba;
        if (TestUtil.findAll(em, Prueba.class).isEmpty()) {
            prueba = PruebaResourceIT.createUpdatedEntity(em);
            em.persist(prueba);
            em.flush();
        } else {
            prueba = TestUtil.findAll(em, Prueba.class).get(0);
        }
        estadisticaPuntoCorte.setPrueba(prueba);
        return estadisticaPuntoCorte;
    }

    @BeforeEach
    public void initTest() {
        estadisticaPuntoCorte = createEntity(em);
    }

    @Test
    @Transactional
    public void createEstadisticaPuntoCorte() throws Exception {
        int databaseSizeBeforeCreate = estadisticaPuntoCorteRepository.findAll().size();

        // Create the EstadisticaPuntoCorte
        EstadisticaPuntoCorteDTO estadisticaPuntoCorteDTO = estadisticaPuntoCorteMapper.toDto(estadisticaPuntoCorte);
        restEstadisticaPuntoCorteMockMvc.perform(post("/api/estadistica-punto-cortes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(estadisticaPuntoCorteDTO)))
            .andExpect(status().isCreated());

        // Validate the EstadisticaPuntoCorte in the database
        List<EstadisticaPuntoCorte> estadisticaPuntoCorteList = estadisticaPuntoCorteRepository.findAll();
        assertThat(estadisticaPuntoCorteList).hasSize(databaseSizeBeforeCreate + 1);
        EstadisticaPuntoCorte testEstadisticaPuntoCorte = estadisticaPuntoCorteList.get(estadisticaPuntoCorteList.size() - 1);
        assertThat(testEstadisticaPuntoCorte.getPuntoCorte()).isEqualTo(DEFAULT_PUNTO_CORTE);
    }

    @Test
    @Transactional
    public void createEstadisticaPuntoCorteWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = estadisticaPuntoCorteRepository.findAll().size();

        // Create the EstadisticaPuntoCorte with an existing ID
        estadisticaPuntoCorte.setId(1L);
        EstadisticaPuntoCorteDTO estadisticaPuntoCorteDTO = estadisticaPuntoCorteMapper.toDto(estadisticaPuntoCorte);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEstadisticaPuntoCorteMockMvc.perform(post("/api/estadistica-punto-cortes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(estadisticaPuntoCorteDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EstadisticaPuntoCorte in the database
        List<EstadisticaPuntoCorte> estadisticaPuntoCorteList = estadisticaPuntoCorteRepository.findAll();
        assertThat(estadisticaPuntoCorteList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkPuntoCorteIsRequired() throws Exception {
        int databaseSizeBeforeTest = estadisticaPuntoCorteRepository.findAll().size();
        // set the field null
        estadisticaPuntoCorte.setPuntoCorte(null);

        // Create the EstadisticaPuntoCorte, which fails.
        EstadisticaPuntoCorteDTO estadisticaPuntoCorteDTO = estadisticaPuntoCorteMapper.toDto(estadisticaPuntoCorte);

        restEstadisticaPuntoCorteMockMvc.perform(post("/api/estadistica-punto-cortes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(estadisticaPuntoCorteDTO)))
            .andExpect(status().isBadRequest());

        List<EstadisticaPuntoCorte> estadisticaPuntoCorteList = estadisticaPuntoCorteRepository.findAll();
        assertThat(estadisticaPuntoCorteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllEstadisticaPuntoCortes() throws Exception {
        // Initialize the database
        estadisticaPuntoCorteRepository.saveAndFlush(estadisticaPuntoCorte);

        // Get all the estadisticaPuntoCorteList
        restEstadisticaPuntoCorteMockMvc.perform(get("/api/estadistica-punto-cortes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(estadisticaPuntoCorte.getId().intValue())))
            .andExpect(jsonPath("$.[*].puntoCorte").value(hasItem(DEFAULT_PUNTO_CORTE)));
    }
    
    @Test
    @Transactional
    public void getEstadisticaPuntoCorte() throws Exception {
        // Initialize the database
        estadisticaPuntoCorteRepository.saveAndFlush(estadisticaPuntoCorte);

        // Get the estadisticaPuntoCorte
        restEstadisticaPuntoCorteMockMvc.perform(get("/api/estadistica-punto-cortes/{id}", estadisticaPuntoCorte.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(estadisticaPuntoCorte.getId().intValue()))
            .andExpect(jsonPath("$.puntoCorte").value(DEFAULT_PUNTO_CORTE));
    }

    @Test
    @Transactional
    public void getNonExistingEstadisticaPuntoCorte() throws Exception {
        // Get the estadisticaPuntoCorte
        restEstadisticaPuntoCorteMockMvc.perform(get("/api/estadistica-punto-cortes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEstadisticaPuntoCorte() throws Exception {
        // Initialize the database
        estadisticaPuntoCorteRepository.saveAndFlush(estadisticaPuntoCorte);

        int databaseSizeBeforeUpdate = estadisticaPuntoCorteRepository.findAll().size();

        // Update the estadisticaPuntoCorte
        EstadisticaPuntoCorte updatedEstadisticaPuntoCorte = estadisticaPuntoCorteRepository.findById(estadisticaPuntoCorte.getId()).get();
        // Disconnect from session so that the updates on updatedEstadisticaPuntoCorte are not directly saved in db
        em.detach(updatedEstadisticaPuntoCorte);
        updatedEstadisticaPuntoCorte
            .puntoCorte(UPDATED_PUNTO_CORTE);
        EstadisticaPuntoCorteDTO estadisticaPuntoCorteDTO = estadisticaPuntoCorteMapper.toDto(updatedEstadisticaPuntoCorte);

        restEstadisticaPuntoCorteMockMvc.perform(put("/api/estadistica-punto-cortes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(estadisticaPuntoCorteDTO)))
            .andExpect(status().isOk());

        // Validate the EstadisticaPuntoCorte in the database
        List<EstadisticaPuntoCorte> estadisticaPuntoCorteList = estadisticaPuntoCorteRepository.findAll();
        assertThat(estadisticaPuntoCorteList).hasSize(databaseSizeBeforeUpdate);
        EstadisticaPuntoCorte testEstadisticaPuntoCorte = estadisticaPuntoCorteList.get(estadisticaPuntoCorteList.size() - 1);
        assertThat(testEstadisticaPuntoCorte.getPuntoCorte()).isEqualTo(UPDATED_PUNTO_CORTE);
    }

    @Test
    @Transactional
    public void updateNonExistingEstadisticaPuntoCorte() throws Exception {
        int databaseSizeBeforeUpdate = estadisticaPuntoCorteRepository.findAll().size();

        // Create the EstadisticaPuntoCorte
        EstadisticaPuntoCorteDTO estadisticaPuntoCorteDTO = estadisticaPuntoCorteMapper.toDto(estadisticaPuntoCorte);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEstadisticaPuntoCorteMockMvc.perform(put("/api/estadistica-punto-cortes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(estadisticaPuntoCorteDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EstadisticaPuntoCorte in the database
        List<EstadisticaPuntoCorte> estadisticaPuntoCorteList = estadisticaPuntoCorteRepository.findAll();
        assertThat(estadisticaPuntoCorteList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEstadisticaPuntoCorte() throws Exception {
        // Initialize the database
        estadisticaPuntoCorteRepository.saveAndFlush(estadisticaPuntoCorte);

        int databaseSizeBeforeDelete = estadisticaPuntoCorteRepository.findAll().size();

        // Delete the estadisticaPuntoCorte
        restEstadisticaPuntoCorteMockMvc.perform(delete("/api/estadistica-punto-cortes/{id}", estadisticaPuntoCorte.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EstadisticaPuntoCorte> estadisticaPuntoCorteList = estadisticaPuntoCorteRepository.findAll();
        assertThat(estadisticaPuntoCorteList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
