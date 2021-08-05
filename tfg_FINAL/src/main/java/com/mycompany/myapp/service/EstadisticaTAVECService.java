package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.EstadisticaTAVECDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.EstadisticaTAVEC}.
 */
public interface EstadisticaTAVECService {

    /**
     * Save a estadisticaTAVEC.
     *
     * @param estadisticaTAVECDTO the entity to save.
     * @return the persisted entity.
     */
    EstadisticaTAVECDTO save(EstadisticaTAVECDTO estadisticaTAVECDTO);

    /**
     * Get all the estadisticaTAVECS.
     *
     * @return the list of entities.
     */
    List<EstadisticaTAVECDTO> findAll();


    /**
     * Get the "id" estadisticaTAVEC.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EstadisticaTAVECDTO> findOne(Long id);

    /**
     * Delete the "id" estadisticaTAVEC.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
