package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.EvaluacionDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.Evaluacion}.
 */
public interface EvaluacionService {

    /**
     * Save a evaluacion.
     *
     * @param evaluacionDTO the entity to save.
     * @return the persisted entity.
     */
    EvaluacionDTO save(EvaluacionDTO evaluacionDTO);

    /**
     * Get all the evaluacions.
     *
     * @return the list of entities.
     */
    List<EvaluacionDTO> findAll();


    /**
     * Get the "id" evaluacion.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EvaluacionDTO> findOne(Long id);

    /**
     * Delete the "id" evaluacion.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
