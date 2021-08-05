package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.EstadisticaTBAService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.EstadisticaTBADTO;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.EstadisticaTBA}.
 */
@RestController
@RequestMapping("/api")
public class EstadisticaTBAResource {

    private final Logger log = LoggerFactory.getLogger(EstadisticaTBAResource.class);

    private static final String ENTITY_NAME = "estadisticaTBA";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EstadisticaTBAService estadisticaTBAService;

    public EstadisticaTBAResource(EstadisticaTBAService estadisticaTBAService) {
        this.estadisticaTBAService = estadisticaTBAService;
    }

    /**
     * {@code POST  /estadistica-tbas} : Create a new estadisticaTBA.
     *
     * @param estadisticaTBADTO the estadisticaTBADTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new estadisticaTBADTO, or with status {@code 400 (Bad Request)} if the estadisticaTBA has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/estadistica-tbas")
    public ResponseEntity<EstadisticaTBADTO> createEstadisticaTBA(@Valid @RequestBody EstadisticaTBADTO estadisticaTBADTO) throws URISyntaxException {
        log.debug("REST request to save EstadisticaTBA : {}", estadisticaTBADTO);
        if (estadisticaTBADTO.getId() != null) {
            throw new BadRequestAlertException("A new estadisticaTBA cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EstadisticaTBADTO result = estadisticaTBAService.save(estadisticaTBADTO);
        return ResponseEntity.created(new URI("/api/estadistica-tbas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /estadistica-tbas} : Updates an existing estadisticaTBA.
     *
     * @param estadisticaTBADTO the estadisticaTBADTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated estadisticaTBADTO,
     * or with status {@code 400 (Bad Request)} if the estadisticaTBADTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the estadisticaTBADTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/estadistica-tbas")
    public ResponseEntity<EstadisticaTBADTO> updateEstadisticaTBA(@Valid @RequestBody EstadisticaTBADTO estadisticaTBADTO) throws URISyntaxException {
        log.debug("REST request to update EstadisticaTBA : {}", estadisticaTBADTO);
        if (estadisticaTBADTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EstadisticaTBADTO result = estadisticaTBAService.save(estadisticaTBADTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, estadisticaTBADTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /estadistica-tbas} : get all the estadisticaTBAS.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of estadisticaTBAS in body.
     */
    @GetMapping("/estadistica-tbas")
    public List<EstadisticaTBADTO> getAllEstadisticaTBAS() {
        log.debug("REST request to get all EstadisticaTBAS");
        return estadisticaTBAService.findAll();
    }

    /**
     * {@code GET  /estadistica-tbas/:id} : get the "id" estadisticaTBA.
     *
     * @param id the id of the estadisticaTBADTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the estadisticaTBADTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/estadistica-tbas/{id}")
    public ResponseEntity<EstadisticaTBADTO> getEstadisticaTBA(@PathVariable Long id) {
        log.debug("REST request to get EstadisticaTBA : {}", id);
        Optional<EstadisticaTBADTO> estadisticaTBADTO = estadisticaTBAService.findOne(id);
        return ResponseUtil.wrapOrNotFound(estadisticaTBADTO);
    }

    /**
     * {@code DELETE  /estadistica-tbas/:id} : delete the "id" estadisticaTBA.
     *
     * @param id the id of the estadisticaTBADTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/estadistica-tbas/{id}")
    public ResponseEntity<Void> deleteEstadisticaTBA(@PathVariable Long id) {
        log.debug("REST request to delete EstadisticaTBA : {}", id);
        estadisticaTBAService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
