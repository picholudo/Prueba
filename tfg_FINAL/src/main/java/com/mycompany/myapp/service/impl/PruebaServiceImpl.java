package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.PruebaService;
import com.mycompany.myapp.domain.Prueba;
import com.mycompany.myapp.domain.enumeration.TipoPrueba;
import com.mycompany.myapp.repository.PruebaRepository;
import com.mycompany.myapp.service.dto.PruebaDTO;
import com.mycompany.myapp.service.mapper.PruebaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Prueba}.
 */
@Service
@Transactional
public class PruebaServiceImpl implements PruebaService {

    private final Logger log = LoggerFactory.getLogger(PruebaServiceImpl.class);

    private final PruebaRepository pruebaRepository;

    private final PruebaMapper pruebaMapper;

    public PruebaServiceImpl(PruebaRepository pruebaRepository, PruebaMapper pruebaMapper) {
        this.pruebaRepository = pruebaRepository;
        this.pruebaMapper = pruebaMapper;
    }

    /**
     * Save a prueba.
     *
     * @param pruebaDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public PruebaDTO save(PruebaDTO pruebaDTO) {
        log.debug("Request to save Prueba : {}", pruebaDTO);
        Prueba prueba = pruebaMapper.toEntity(pruebaDTO);
        prueba = pruebaRepository.save(prueba);
        return pruebaMapper.toDto(prueba);
    }

    /**
     * Get all the pruebas.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<PruebaDTO> findAll() {
        log.debug("Request to get all Pruebas");
        return pruebaRepository.findAll().stream()
            .map(pruebaMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one prueba by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PruebaDTO> findOne(Long id) {
        log.debug("Request to get Prueba : {}", id);
        return pruebaRepository.findById(id)
            .map(pruebaMapper::toDto);
    }

    /**
     * Delete the prueba by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Prueba : {}", id);
        pruebaRepository.deleteById(id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<PruebaDTO> findByTipoPrueba(TipoPrueba tipoPrueba) {
        log.debug("Request to get all Pruebas by {}", tipoPrueba);
        return pruebaRepository.findByTipoPrueba(tipoPrueba).stream()
            .map(pruebaMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }
}
