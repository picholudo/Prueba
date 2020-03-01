package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.ZScoreService;
import com.mycompany.myapp.domain.ZScore;
import com.mycompany.myapp.repository.ZScoreRepository;
import com.mycompany.myapp.service.dto.ZScoreDTO;
import com.mycompany.myapp.service.mapper.ZScoreMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link ZScore}.
 */
@Service
@Transactional
public class ZScoreServiceImpl implements ZScoreService {

    private final Logger log = LoggerFactory.getLogger(ZScoreServiceImpl.class);

    private final ZScoreRepository zScoreRepository;

    private final ZScoreMapper zScoreMapper;

    public ZScoreServiceImpl(ZScoreRepository zScoreRepository, ZScoreMapper zScoreMapper) {
        this.zScoreRepository = zScoreRepository;
        this.zScoreMapper = zScoreMapper;
    }

    /**
     * Save a zScore.
     *
     * @param zScoreDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ZScoreDTO save(ZScoreDTO zScoreDTO) {
        log.debug("Request to save ZScore : {}", zScoreDTO);
        ZScore zScore = zScoreMapper.toEntity(zScoreDTO);
        zScore = zScoreRepository.save(zScore);
        return zScoreMapper.toDto(zScore);
    }

    /**
     * Get all the zScores.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<ZScoreDTO> findAll() {
        log.debug("Request to get all ZScores");
        return zScoreRepository.findAll().stream()
            .map(zScoreMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one zScore by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ZScoreDTO> findOne(Long id) {
        log.debug("Request to get ZScore : {}", id);
        return zScoreRepository.findById(id)
            .map(zScoreMapper::toDto);
    }

    /**
     * Delete the zScore by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ZScore : {}", id);
        zScoreRepository.deleteById(id);
    }
}
