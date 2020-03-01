package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.PuntosCorteService;
import com.mycompany.myapp.domain.PuntosCorte;
import com.mycompany.myapp.repository.PuntosCorteRepository;
import com.mycompany.myapp.service.dto.PuntosCorteDTO;
import com.mycompany.myapp.service.mapper.PuntosCorteMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link PuntosCorte}.
 */
@Service
@Transactional
public class PuntosCorteServiceImpl implements PuntosCorteService {

    private final Logger log = LoggerFactory.getLogger(PuntosCorteServiceImpl.class);

    private final PuntosCorteRepository puntosCorteRepository;

    private final PuntosCorteMapper puntosCorteMapper;

    public PuntosCorteServiceImpl(PuntosCorteRepository puntosCorteRepository, PuntosCorteMapper puntosCorteMapper) {
        this.puntosCorteRepository = puntosCorteRepository;
        this.puntosCorteMapper = puntosCorteMapper;
    }

    /**
     * Save a puntosCorte.
     *
     * @param puntosCorteDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public PuntosCorteDTO save(PuntosCorteDTO puntosCorteDTO) {
        log.debug("Request to save PuntosCorte : {}", puntosCorteDTO);
        PuntosCorte puntosCorte = puntosCorteMapper.toEntity(puntosCorteDTO);
        puntosCorte = puntosCorteRepository.save(puntosCorte);
        return puntosCorteMapper.toDto(puntosCorte);
    }

    /**
     * Get all the puntosCortes.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<PuntosCorteDTO> findAll() {
        log.debug("Request to get all PuntosCortes");
        return puntosCorteRepository.findAll().stream()
            .map(puntosCorteMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one puntosCorte by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PuntosCorteDTO> findOne(Long id) {
        log.debug("Request to get PuntosCorte : {}", id);
        return puntosCorteRepository.findById(id)
            .map(puntosCorteMapper::toDto);
    }

    /**
     * Delete the puntosCorte by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete PuntosCorte : {}", id);
        puntosCorteRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        log.debug("Request to delete all PuntosCorte");
        puntosCorteRepository.deleteAll();
    }
}
