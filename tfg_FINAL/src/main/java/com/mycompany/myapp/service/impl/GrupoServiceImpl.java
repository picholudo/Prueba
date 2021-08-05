package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.GrupoService;
import com.mycompany.myapp.domain.Grupo;
import com.mycompany.myapp.repository.GrupoRepository;
import com.mycompany.myapp.service.dto.GrupoDTO;
import com.mycompany.myapp.service.mapper.GrupoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Grupo}.
 */
@Service
@Transactional
public class GrupoServiceImpl implements GrupoService {

    private final Logger log = LoggerFactory.getLogger(GrupoServiceImpl.class);

    private final GrupoRepository grupoRepository;

    private final GrupoMapper grupoMapper;

    public GrupoServiceImpl(GrupoRepository grupoRepository, GrupoMapper grupoMapper) {
        this.grupoRepository = grupoRepository;
        this.grupoMapper = grupoMapper;
    }

    /**
     * Save a grupo.
     *
     * @param grupoDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public GrupoDTO save(GrupoDTO grupoDTO) {
        log.debug("Request to save Grupo : {}", grupoDTO);
        Grupo grupo = grupoMapper.toEntity(grupoDTO);
        grupo = grupoRepository.save(grupo);
        return grupoMapper.toDto(grupo);
    }

    /**
     * Get all the grupos.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<GrupoDTO> findAll() {
        log.debug("Request to get all Grupos");
        return grupoRepository.findAllWithEagerRelationships().stream()
            .map(grupoMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get all the grupos with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<GrupoDTO> findAllWithEagerRelationships(Pageable pageable) {
        return grupoRepository.findAllWithEagerRelationships(pageable).map(grupoMapper::toDto);
    }
    

    /**
     * Get one grupo by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<GrupoDTO> findOne(Long id) {
        log.debug("Request to get Grupo : {}", id);
        return grupoRepository.findOneWithEagerRelationships(id)
            .map(grupoMapper::toDto);
    }

    /**
     * Delete the grupo by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Grupo : {}", id);
        grupoRepository.deleteById(id);
    }
}
