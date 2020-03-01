package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.PuntosCorteDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.PuntosCorte}.
 */
public interface PuntosCorteService {

    /**
     * Save a puntosCorte.
     *
     * @param puntosCorteDTO the entity to save.
     * @return the persisted entity.
     */
    PuntosCorteDTO save(PuntosCorteDTO puntosCorteDTO);

    /**
     * Get all the puntosCortes.
     *
     * @return the list of entities.
     */
    List<PuntosCorteDTO> findAll();


    /**
     * Get the "id" puntosCorte.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PuntosCorteDTO> findOne(Long id);

    /**
     * Delete the "id" puntosCorte.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    void deleteAll();
}
