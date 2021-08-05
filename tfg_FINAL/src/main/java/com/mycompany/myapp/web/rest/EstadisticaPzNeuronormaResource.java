package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.EstadisticaPzNeuronormaService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.EstadisticaPzNeuronormaDTO;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.EstadisticaPzNeuronorma}.
 */
@RestController
@RequestMapping("/api")
public class EstadisticaPzNeuronormaResource {

    private final Logger log = LoggerFactory.getLogger(EstadisticaPzNeuronormaResource.class);

    private static final String ENTITY_NAME = "estadisticaPzNeuronorma";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EstadisticaPzNeuronormaService estadisticaPzNeuronormaService;

    public EstadisticaPzNeuronormaResource(EstadisticaPzNeuronormaService estadisticaPzNeuronormaService) {
        this.estadisticaPzNeuronormaService = estadisticaPzNeuronormaService;
    }

    /**
     * {@code POST  /estadistica-pz-neuronormas} : Create a new estadisticaPzNeuronorma.
     *
     * @param estadisticaPzNeuronormaDTO the estadisticaPzNeuronormaDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new estadisticaPzNeuronormaDTO, or with status {@code 400 (Bad Request)} if the estadisticaPzNeuronorma has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/estadistica-pz-neuronormas")
    public ResponseEntity<EstadisticaPzNeuronormaDTO> createEstadisticaPzNeuronorma(@Valid @RequestBody EstadisticaPzNeuronormaDTO estadisticaPzNeuronormaDTO) throws URISyntaxException {
        log.debug("REST request to save EstadisticaPzNeuronorma : {}", estadisticaPzNeuronormaDTO);
        if (estadisticaPzNeuronormaDTO.getId() != null) {
            throw new BadRequestAlertException("A new estadisticaPzNeuronorma cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EstadisticaPzNeuronormaDTO result = estadisticaPzNeuronormaService.save(estadisticaPzNeuronormaDTO);
        return ResponseEntity.created(new URI("/api/estadistica-pz-neuronormas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /estadistica-pz-neuronormas} : Updates an existing estadisticaPzNeuronorma.
     *
     * @param estadisticaPzNeuronormaDTO the estadisticaPzNeuronormaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated estadisticaPzNeuronormaDTO,
     * or with status {@code 400 (Bad Request)} if the estadisticaPzNeuronormaDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the estadisticaPzNeuronormaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/estadistica-pz-neuronormas")
    public ResponseEntity<EstadisticaPzNeuronormaDTO> updateEstadisticaPzNeuronorma(@Valid @RequestBody EstadisticaPzNeuronormaDTO estadisticaPzNeuronormaDTO) throws URISyntaxException {
        log.debug("REST request to update EstadisticaPzNeuronorma : {}", estadisticaPzNeuronormaDTO);
        if (estadisticaPzNeuronormaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EstadisticaPzNeuronormaDTO result = estadisticaPzNeuronormaService.save(estadisticaPzNeuronormaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, estadisticaPzNeuronormaDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /estadistica-pz-neuronormas} : get all the estadisticaPzNeuronormas.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of estadisticaPzNeuronormas in body.
     */
    @GetMapping("/estadistica-pz-neuronormas")
    public List<EstadisticaPzNeuronormaDTO> getAllEstadisticaPzNeuronormas() {
        log.debug("REST request to get all EstadisticaPzNeuronormas");
        return estadisticaPzNeuronormaService.findAll();
    }

    /**
     * {@code GET  /estadistica-pz-neuronormas/:id} : get the "id" estadisticaPzNeuronorma.
     *
     * @param id the id of the estadisticaPzNeuronormaDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the estadisticaPzNeuronormaDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/estadistica-pz-neuronormas/{id}")
    public ResponseEntity<EstadisticaPzNeuronormaDTO> getEstadisticaPzNeuronorma(@PathVariable Long id) {
        log.debug("REST request to get EstadisticaPzNeuronorma : {}", id);
        Optional<EstadisticaPzNeuronormaDTO> estadisticaPzNeuronormaDTO = estadisticaPzNeuronormaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(estadisticaPzNeuronormaDTO);
    }

    /**
     * {@code DELETE  /estadistica-pz-neuronormas/:id} : delete the "id" estadisticaPzNeuronorma.
     *
     * @param id the id of the estadisticaPzNeuronormaDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/estadistica-pz-neuronormas/{id}")
    public ResponseEntity<Void> deleteEstadisticaPzNeuronorma(@PathVariable Long id) {
        log.debug("REST request to delete EstadisticaPzNeuronorma : {}", id);
        estadisticaPzNeuronormaService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
