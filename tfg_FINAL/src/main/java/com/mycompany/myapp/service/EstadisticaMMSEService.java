package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.EstadisticaMMSEDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.EstadisticaMMSE}.
 */
public interface EstadisticaMMSEService {

    /**
     * Save a estadisticaMMSE.
     *
     * @param estadisticaMMSEDTO the entity to save.
     * @return the persisted entity.
     */
    EstadisticaMMSEDTO save(EstadisticaMMSEDTO estadisticaMMSEDTO);

    /**
     * Get all the estadisticaMMSES.
     *
     * @return the list of entities.
     */
    List<EstadisticaMMSEDTO> findAll();


    /**
     * Get the "id" estadisticaMMSE.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EstadisticaMMSEDTO> findOne(Long id);

    /**
     * Delete the "id" estadisticaMMSE.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
