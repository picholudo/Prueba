package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.EstadisticaAjusteNeuronormaDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.EstadisticaAjusteNeuronorma}.
 */
public interface EstadisticaAjusteNeuronormaService {

    /**
     * Save a estadisticaAjusteNeuronorma.
     *
     * @param estadisticaAjusteNeuronormaDTO the entity to save.
     * @return the persisted entity.
     */
    EstadisticaAjusteNeuronormaDTO save(EstadisticaAjusteNeuronormaDTO estadisticaAjusteNeuronormaDTO);

    /**
     * Get all the estadisticaAjusteNeuronormas.
     *
     * @return the list of entities.
     */
    List<EstadisticaAjusteNeuronormaDTO> findAll();


    /**
     * Get the "id" estadisticaAjusteNeuronorma.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EstadisticaAjusteNeuronormaDTO> findOne(Long id);

    /**
     * Delete the "id" estadisticaAjusteNeuronorma.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
