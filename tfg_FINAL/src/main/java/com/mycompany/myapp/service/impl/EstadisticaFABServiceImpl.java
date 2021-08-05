package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.EstadisticaFABService;
import com.mycompany.myapp.domain.EstadisticaFAB;
import com.mycompany.myapp.repository.EstadisticaFABRepository;
import com.mycompany.myapp.service.dto.EstadisticaFABDTO;
import com.mycompany.myapp.service.mapper.EstadisticaFABMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link EstadisticaFAB}.
 */
@Service
@Transactional
public class EstadisticaFABServiceImpl implements EstadisticaFABService {

    private final Logger log = LoggerFactory.getLogger(EstadisticaFABServiceImpl.class);

    private final EstadisticaFABRepository estadisticaFABRepository;

    private final EstadisticaFABMapper estadisticaFABMapper;

    public EstadisticaFABServiceImpl(EstadisticaFABRepository estadisticaFABRepository, EstadisticaFABMapper estadisticaFABMapper) {
        this.estadisticaFABRepository = estadisticaFABRepository;
        this.estadisticaFABMapper = estadisticaFABMapper;
    }

    /**
     * Save a estadisticaFAB.
     *
     * @param estadisticaFABDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public EstadisticaFABDTO save(EstadisticaFABDTO estadisticaFABDTO) {
        log.debug("Request to save EstadisticaFAB : {}", estadisticaFABDTO);
        EstadisticaFAB estadisticaFAB = estadisticaFABMapper.toEntity(estadisticaFABDTO);
        estadisticaFAB = estadisticaFABRepository.save(estadisticaFAB);
        return estadisticaFABMapper.toDto(estadisticaFAB);
    }

    /**
     * Get all the estadisticaFABS.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<EstadisticaFABDTO> findAll() {
        log.debug("Request to get all EstadisticaFABS");
        return estadisticaFABRepository.findAll().stream()
            .map(estadisticaFABMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one estadisticaFAB by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EstadisticaFABDTO> findOne(Long id) {
        log.debug("Request to get EstadisticaFAB : {}", id);
        return estadisticaFABRepository.findById(id)
            .map(estadisticaFABMapper::toDto);
    }

    /**
     * Delete the estadisticaFAB by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EstadisticaFAB : {}", id);
        estadisticaFABRepository.deleteById(id);
    }
}
