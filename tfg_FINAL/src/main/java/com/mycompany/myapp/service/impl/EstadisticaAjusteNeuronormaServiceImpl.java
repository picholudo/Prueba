package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.EstadisticaAjusteNeuronormaService;
import com.mycompany.myapp.domain.EstadisticaAjusteNeuronorma;
import com.mycompany.myapp.repository.EstadisticaAjusteNeuronormaRepository;
import com.mycompany.myapp.service.dto.EstadisticaAjusteNeuronormaDTO;
import com.mycompany.myapp.service.mapper.EstadisticaAjusteNeuronormaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link EstadisticaAjusteNeuronorma}.
 */
@Service
@Transactional
public class EstadisticaAjusteNeuronormaServiceImpl implements EstadisticaAjusteNeuronormaService {

    private final Logger log = LoggerFactory.getLogger(EstadisticaAjusteNeuronormaServiceImpl.class);

    private final EstadisticaAjusteNeuronormaRepository estadisticaAjusteNeuronormaRepository;

    private final EstadisticaAjusteNeuronormaMapper estadisticaAjusteNeuronormaMapper;

    public EstadisticaAjusteNeuronormaServiceImpl(EstadisticaAjusteNeuronormaRepository estadisticaAjusteNeuronormaRepository, EstadisticaAjusteNeuronormaMapper estadisticaAjusteNeuronormaMapper) {
        this.estadisticaAjusteNeuronormaRepository = estadisticaAjusteNeuronormaRepository;
        this.estadisticaAjusteNeuronormaMapper = estadisticaAjusteNeuronormaMapper;
    }

    /**
     * Save a estadisticaAjusteNeuronorma.
     *
     * @param estadisticaAjusteNeuronormaDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public EstadisticaAjusteNeuronormaDTO save(EstadisticaAjusteNeuronormaDTO estadisticaAjusteNeuronormaDTO) {
        log.debug("Request to save EstadisticaAjusteNeuronorma : {}", estadisticaAjusteNeuronormaDTO);
        EstadisticaAjusteNeuronorma estadisticaAjusteNeuronorma = estadisticaAjusteNeuronormaMapper.toEntity(estadisticaAjusteNeuronormaDTO);
        estadisticaAjusteNeuronorma = estadisticaAjusteNeuronormaRepository.save(estadisticaAjusteNeuronorma);
        return estadisticaAjusteNeuronormaMapper.toDto(estadisticaAjusteNeuronorma);
    }

    /**
     * Get all the estadisticaAjusteNeuronormas.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<EstadisticaAjusteNeuronormaDTO> findAll() {
        log.debug("Request to get all EstadisticaAjusteNeuronormas");
        return estadisticaAjusteNeuronormaRepository.findAll().stream()
            .map(estadisticaAjusteNeuronormaMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one estadisticaAjusteNeuronorma by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EstadisticaAjusteNeuronormaDTO> findOne(Long id) {
        log.debug("Request to get EstadisticaAjusteNeuronorma : {}", id);
        return estadisticaAjusteNeuronormaRepository.findById(id)
            .map(estadisticaAjusteNeuronormaMapper::toDto);
    }

    /**
     * Delete the estadisticaAjusteNeuronorma by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EstadisticaAjusteNeuronorma : {}", id);
        estadisticaAjusteNeuronormaRepository.deleteById(id);
    }
}
