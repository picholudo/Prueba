package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.EvaluacionService;
import com.mycompany.myapp.domain.Evaluacion;
import com.mycompany.myapp.repository.EvaluacionRepository;
import com.mycompany.myapp.service.dto.EvaluacionDTO;
import com.mycompany.myapp.service.mapper.EvaluacionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Evaluacion}.
 */
@Service
@Transactional
public class EvaluacionServiceImpl implements EvaluacionService {

    private final Logger log = LoggerFactory.getLogger(EvaluacionServiceImpl.class);

    private final EvaluacionRepository evaluacionRepository;

    private final EvaluacionMapper evaluacionMapper;

    public EvaluacionServiceImpl(EvaluacionRepository evaluacionRepository, EvaluacionMapper evaluacionMapper) {
        this.evaluacionRepository = evaluacionRepository;
        this.evaluacionMapper = evaluacionMapper;
    }

    /**
     * Save a evaluacion.
     *
     * @param evaluacionDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public EvaluacionDTO save(EvaluacionDTO evaluacionDTO) {
        log.debug("Request to save Evaluacion : {}", evaluacionDTO);
        Evaluacion evaluacion = evaluacionMapper.toEntity(evaluacionDTO);
        evaluacion = evaluacionRepository.save(evaluacion);
        return evaluacionMapper.toDto(evaluacion);
    }

    /**
     * Get all the evaluacions.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<EvaluacionDTO> findAll() {
        log.debug("Request to get all Evaluacions");
        return evaluacionRepository.findAll().stream()
            .map(evaluacionMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one evaluacion by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EvaluacionDTO> findOne(Long id) {
        log.debug("Request to get Evaluacion : {}", id);
        return evaluacionRepository.findById(id)
            .map(evaluacionMapper::toDto);
    }

    /**
     * Delete the evaluacion by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Evaluacion : {}", id);
        evaluacionRepository.deleteById(id);
    }
}
