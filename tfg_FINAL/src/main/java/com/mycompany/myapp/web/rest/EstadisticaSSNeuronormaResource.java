package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.EstadisticaSSNeuronormaService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.EstadisticaSSNeuronormaDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.EstadisticaSSNeuronorma}.
 */
@RestController
@RequestMapping("/api")
public class EstadisticaSSNeuronormaResource {

    private final Logger log = LoggerFactory.getLogger(EstadisticaSSNeuronormaResource.class);

    private static final String ENTITY_NAME = "estadisticaSSNeuronorma";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EstadisticaSSNeuronormaService estadisticaSSNeuronormaService;

    public EstadisticaSSNeuronormaResource(EstadisticaSSNeuronormaService estadisticaSSNeuronormaService) {
        this.estadisticaSSNeuronormaService = estadisticaSSNeuronormaService;
    }

    /**
     * {@code POST  /estadistica-ss-neuronormas} : Create a new estadisticaSSNeuronorma.
     *
     * @param estadisticaSSNeuronormaDTO the estadisticaSSNeuronormaDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new estadisticaSSNeuronormaDTO, or with status {@code 400 (Bad Request)} if the estadisticaSSNeuronorma has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/estadistica-ss-neuronormas")
    public ResponseEntity<EstadisticaSSNeuronormaDTO> createEstadisticaSSNeuronorma(@Valid @RequestBody EstadisticaSSNeuronormaDTO estadisticaSSNeuronormaDTO) throws URISyntaxException {
        log.debug("REST request to save EstadisticaSSNeuronorma : {}", estadisticaSSNeuronormaDTO);
        if (estadisticaSSNeuronormaDTO.getId() != null) {
            throw new BadRequestAlertException("A new estadisticaSSNeuronorma cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EstadisticaSSNeuronormaDTO result = estadisticaSSNeuronormaService.save(estadisticaSSNeuronormaDTO);
        return ResponseEntity.created(new URI("/api/estadistica-ss-neuronormas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /estadistica-ss-neuronormas} : Updates an existing estadisticaSSNeuronorma.
     *
     * @param estadisticaSSNeuronormaDTO the estadisticaSSNeuronormaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated estadisticaSSNeuronormaDTO,
     * or with status {@code 400 (Bad Request)} if the estadisticaSSNeuronormaDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the estadisticaSSNeuronormaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/estadistica-ss-neuronormas")
    public ResponseEntity<EstadisticaSSNeuronormaDTO> updateEstadisticaSSNeuronorma(@Valid @RequestBody EstadisticaSSNeuronormaDTO estadisticaSSNeuronormaDTO) throws URISyntaxException {
        log.debug("REST request to update EstadisticaSSNeuronorma : {}", estadisticaSSNeuronormaDTO);
        if (estadisticaSSNeuronormaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EstadisticaSSNeuronormaDTO result = estadisticaSSNeuronormaService.save(estadisticaSSNeuronormaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, estadisticaSSNeuronormaDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /estadistica-ss-neuronormas} : get all the estadisticaSSNeuronormas.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of estadisticaSSNeuronormas in body.
     */
    @GetMapping("/estadistica-ss-neuronormas")
    public List<EstadisticaSSNeuronormaDTO> getAllEstadisticaSSNeuronormas() {
        log.debug("REST request to get all EstadisticaSSNeuronormas");
        return estadisticaSSNeuronormaService.findAll();
    }

    /**
     * {@code GET  /estadistica-ss-neuronormas/:id} : get the "id" estadisticaSSNeuronorma.
     *
     * @param id the id of the estadisticaSSNeuronormaDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the estadisticaSSNeuronormaDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/estadistica-ss-neuronormas/{id}")
    public ResponseEntity<EstadisticaSSNeuronormaDTO> getEstadisticaSSNeuronorma(@PathVariable Long id) {
        log.debug("REST request to get EstadisticaSSNeuronorma : {}", id);
        Optional<EstadisticaSSNeuronormaDTO> estadisticaSSNeuronormaDTO = estadisticaSSNeuronormaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(estadisticaSSNeuronormaDTO);
    }

    /**
     * {@code DELETE  /estadistica-ss-neuronormas/:id} : delete the "id" estadisticaSSNeuronorma.
     *
     * @param id the id of the estadisticaSSNeuronormaDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/estadistica-ss-neuronormas/{id}")
    public ResponseEntity<Void> deleteEstadisticaSSNeuronorma(@PathVariable Long id) {
        log.debug("REST request to delete EstadisticaSSNeuronorma : {}", id);
        estadisticaSSNeuronormaService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
