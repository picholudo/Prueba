package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.CodigoEstudioService;
import com.mycompany.myapp.domain.CodigoEstudio;
import com.mycompany.myapp.repository.CodigoEstudioRepository;
import com.mycompany.myapp.service.dto.CodigoEstudioDTO;
import com.mycompany.myapp.service.mapper.CodigoEstudioMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link CodigoEstudio}.
 */
@Service
@Transactional
public class CodigoEstudioServiceImpl implements CodigoEstudioService {

    private final Logger log = LoggerFactory.getLogger(CodigoEstudioServiceImpl.class);

    private final CodigoEstudioRepository codigoEstudioRepository;

    private final CodigoEstudioMapper codigoEstudioMapper;

    public CodigoEstudioServiceImpl(CodigoEstudioRepository codigoEstudioRepository, CodigoEstudioMapper codigoEstudioMapper) {
        this.codigoEstudioRepository = codigoEstudioRepository;
        this.codigoEstudioMapper = codigoEstudioMapper;
    }

    /**
     * Save a codigoEstudio.
     *
     * @param codigoEstudioDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public CodigoEstudioDTO save(CodigoEstudioDTO codigoEstudioDTO) {
        log.debug("Request to save CodigoEstudio : {}", codigoEstudioDTO);
        CodigoEstudio codigoEstudio = codigoEstudioMapper.toEntity(codigoEstudioDTO);
        codigoEstudio = codigoEstudioRepository.save(codigoEstudio);
        return codigoEstudioMapper.toDto(codigoEstudio);
    }

    /**
     * Get all the codigoEstudios.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<CodigoEstudioDTO> findAll() {
        log.debug("Request to get all CodigoEstudios");
        return codigoEstudioRepository.findAll().stream()
            .map(codigoEstudioMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one codigoEstudio by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CodigoEstudioDTO> findOne(Long id) {
        log.debug("Request to get CodigoEstudio : {}", id);
        return codigoEstudioRepository.findById(id)
            .map(codigoEstudioMapper::toDto);
    }

    /**
     * Delete the codigoEstudio by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CodigoEstudio : {}", id);
        codigoEstudioRepository.deleteById(id);
    }
}
