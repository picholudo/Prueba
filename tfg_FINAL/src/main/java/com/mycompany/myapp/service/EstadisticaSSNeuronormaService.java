package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.EstadisticaSSNeuronormaDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.EstadisticaSSNeuronorma}.
 */
public interface EstadisticaSSNeuronormaService {

    /**
     * Save a estadisticaSSNeuronorma.
     *
     * @param estadisticaSSNeuronormaDTO the entity to save.
     * @return the persisted entity.
     */
    EstadisticaSSNeuronormaDTO save(EstadisticaSSNeuronormaDTO estadisticaSSNeuronormaDTO);

    /**
     * Get all the estadisticaSSNeuronormas.
     *
     * @return the list of entities.
     */
    List<EstadisticaSSNeuronormaDTO> findAll();


    /**
     * Get the "id" estadisticaSSNeuronorma.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EstadisticaSSNeuronormaDTO> findOne(Long id);

    /**
     * Delete the "id" estadisticaSSNeuronorma.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
