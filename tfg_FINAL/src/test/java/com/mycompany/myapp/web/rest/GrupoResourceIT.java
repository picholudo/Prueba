package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.Application;
import com.mycompany.myapp.domain.Grupo;
import com.mycompany.myapp.repository.GrupoRepository;
import com.mycompany.myapp.service.GrupoService;
import com.mycompany.myapp.service.dto.GrupoDTO;
import com.mycompany.myapp.service.mapper.GrupoMapper;
import com.mycompany.myapp.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static com.mycompany.myapp.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link GrupoResource} REST controller.
 */
@SpringBootTest(classes = Application.class)
public class GrupoResourceIT {

    private static final String DEFAULT_NOMBRE = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE = "BBBBBBBBBB";

    @Autowired
    private GrupoRepository grupoRepository;

    @Mock
    private GrupoRepository grupoRepositoryMock;

    @Autowired
    private GrupoMapper grupoMapper;

    @Mock
    private GrupoService grupoServiceMock;

    @Autowired
    private GrupoService grupoService;

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

    private MockMvc restGrupoMockMvc;

    private Grupo grupo;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final GrupoResource grupoResource = new GrupoResource(grupoService);
        this.restGrupoMockMvc = MockMvcBuilders.standaloneSetup(grupoResource)
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
    public static Grupo createEntity(EntityManager em) {
        Grupo grupo = new Grupo()
            .nombre(DEFAULT_NOMBRE);
        return grupo;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Grupo createUpdatedEntity(EntityManager em) {
        Grupo grupo = new Grupo()
            .nombre(UPDATED_NOMBRE);
        return grupo;
    }

    @BeforeEach
    public void initTest() {
        grupo = createEntity(em);
    }

    @Test
    @Transactional
    public void createGrupo() throws Exception {
        int databaseSizeBeforeCreate = grupoRepository.findAll().size();

        // Create the Grupo
        GrupoDTO grupoDTO = grupoMapper.toDto(grupo);
        restGrupoMockMvc.perform(post("/api/grupos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(grupoDTO)))
            .andExpect(status().isCreated());

        // Validate the Grupo in the database
        List<Grupo> grupoList = grupoRepository.findAll();
        assertThat(grupoList).hasSize(databaseSizeBeforeCreate + 1);
        Grupo testGrupo = grupoList.get(grupoList.size() - 1);
        assertThat(testGrupo.getNombre()).isEqualTo(DEFAULT_NOMBRE);
    }

    @Test
    @Transactional
    public void createGrupoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = grupoRepository.findAll().size();

        // Create the Grupo with an existing ID
        grupo.setId(1L);
        GrupoDTO grupoDTO = grupoMapper.toDto(grupo);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGrupoMockMvc.perform(post("/api/grupos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(grupoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Grupo in the database
        List<Grupo> grupoList = grupoRepository.findAll();
        assertThat(grupoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNombreIsRequired() throws Exception {
        int databaseSizeBeforeTest = grupoRepository.findAll().size();
        // set the field null
        grupo.setNombre(null);

        // Create the Grupo, which fails.
        GrupoDTO grupoDTO = grupoMapper.toDto(grupo);

        restGrupoMockMvc.perform(post("/api/grupos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(grupoDTO)))
            .andExpect(status().isBadRequest());

        List<Grupo> grupoList = grupoRepository.findAll();
        assertThat(grupoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllGrupos() throws Exception {
        // Initialize the database
        grupoRepository.saveAndFlush(grupo);

        // Get all the grupoList
        restGrupoMockMvc.perform(get("/api/grupos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(grupo.getId().intValue())))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE)));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllGruposWithEagerRelationshipsIsEnabled() throws Exception {
        GrupoResource grupoResource = new GrupoResource(grupoServiceMock);
        when(grupoServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        MockMvc restGrupoMockMvc = MockMvcBuilders.standaloneSetup(grupoResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restGrupoMockMvc.perform(get("/api/grupos?eagerload=true"))
        .andExpect(status().isOk());

        verify(grupoServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllGruposWithEagerRelationshipsIsNotEnabled() throws Exception {
        GrupoResource grupoResource = new GrupoResource(grupoServiceMock);
            when(grupoServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));
            MockMvc restGrupoMockMvc = MockMvcBuilders.standaloneSetup(grupoResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restGrupoMockMvc.perform(get("/api/grupos?eagerload=true"))
        .andExpect(status().isOk());

            verify(grupoServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getGrupo() throws Exception {
        // Initialize the database
        grupoRepository.saveAndFlush(grupo);

        // Get the grupo
        restGrupoMockMvc.perform(get("/api/grupos/{id}", grupo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(grupo.getId().intValue()))
            .andExpect(jsonPath("$.nombre").value(DEFAULT_NOMBRE));
    }

    @Test
    @Transactional
    public void getNonExistingGrupo() throws Exception {
        // Get the grupo
        restGrupoMockMvc.perform(get("/api/grupos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGrupo() throws Exception {
        // Initialize the database
        grupoRepository.saveAndFlush(grupo);

        int databaseSizeBeforeUpdate = grupoRepository.findAll().size();

        // Update the grupo
        Grupo updatedGrupo = grupoRepository.findById(grupo.getId()).get();
        // Disconnect from session so that the updates on updatedGrupo are not directly saved in db
        em.detach(updatedGrupo);
        updatedGrupo
            .nombre(UPDATED_NOMBRE);
        GrupoDTO grupoDTO = grupoMapper.toDto(updatedGrupo);

        restGrupoMockMvc.perform(put("/api/grupos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(grupoDTO)))
            .andExpect(status().isOk());

        // Validate the Grupo in the database
        List<Grupo> grupoList = grupoRepository.findAll();
        assertThat(grupoList).hasSize(databaseSizeBeforeUpdate);
        Grupo testGrupo = grupoList.get(grupoList.size() - 1);
        assertThat(testGrupo.getNombre()).isEqualTo(UPDATED_NOMBRE);
    }

    @Test
    @Transactional
    public void updateNonExistingGrupo() throws Exception {
        int databaseSizeBeforeUpdate = grupoRepository.findAll().size();

        // Create the Grupo
        GrupoDTO grupoDTO = grupoMapper.toDto(grupo);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGrupoMockMvc.perform(put("/api/grupos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(grupoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Grupo in the database
        List<Grupo> grupoList = grupoRepository.findAll();
        assertThat(grupoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteGrupo() throws Exception {
        // Initialize the database
        grupoRepository.saveAndFlush(grupo);

        int databaseSizeBeforeDelete = grupoRepository.findAll().size();

        // Delete the grupo
        restGrupoMockMvc.perform(delete("/api/grupos/{id}", grupo.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Grupo> grupoList = grupoRepository.findAll();
        assertThat(grupoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
