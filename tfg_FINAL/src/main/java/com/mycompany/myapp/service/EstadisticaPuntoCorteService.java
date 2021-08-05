package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.EstadisticaPuntoCorteDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.EstadisticaPuntoCorte}.
 */
public interface EstadisticaPuntoCorteService {

    /**
     * Save a estadisticaPuntoCorte.
     *
     * @param estadisticaPuntoCorteDTO the entity to save.
     * @return the persisted entity.
     */
    EstadisticaPuntoCorteDTO save(EstadisticaPuntoCorteDTO estadisticaPuntoCorteDTO);

    /**
     * Get all the estadisticaPuntoCortes.
     *
     * @return the list of entities.
     */
    List<EstadisticaPuntoCorteDTO> findAll();


    /**
     * Get the "id" estadisticaPuntoCorte.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EstadisticaPuntoCorteDTO> findOne(Long id);

    /**
     * Delete the "id" estadisticaPuntoCorte.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
