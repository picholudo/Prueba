package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.EstadisticaFABDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.EstadisticaFAB}.
 */
public interface EstadisticaFABService {

    /**
     * Save a estadisticaFAB.
     *
     * @param estadisticaFABDTO the entity to save.
     * @return the persisted entity.
     */
    EstadisticaFABDTO save(EstadisticaFABDTO estadisticaFABDTO);

    /**
     * Get all the estadisticaFABS.
     *
     * @return the list of entities.
     */
    List<EstadisticaFABDTO> findAll();


    /**
     * Get the "id" estadisticaFAB.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EstadisticaFABDTO> findOne(Long id);

    /**
     * Delete the "id" estadisticaFAB.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
