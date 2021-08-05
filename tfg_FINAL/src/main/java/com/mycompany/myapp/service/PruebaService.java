package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.enumeration.TipoPrueba;
import com.mycompany.myapp.service.dto.PruebaDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.Prueba}.
 */
public interface PruebaService {

    /**
     * Save a prueba.
     *
     * @param pruebaDTO the entity to save.
     * @return the persisted entity.
     */
    PruebaDTO save(PruebaDTO pruebaDTO);

    /**
     * Get all the pruebas.
     *
     * @return the list of entities.
     */
    List<PruebaDTO> findAll();


    /**
     * Get the "id" prueba.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PruebaDTO> findOne(Long id);

    /**
     * Delete the "id" prueba.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

	List<PruebaDTO> findByTipoPrueba(TipoPrueba tipoPrueba);
}
