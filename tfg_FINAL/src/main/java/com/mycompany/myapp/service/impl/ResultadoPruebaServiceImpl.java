package com.mycompany.myapp.service.impl;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.myapp.domain.Informe;
import com.mycompany.myapp.domain.ResultadoPrueba;
import com.mycompany.myapp.repository.InformeRepository;
import com.mycompany.myapp.repository.ResultadoPruebaRepository;
import com.mycompany.myapp.service.CalculadorResultadoPrueba;
import com.mycompany.myapp.service.CalculadorResultadoPruebaException;
import com.mycompany.myapp.service.ResultadoPruebaService;
import com.mycompany.myapp.service.dto.ResultadoPruebaDTO;
import com.mycompany.myapp.service.mapper.ResultadoPruebaMapper;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;


/**
 * Service Implementation for managing {@link ResultadoPrueba}.
 */
@Service
@Transactional
public class ResultadoPruebaServiceImpl implements ResultadoPruebaService {

    private final Logger log = LoggerFactory.getLogger(ResultadoPruebaServiceImpl.class);

    private final ResultadoPruebaRepository resultadoPruebaRepository;
    private final InformeRepository informeRepository;
    private final CalculadorResultadoPrueba calculadorResultadoPrueba;
    

    private final ResultadoPruebaMapper resultadoPruebaMapper;

    public ResultadoPruebaServiceImpl(ResultadoPruebaRepository resultadoPruebaRepository, InformeRepository informeRepository, CalculadorResultadoPrueba calculadorResultadoPrueba, ResultadoPruebaMapper resultadoPruebaMapper) {
        this.resultadoPruebaRepository = resultadoPruebaRepository;
        this.informeRepository = informeRepository;
        this.resultadoPruebaMapper = resultadoPruebaMapper;
        this.calculadorResultadoPrueba = calculadorResultadoPrueba;
    }

    /**
     * Save a resultadoPrueba.
     *
     * @param resultadoPruebaDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ResultadoPruebaDTO save(ResultadoPruebaDTO resultadoPruebaDTO) {
        log.debug("Request to save ResultadoPrueba : {}", resultadoPruebaDTO);
        ResultadoPrueba resultadoPrueba = resultadoPruebaMapper.toEntity(resultadoPruebaDTO);
        if(resultadoPrueba.getPd() != null) {
        	resultadoPrueba = calculadorResultadoPrueba.calcular(resultadoPrueba);
        }
        resultadoPrueba = resultadoPruebaRepository.save(resultadoPrueba);
        
        return resultadoPruebaMapper.toDto(resultadoPrueba);
    }

    /**
     * Get all the resultadoPruebas.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<ResultadoPruebaDTO> findAll() {
        log.debug("Request to get all ResultadoPruebas");
        return resultadoPruebaRepository.findAll().stream()
            .map(resultadoPruebaMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one resultadoPrueba by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ResultadoPruebaDTO> findOne(Long id) {
        log.debug("Request to get ResultadoPrueba : {}", id);
        return resultadoPruebaRepository.findById(id)
            .map(resultadoPruebaMapper::toDto);
    }

    /**
     * Delete the resultadoPrueba by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ResultadoPrueba : {}", id);
        resultadoPruebaRepository.deleteById(id);
    }
    
    @Override
    public List<ResultadoPruebaDTO> findByInformeId(Long informeId) {
        log.debug("Request to get all ResultadoPruebas");
        
        Optional<Informe> informe = informeRepository.findById(informeId);
        if(informe.isPresent()) {
        	log.debug("Informe {} has been found", informe);
        	
            return resultadoPruebaRepository.findByInforme(informe.get()).stream()
                    .map(resultadoPruebaMapper::toDto)
                    .collect(Collectors.toCollection(LinkedList::new));        	
        } else {
        	return Collections.emptyList();
        }
    }
}
