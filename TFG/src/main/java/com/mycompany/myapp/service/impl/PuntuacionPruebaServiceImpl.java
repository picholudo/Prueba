package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.PuntuacionPruebaService;
import com.mycompany.myapp.domain.PuntuacionPrueba;
import com.mycompany.myapp.repository.PuntuacionPruebaRepository;
import com.mycompany.myapp.service.dto.PuntuacionPruebaDTO;
import com.mycompany.myapp.service.mapper.PuntuacionPruebaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link PuntuacionPrueba}.
 */
@Service
@Transactional
public class PuntuacionPruebaServiceImpl implements PuntuacionPruebaService {

    private final Logger log = LoggerFactory.getLogger(PuntuacionPruebaServiceImpl.class);

    private final PuntuacionPruebaRepository puntuacionPruebaRepository;

    private final PuntuacionPruebaMapper puntuacionPruebaMapper;

    public PuntuacionPruebaServiceImpl(PuntuacionPruebaRepository puntuacionPruebaRepository, PuntuacionPruebaMapper puntuacionPruebaMapper) {
        this.puntuacionPruebaRepository = puntuacionPruebaRepository;
        this.puntuacionPruebaMapper = puntuacionPruebaMapper;
    }

    /**
     * Save a puntuacionPrueba.
     *
     * @param puntuacionPruebaDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public PuntuacionPruebaDTO save(PuntuacionPruebaDTO puntuacionPruebaDTO) {
        log.debug("Request to save PuntuacionPrueba : {}", puntuacionPruebaDTO);
        PuntuacionPrueba puntuacionPrueba = puntuacionPruebaMapper.toEntity(puntuacionPruebaDTO);
        puntuacionPrueba = puntuacionPruebaRepository.save(puntuacionPrueba);
        return puntuacionPruebaMapper.toDto(puntuacionPrueba);
    }

    /**
     * Get all the puntuacionPruebas.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<PuntuacionPruebaDTO> findAll() {
        log.debug("Request to get all PuntuacionPruebas");
        return puntuacionPruebaRepository.findAll().stream()
            .map(puntuacionPruebaMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one puntuacionPrueba by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PuntuacionPruebaDTO> findOne(Long id) {
        log.debug("Request to get PuntuacionPrueba : {}", id);
        return puntuacionPruebaRepository.findById(id)
            .map(puntuacionPruebaMapper::toDto);
    }

    /**
     * Delete the puntuacionPrueba by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete PuntuacionPrueba : {}", id);
        puntuacionPruebaRepository.deleteById(id);
    }
}
