package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.EstadisticaPzNeuronormaService;
import com.mycompany.myapp.domain.EstadisticaPzNeuronorma;
import com.mycompany.myapp.repository.EstadisticaPzNeuronormaRepository;
import com.mycompany.myapp.service.dto.EstadisticaPzNeuronormaDTO;
import com.mycompany.myapp.service.mapper.EstadisticaPzNeuronormaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link EstadisticaPzNeuronorma}.
 */
@Service
@Transactional
public class EstadisticaPzNeuronormaServiceImpl implements EstadisticaPzNeuronormaService {

    private final Logger log = LoggerFactory.getLogger(EstadisticaPzNeuronormaServiceImpl.class);

    private final EstadisticaPzNeuronormaRepository estadisticaPzNeuronormaRepository;

    private final EstadisticaPzNeuronormaMapper estadisticaPzNeuronormaMapper;

    public EstadisticaPzNeuronormaServiceImpl(EstadisticaPzNeuronormaRepository estadisticaPzNeuronormaRepository, EstadisticaPzNeuronormaMapper estadisticaPzNeuronormaMapper) {
        this.estadisticaPzNeuronormaRepository = estadisticaPzNeuronormaRepository;
        this.estadisticaPzNeuronormaMapper = estadisticaPzNeuronormaMapper;
    }

    /**
     * Save a estadisticaPzNeuronorma.
     *
     * @param estadisticaPzNeuronormaDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public EstadisticaPzNeuronormaDTO save(EstadisticaPzNeuronormaDTO estadisticaPzNeuronormaDTO) {
        log.debug("Request to save EstadisticaPzNeuronorma : {}", estadisticaPzNeuronormaDTO);
        EstadisticaPzNeuronorma estadisticaPzNeuronorma = estadisticaPzNeuronormaMapper.toEntity(estadisticaPzNeuronormaDTO);
        estadisticaPzNeuronorma = estadisticaPzNeuronormaRepository.save(estadisticaPzNeuronorma);
        return estadisticaPzNeuronormaMapper.toDto(estadisticaPzNeuronorma);
    }

    /**
     * Get all the estadisticaPzNeuronormas.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<EstadisticaPzNeuronormaDTO> findAll() {
        log.debug("Request to get all EstadisticaPzNeuronormas");
        return estadisticaPzNeuronormaRepository.findAll().stream()
            .map(estadisticaPzNeuronormaMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one estadisticaPzNeuronorma by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EstadisticaPzNeuronormaDTO> findOne(Long id) {
        log.debug("Request to get EstadisticaPzNeuronorma : {}", id);
        return estadisticaPzNeuronormaRepository.findById(id)
            .map(estadisticaPzNeuronormaMapper::toDto);
    }

    /**
     * Delete the estadisticaPzNeuronorma by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EstadisticaPzNeuronorma : {}", id);
        estadisticaPzNeuronormaRepository.deleteById(id);
    }
}
