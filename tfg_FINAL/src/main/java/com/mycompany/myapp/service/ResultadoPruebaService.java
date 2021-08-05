package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.ResultadoPruebaDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.ResultadoPrueba}.
 */
public interface ResultadoPruebaService {

    /**
     * Save a resultadoPrueba.
     *
     * @param resultadoPruebaDTO the entity to save.
     * @return the persisted entity.
     */
    ResultadoPruebaDTO save(ResultadoPruebaDTO resultadoPruebaDTO);

    /**
     * Get all the resultadoPruebas.
     *
     * @return the list of entities.
     */
    List<ResultadoPruebaDTO> findAll();


    /**
     * Get the "id" resultadoPrueba.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ResultadoPruebaDTO> findOne(Long id);

    /**
     * Delete the "id" resultadoPrueba.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

	List<ResultadoPruebaDTO> findByInformeId(Long informeId);
}
