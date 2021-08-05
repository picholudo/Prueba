package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.EstadisticaSSNeuronormaService;
import com.mycompany.myapp.domain.EstadisticaSSNeuronorma;
import com.mycompany.myapp.repository.EstadisticaSSNeuronormaRepository;
import com.mycompany.myapp.service.dto.EstadisticaSSNeuronormaDTO;
import com.mycompany.myapp.service.mapper.EstadisticaSSNeuronormaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link EstadisticaSSNeuronorma}.
 */
@Service
@Transactional
public class EstadisticaSSNeuronormaServiceImpl implements EstadisticaSSNeuronormaService {

    private final Logger log = LoggerFactory.getLogger(EstadisticaSSNeuronormaServiceImpl.class);

    private final EstadisticaSSNeuronormaRepository estadisticaSSNeuronormaRepository;

    private final EstadisticaSSNeuronormaMapper estadisticaSSNeuronormaMapper;

    public EstadisticaSSNeuronormaServiceImpl(EstadisticaSSNeuronormaRepository estadisticaSSNeuronormaRepository, EstadisticaSSNeuronormaMapper estadisticaSSNeuronormaMapper) {
        this.estadisticaSSNeuronormaRepository = estadisticaSSNeuronormaRepository;
        this.estadisticaSSNeuronormaMapper = estadisticaSSNeuronormaMapper;
    }

    /**
     * Save a estadisticaSSNeuronorma.
     *
     * @param estadisticaSSNeuronormaDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public EstadisticaSSNeuronormaDTO save(EstadisticaSSNeuronormaDTO estadisticaSSNeuronormaDTO) {
        log.debug("Request to save EstadisticaSSNeuronorma : {}", estadisticaSSNeuronormaDTO);
        EstadisticaSSNeuronorma estadisticaSSNeuronorma = estadisticaSSNeuronormaMapper.toEntity(estadisticaSSNeuronormaDTO);
        estadisticaSSNeuronorma = estadisticaSSNeuronormaRepository.save(estadisticaSSNeuronorma);
        return estadisticaSSNeuronormaMapper.toDto(estadisticaSSNeuronorma);
    }

    /**
     * Get all the estadisticaSSNeuronormas.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<EstadisticaSSNeuronormaDTO> findAll() {
        log.debug("Request to get all EstadisticaSSNeuronormas");
        return estadisticaSSNeuronormaRepository.findAll().stream()
            .map(estadisticaSSNeuronormaMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one estadisticaSSNeuronorma by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EstadisticaSSNeuronormaDTO> findOne(Long id) {
        log.debug("Request to get EstadisticaSSNeuronorma : {}", id);
        return estadisticaSSNeuronormaRepository.findById(id)
            .map(estadisticaSSNeuronormaMapper::toDto);
    }

    /**
     * Delete the estadisticaSSNeuronorma by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EstadisticaSSNeuronorma : {}", id);
        estadisticaSSNeuronormaRepository.deleteById(id);
    }
}
