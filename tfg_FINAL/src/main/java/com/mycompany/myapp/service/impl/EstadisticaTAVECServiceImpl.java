package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.EstadisticaTAVECService;
import com.mycompany.myapp.domain.EstadisticaTAVEC;
import com.mycompany.myapp.repository.EstadisticaTAVECRepository;
import com.mycompany.myapp.service.dto.EstadisticaTAVECDTO;
import com.mycompany.myapp.service.mapper.EstadisticaTAVECMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link EstadisticaTAVEC}.
 */
@Service
@Transactional
public class EstadisticaTAVECServiceImpl implements EstadisticaTAVECService {

    private final Logger log = LoggerFactory.getLogger(EstadisticaTAVECServiceImpl.class);

    private final EstadisticaTAVECRepository estadisticaTAVECRepository;

    private final EstadisticaTAVECMapper estadisticaTAVECMapper;

    public EstadisticaTAVECServiceImpl(EstadisticaTAVECRepository estadisticaTAVECRepository, EstadisticaTAVECMapper estadisticaTAVECMapper) {
        this.estadisticaTAVECRepository = estadisticaTAVECRepository;
        this.estadisticaTAVECMapper = estadisticaTAVECMapper;
    }

    /**
     * Save a estadisticaTAVEC.
     *
     * @param estadisticaTAVECDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public EstadisticaTAVECDTO save(EstadisticaTAVECDTO estadisticaTAVECDTO) {
        log.debug("Request to save EstadisticaTAVEC : {}", estadisticaTAVECDTO);
        EstadisticaTAVEC estadisticaTAVEC = estadisticaTAVECMapper.toEntity(estadisticaTAVECDTO);
        estadisticaTAVEC = estadisticaTAVECRepository.save(estadisticaTAVEC);
        return estadisticaTAVECMapper.toDto(estadisticaTAVEC);
    }

    /**
     * Get all the estadisticaTAVECS.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<EstadisticaTAVECDTO> findAll() {
        log.debug("Request to get all EstadisticaTAVECS");
        return estadisticaTAVECRepository.findAll().stream()
            .map(estadisticaTAVECMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one estadisticaTAVEC by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EstadisticaTAVECDTO> findOne(Long id) {
        log.debug("Request to get EstadisticaTAVEC : {}", id);
        return estadisticaTAVECRepository.findById(id)
            .map(estadisticaTAVECMapper::toDto);
    }

    /**
     * Delete the estadisticaTAVEC by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EstadisticaTAVEC : {}", id);
        estadisticaTAVECRepository.deleteById(id);
    }
}
