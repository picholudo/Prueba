package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.EstadisticasDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.Estadisticas}.
 */
public interface EstadisticasService {

    /**
     * Save a estadisticas.
     *
     * @param estadisticasDTO the entity to save.
     * @return the persisted entity.
     */
    EstadisticasDTO save(EstadisticasDTO estadisticasDTO);

    /**
     * Get all the estadisticas.
     *
     * @return the list of entities.
     */
    List<EstadisticasDTO> findAll();


    /**
     * Get the "id" estadisticas.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EstadisticasDTO> findOne(Long id);

    /**
     * Delete the "id" estadisticas.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
