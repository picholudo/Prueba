package com.mycompany.myapp.service.impl;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.mycompany.myapp.domain.Grupo;
import com.mycompany.myapp.domain.Informe;
import com.mycompany.myapp.domain.Paciente;
import com.mycompany.myapp.domain.User;
import com.mycompany.myapp.repository.GrupoRepository;
import com.mycompany.myapp.repository.InformeRepository;
import com.mycompany.myapp.repository.PacienteRepository;
import com.mycompany.myapp.repository.UserRepository;
import com.mycompany.myapp.service.InformeService;
import com.mycompany.myapp.service.dto.InformeDTO;
import com.mycompany.myapp.service.mapper.InformeMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

/**
 * Service Implementation for managing {@link Informe}.
 */
@Service
@Transactional
public class InformeServiceImpl implements InformeService {

    private final Logger log = LoggerFactory.getLogger(InformeServiceImpl.class);

    private final InformeRepository informeRepository;
    private final PacienteRepository pacienteRepository;
    private final UserRepository userRepository;
    private final GrupoRepository grupoRepository;

    private final InformeMapper informeMapper;

    public InformeServiceImpl(InformeRepository informeRepository, PacienteRepository pacienteRepository, UserRepository userRepository, GrupoRepository grupoRepository, InformeMapper informeMapper) {
        this.informeRepository = informeRepository;
        this.pacienteRepository = pacienteRepository;
        this.userRepository = userRepository;
        this.grupoRepository = grupoRepository;
        this.informeMapper = informeMapper;
    }

    /**
     * Save a informe.
     *
     * @param informeDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public InformeDTO save(InformeDTO informeDTO) {
        log.debug("Request to save Informe : {}", informeDTO);
        Informe informe = informeMapper.toEntity(informeDTO);
        informe = informeRepository.save(informe);
        return informeMapper.toDto(informe);
    }

    /**
     * Get all the informes.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<InformeDTO> findAll() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if(authentication != null && authentication.isAuthenticated()) {
        	log.debug("Request to get all Informes for user {}", authentication.getName());
        	Optional<User> user = userRepository.findOneByLogin(authentication.getName());
        	if(user.isPresent()) {
        		Set<Grupo> grupos = user.get().getGrupos();
        		if(CollectionUtils.isEmpty(grupos)) {
            		return informeRepository.findByUserIsCurrentUser().stream()
                            .map(informeMapper::toDto)
                            .collect(Collectors.toCollection(LinkedList::new));
        		} else {
            		return informeRepository.findByGruposOrUserIsCurrentUser(grupos).stream()
                            .map(informeMapper::toDto)
                            .collect(Collectors.toCollection(LinkedList::new));        			
        		}        		
        	}

        	return Collections.emptyList();
        } else {
        	log.debug("Request to get all Informes");
            return informeRepository.findAll().stream()
                    .map(informeMapper::toDto)
                    .collect(Collectors.toCollection(LinkedList::new));
        }
    }


    /**
     * Get one informe by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<InformeDTO> findOne(Long id) {
        log.debug("Request to get Informe : {}", id);
        return informeRepository.findById(id)
            .map(informeMapper::toDto);
    }

    /**
     * Delete the informe by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Informe : {}", id);
        informeRepository.deleteById(id);
    }
    
    @Override
    public List<InformeDTO> findByPacienteId(Long pacienteId) {
        log.debug("Request to get all Informes by pacienteId {}", pacienteId);

        Optional<Paciente> paciente = pacienteRepository.findById(pacienteId);
        if(paciente.isPresent()) {
        	log.debug("Paciente {} has been found", paciente);
        	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if(authentication != null && authentication.isAuthenticated()) {
            	Optional<User> user = userRepository.findOneByLogin(authentication.getName());
            	if(user.isPresent()) { 
            		Set<Grupo> grupos = user.get().getGrupos();
            		if(CollectionUtils.isEmpty(grupos)) {
            			return informeRepository.findByPacienteAndUserIsCurrentUser(paciente.get()).stream()
                                .map(informeMapper::toDto)
                                .collect(Collectors.toCollection(LinkedList::new));
            		} else {
            			return informeRepository.findByPacienteAndGruposOrUserIsCurrentUser(paciente.get(), grupos).stream()
                                .map(informeMapper::toDto)
                                .collect(Collectors.toCollection(LinkedList::new));
            		}
            	}
            	
            } else {
            	return informeRepository.findByPaciente(paciente.get()).stream()
            		.map(informeMapper::toDto)
            		.collect(Collectors.toCollection(LinkedList::new));
            } 
	
        } 
        
        return Collections.emptyList();
        
    }

    @Override
    public String getSospechaClinicaSugerida() {
        // TODO Auto-generated method stub
        return null;
    }



}
