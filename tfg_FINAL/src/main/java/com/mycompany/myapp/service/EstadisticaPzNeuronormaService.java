package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.EstadisticaPzNeuronormaDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.EstadisticaPzNeuronorma}.
 */
public interface EstadisticaPzNeuronormaService {

    /**
     * Save a estadisticaPzNeuronorma.
     *
     * @param estadisticaPzNeuronormaDTO the entity to save.
     * @return the persisted entity.
     */
    EstadisticaPzNeuronormaDTO save(EstadisticaPzNeuronormaDTO estadisticaPzNeuronormaDTO);

    /**
     * Get all the estadisticaPzNeuronormas.
     *
     * @return the list of entities.
     */
    List<EstadisticaPzNeuronormaDTO> findAll();


    /**
     * Get the "id" estadisticaPzNeuronorma.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EstadisticaPzNeuronormaDTO> findOne(Long id);

    /**
     * Delete the "id" estadisticaPzNeuronorma.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
