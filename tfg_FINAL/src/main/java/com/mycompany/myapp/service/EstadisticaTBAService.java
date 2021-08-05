package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.EstadisticaTBADTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.EstadisticaTBA}.
 */
public interface EstadisticaTBAService {

    /**
     * Save a estadisticaTBA.
     *
     * @param estadisticaTBADTO the entity to save.
     * @return the persisted entity.
     */
    EstadisticaTBADTO save(EstadisticaTBADTO estadisticaTBADTO);

    /**
     * Get all the estadisticaTBAS.
     *
     * @return the list of entities.
     */
    List<EstadisticaTBADTO> findAll();


    /**
     * Get the "id" estadisticaTBA.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EstadisticaTBADTO> findOne(Long id);

    /**
     * Delete the "id" estadisticaTBA.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
