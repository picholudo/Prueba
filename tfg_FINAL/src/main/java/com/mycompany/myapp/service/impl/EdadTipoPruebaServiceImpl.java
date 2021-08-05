package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.EdadTipoPruebaService;
import com.mycompany.myapp.domain.EdadTipoPrueba;
import com.mycompany.myapp.domain.enumeration.TipoPrueba;
import com.mycompany.myapp.repository.EdadTipoPruebaRepository;
import com.mycompany.myapp.service.dto.EdadTipoPruebaDTO;
import com.mycompany.myapp.service.mapper.EdadTipoPruebaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link EdadTipoPrueba}.
 */
@Service
@Transactional
public class EdadTipoPruebaServiceImpl implements EdadTipoPruebaService {

    private final Logger log = LoggerFactory.getLogger(EdadTipoPruebaServiceImpl.class);

    private final EdadTipoPruebaRepository edadTipoPruebaRepository;

    private final EdadTipoPruebaMapper edadTipoPruebaMapper;

    public EdadTipoPruebaServiceImpl(EdadTipoPruebaRepository edadTipoPruebaRepository, EdadTipoPruebaMapper edadTipoPruebaMapper) {
        this.edadTipoPruebaRepository = edadTipoPruebaRepository;
        this.edadTipoPruebaMapper = edadTipoPruebaMapper;
    }

    /**
     * Save a edadTipoPrueba.
     *
     * @param edadTipoPruebaDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public EdadTipoPruebaDTO save(EdadTipoPruebaDTO edadTipoPruebaDTO) {
        log.debug("Request to save EdadTipoPrueba : {}", edadTipoPruebaDTO);
        EdadTipoPrueba edadTipoPrueba = edadTipoPruebaMapper.toEntity(edadTipoPruebaDTO);
        edadTipoPrueba = edadTipoPruebaRepository.save(edadTipoPrueba);
        return edadTipoPruebaMapper.toDto(edadTipoPrueba);
    }

    /**
     * Get all the edadTipoPruebas.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<EdadTipoPruebaDTO> findAll() {
        log.debug("Request to get all EdadTipoPruebas");
        return edadTipoPruebaRepository.findAll().stream()
            .map(edadTipoPruebaMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one edadTipoPrueba by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EdadTipoPruebaDTO> findOne(Long id) {
        log.debug("Request to get EdadTipoPrueba : {}", id);
        return edadTipoPruebaRepository.findById(id)
            .map(edadTipoPruebaMapper::toDto);
    }

    /**
     * Delete the edadTipoPrueba by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EdadTipoPrueba : {}", id);
        edadTipoPruebaRepository.deleteById(id);
    }
    
    
    /**
     * Get all the edadTipoPruebas.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<EdadTipoPruebaDTO> findByTipoPrueba(TipoPrueba tipoPrueba) {
        log.debug("Request to get all EdadTipoPruebas");
        return edadTipoPruebaRepository.findByTipoPrueba(tipoPrueba).stream()
            .map(edadTipoPruebaMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }
}
