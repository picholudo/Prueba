package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.ZScoreDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.ZScore}.
 */
public interface ZScoreService {

    /**
     * Save a zScore.
     *
     * @param zScoreDTO the entity to save.
     * @return the persisted entity.
     */
    ZScoreDTO save(ZScoreDTO zScoreDTO);

    /**
     * Get all the zScores.
     *
     * @return the list of entities.
     */
    List<ZScoreDTO> findAll();


    /**
     * Get the "id" zScore.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ZScoreDTO> findOne(Long id);

    /**
     * Delete the "id" zScore.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
