package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.EstadisticaMMSEService;
import com.mycompany.myapp.domain.EstadisticaMMSE;
import com.mycompany.myapp.repository.EstadisticaMMSERepository;
import com.mycompany.myapp.service.dto.EstadisticaMMSEDTO;
import com.mycompany.myapp.service.mapper.EstadisticaMMSEMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link EstadisticaMMSE}.
 */
@Service
@Transactional
public class EstadisticaMMSEServiceImpl implements EstadisticaMMSEService {

    private final Logger log = LoggerFactory.getLogger(EstadisticaMMSEServiceImpl.class);

    private final EstadisticaMMSERepository estadisticaMMSERepository;

    private final EstadisticaMMSEMapper estadisticaMMSEMapper;

    public EstadisticaMMSEServiceImpl(EstadisticaMMSERepository estadisticaMMSERepository, EstadisticaMMSEMapper estadisticaMMSEMapper) {
        this.estadisticaMMSERepository = estadisticaMMSERepository;
        this.estadisticaMMSEMapper = estadisticaMMSEMapper;
    }

    /**
     * Save a estadisticaMMSE.
     *
     * @param estadisticaMMSEDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public EstadisticaMMSEDTO save(EstadisticaMMSEDTO estadisticaMMSEDTO) {
        log.debug("Request to save EstadisticaMMSE : {}", estadisticaMMSEDTO);
        EstadisticaMMSE estadisticaMMSE = estadisticaMMSEMapper.toEntity(estadisticaMMSEDTO);
        estadisticaMMSE = estadisticaMMSERepository.save(estadisticaMMSE);
        return estadisticaMMSEMapper.toDto(estadisticaMMSE);
    }

    /**
     * Get all the estadisticaMMSES.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<EstadisticaMMSEDTO> findAll() {
        log.debug("Request to get all EstadisticaMMSES");
        return estadisticaMMSERepository.findAll().stream()
            .map(estadisticaMMSEMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one estadisticaMMSE by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EstadisticaMMSEDTO> findOne(Long id) {
        log.debug("Request to get EstadisticaMMSE : {}", id);
        return estadisticaMMSERepository.findById(id)
            .map(estadisticaMMSEMapper::toDto);
    }

    /**
     * Delete the estadisticaMMSE by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EstadisticaMMSE : {}", id);
        estadisticaMMSERepository.deleteById(id);
    }
}
