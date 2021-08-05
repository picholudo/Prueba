package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.CodigoEstudioDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.CodigoEstudio}.
 */
public interface CodigoEstudioService {

    /**
     * Save a codigoEstudio.
     *
     * @param codigoEstudioDTO the entity to save.
     * @return the persisted entity.
     */
    CodigoEstudioDTO save(CodigoEstudioDTO codigoEstudioDTO);

    /**
     * Get all the codigoEstudios.
     *
     * @return the list of entities.
     */
    List<CodigoEstudioDTO> findAll();


    /**
     * Get the "id" codigoEstudio.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CodigoEstudioDTO> findOne(Long id);

    /**
     * Delete the "id" codigoEstudio.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
