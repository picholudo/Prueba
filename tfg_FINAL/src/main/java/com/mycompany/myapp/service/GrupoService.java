package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.GrupoDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.Grupo}.
 */
public interface GrupoService {

    /**
     * Save a grupo.
     *
     * @param grupoDTO the entity to save.
     * @return the persisted entity.
     */
    GrupoDTO save(GrupoDTO grupoDTO);

    /**
     * Get all the grupos.
     *
     * @return the list of entities.
     */
    List<GrupoDTO> findAll();

    /**
     * Get all the grupos with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    Page<GrupoDTO> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" grupo.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<GrupoDTO> findOne(Long id);

    /**
     * Delete the "id" grupo.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
