package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.PuntuacionPruebaDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.PuntuacionPrueba}.
 */
public interface PuntuacionPruebaService {

    /**
     * Save a puntuacionPrueba.
     *
     * @param puntuacionPruebaDTO the entity to save.
     * @return the persisted entity.
     */
    PuntuacionPruebaDTO save(PuntuacionPruebaDTO puntuacionPruebaDTO);

    /**
     * Get all the puntuacionPruebas.
     *
     * @return the list of entities.
     */
    List<PuntuacionPruebaDTO> findAll();


    /**
     * Get the "id" puntuacionPrueba.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PuntuacionPruebaDTO> findOne(Long id);

    /**
     * Delete the "id" puntuacionPrueba.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
