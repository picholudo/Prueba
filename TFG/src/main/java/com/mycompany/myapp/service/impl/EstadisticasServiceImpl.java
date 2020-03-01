package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.EstadisticasService;
import com.mycompany.myapp.domain.Estadisticas;
import com.mycompany.myapp.repository.EstadisticasRepository;
import com.mycompany.myapp.service.dto.EstadisticasDTO;
import com.mycompany.myapp.service.mapper.EstadisticasMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Estadisticas}.
 */
@Service
@Transactional
public class EstadisticasServiceImpl implements EstadisticasService {

    private final Logger log = LoggerFactory.getLogger(EstadisticasServiceImpl.class);

    private final EstadisticasRepository estadisticasRepository;

    private final EstadisticasMapper estadisticasMapper;

    public EstadisticasServiceImpl(EstadisticasRepository estadisticasRepository, EstadisticasMapper estadisticasMapper) {
        this.estadisticasRepository = estadisticasRepository;
        this.estadisticasMapper = estadisticasMapper;
    }

    /**
     * Save a estadisticas.
     *
     * @param estadisticasDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public EstadisticasDTO save(EstadisticasDTO estadisticasDTO) {
        log.debug("Request to save Estadisticas : {}", estadisticasDTO);
        Estadisticas estadisticas = estadisticasMapper.toEntity(estadisticasDTO);
        estadisticas = estadisticasRepository.save(estadisticas);
        return estadisticasMapper.toDto(estadisticas);
    }

    /**
     * Get all the estadisticas.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<EstadisticasDTO> findAll() {
        log.debug("Request to get all Estadisticas");
        return estadisticasRepository.findAll().stream()
            .map(estadisticasMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one estadisticas by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EstadisticasDTO> findOne(Long id) {
        log.debug("Request to get Estadisticas : {}", id);
        return estadisticasRepository.findById(id)
            .map(estadisticasMapper::toDto);
    }

    /**
     * Delete the estadisticas by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Estadisticas : {}", id);
        estadisticasRepository.deleteById(id);
    }
}
