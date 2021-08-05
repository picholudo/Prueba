package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.EstadisticaTBAService;
import com.mycompany.myapp.domain.EstadisticaTBA;
import com.mycompany.myapp.repository.EstadisticaTBARepository;
import com.mycompany.myapp.service.dto.EstadisticaTBADTO;
import com.mycompany.myapp.service.mapper.EstadisticaTBAMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link EstadisticaTBA}.
 */
@Service
@Transactional
public class EstadisticaTBAServiceImpl implements EstadisticaTBAService {

    private final Logger log = LoggerFactory.getLogger(EstadisticaTBAServiceImpl.class);

    private final EstadisticaTBARepository estadisticaTBARepository;

    private final EstadisticaTBAMapper estadisticaTBAMapper;

    public EstadisticaTBAServiceImpl(EstadisticaTBARepository estadisticaTBARepository, EstadisticaTBAMapper estadisticaTBAMapper) {
        this.estadisticaTBARepository = estadisticaTBARepository;
        this.estadisticaTBAMapper = estadisticaTBAMapper;
    }

    /**
     * Save a estadisticaTBA.
     *
     * @param estadisticaTBADTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public EstadisticaTBADTO save(EstadisticaTBADTO estadisticaTBADTO) {
        log.debug("Request to save EstadisticaTBA : {}", estadisticaTBADTO);
        EstadisticaTBA estadisticaTBA = estadisticaTBAMapper.toEntity(estadisticaTBADTO);
        estadisticaTBA = estadisticaTBARepository.save(estadisticaTBA);
        return estadisticaTBAMapper.toDto(estadisticaTBA);
    }

    /**
     * Get all the estadisticaTBAS.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<EstadisticaTBADTO> findAll() {
        log.debug("Request to get all EstadisticaTBAS");
        return estadisticaTBARepository.findAll().stream()
            .map(estadisticaTBAMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one estadisticaTBA by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EstadisticaTBADTO> findOne(Long id) {
        log.debug("Request to get EstadisticaTBA : {}", id);
        return estadisticaTBARepository.findById(id)
            .map(estadisticaTBAMapper::toDto);
    }

    /**
     * Delete the estadisticaTBA by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EstadisticaTBA : {}", id);
        estadisticaTBARepository.deleteById(id);
    }
}
