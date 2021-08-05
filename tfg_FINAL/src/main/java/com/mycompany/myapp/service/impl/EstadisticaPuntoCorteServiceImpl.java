package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.EstadisticaPuntoCorteService;
import com.mycompany.myapp.domain.EstadisticaPuntoCorte;
import com.mycompany.myapp.repository.EstadisticaPuntoCorteRepository;
import com.mycompany.myapp.service.dto.EstadisticaPuntoCorteDTO;
import com.mycompany.myapp.service.mapper.EstadisticaPuntoCorteMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link EstadisticaPuntoCorte}.
 */
@Service
@Transactional
public class EstadisticaPuntoCorteServiceImpl implements EstadisticaPuntoCorteService {

    private final Logger log = LoggerFactory.getLogger(EstadisticaPuntoCorteServiceImpl.class);

    private final EstadisticaPuntoCorteRepository estadisticaPuntoCorteRepository;

    private final EstadisticaPuntoCorteMapper estadisticaPuntoCorteMapper;

    public EstadisticaPuntoCorteServiceImpl(EstadisticaPuntoCorteRepository estadisticaPuntoCorteRepository, EstadisticaPuntoCorteMapper estadisticaPuntoCorteMapper) {
        this.estadisticaPuntoCorteRepository = estadisticaPuntoCorteRepository;
        this.estadisticaPuntoCorteMapper = estadisticaPuntoCorteMapper;
    }

    /**
     * Save a estadisticaPuntoCorte.
     *
     * @param estadisticaPuntoCorteDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public EstadisticaPuntoCorteDTO save(EstadisticaPuntoCorteDTO estadisticaPuntoCorteDTO) {
        log.debug("Request to save EstadisticaPuntoCorte : {}", estadisticaPuntoCorteDTO);
        EstadisticaPuntoCorte estadisticaPuntoCorte = estadisticaPuntoCorteMapper.toEntity(estadisticaPuntoCorteDTO);
        estadisticaPuntoCorte = estadisticaPuntoCorteRepository.save(estadisticaPuntoCorte);
        return estadisticaPuntoCorteMapper.toDto(estadisticaPuntoCorte);
    }

    /**
     * Get all the estadisticaPuntoCortes.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<EstadisticaPuntoCorteDTO> findAll() {
        log.debug("Request to get all EstadisticaPuntoCortes");
        return estadisticaPuntoCorteRepository.findAll().stream()
            .map(estadisticaPuntoCorteMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one estadisticaPuntoCorte by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EstadisticaPuntoCorteDTO> findOne(Long id) {
        log.debug("Request to get EstadisticaPuntoCorte : {}", id);
        return estadisticaPuntoCorteRepository.findById(id)
            .map(estadisticaPuntoCorteMapper::toDto);
    }

    /**
     * Delete the estadisticaPuntoCorte by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EstadisticaPuntoCorte : {}", id);
        estadisticaPuntoCorteRepository.deleteById(id);
    }
}
