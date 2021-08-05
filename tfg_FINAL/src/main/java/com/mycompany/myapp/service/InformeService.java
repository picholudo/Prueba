package com.mycompany.myapp.service;

import java.util.List;
import java.util.Optional;

import com.mycompany.myapp.service.dto.InformeDTO;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.Informe}.
 */
public interface InformeService {

    /**
     * Save a informe.
     *
     * @param informeDTO the entity to save.
     * @return the persisted entity.
     */
    InformeDTO save(InformeDTO informeDTO);

    /**
     * Get all the informes.
     *
     * @return the list of entities.
     */
    List<InformeDTO> findAll();


    /**
     * Get the "id" informe.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<InformeDTO> findOne(Long id);

    /**
     * Delete the "id" informe.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * @param pacienteId
     * @return
     */
	List<InformeDTO> findByPacienteId(Long pacienteId);

    String getSospechaClinicaSugerida();



}
