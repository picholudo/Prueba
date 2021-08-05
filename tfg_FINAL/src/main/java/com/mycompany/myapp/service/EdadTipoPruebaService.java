package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.enumeration.TipoPrueba;
import com.mycompany.myapp.service.dto.EdadTipoPruebaDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.EdadTipoPrueba}.
 */
public interface EdadTipoPruebaService {

    /**
     * Save a edadTipoPrueba.
     *
     * @param edadTipoPruebaDTO the entity to save.
     * @return the persisted entity.
     */
    EdadTipoPruebaDTO save(EdadTipoPruebaDTO edadTipoPruebaDTO);

    /**
     * Get all the edadTipoPruebas.
     *
     * @return the list of entities.
     */
    List<EdadTipoPruebaDTO> findAll();


    /**
     * Get the "id" edadTipoPrueba.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EdadTipoPruebaDTO> findOne(Long id);

    /**
     * Delete the "id" edadTipoPrueba.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
    
    
    /**
     * Get all the edadTipoPruebas.
     *
     * @return the list of entities.
     */
    List<EdadTipoPruebaDTO> findByTipoPrueba(TipoPrueba tipoPrueba);
    
}
