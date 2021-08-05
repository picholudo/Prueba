package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.EstadisticaFABService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.EstadisticaFABDTO;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.EstadisticaFAB}.
 */
@RestController
@RequestMapping("/api")
public class EstadisticaFABResource {

    private final Logger log = LoggerFactory.getLogger(EstadisticaFABResource.class);

    private static final String ENTITY_NAME = "estadisticaFAB";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EstadisticaFABService estadisticaFABService;

    public EstadisticaFABResource(EstadisticaFABService estadisticaFABService) {
        this.estadisticaFABService = estadisticaFABService;
    }

    /**
     * {@code POST  /estadistica-fabs} : Create a new estadisticaFAB.
     *
     * @param estadisticaFABDTO the estadisticaFABDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new estadisticaFABDTO, or with status {@code 400 (Bad Request)} if the estadisticaFAB has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/estadistica-fabs")
    public ResponseEntity<EstadisticaFABDTO> createEstadisticaFAB(@Valid @RequestBody EstadisticaFABDTO estadisticaFABDTO) throws URISyntaxException {
        log.debug("REST request to save EstadisticaFAB : {}", estadisticaFABDTO);
        if (estadisticaFABDTO.getId() != null) {
            throw new BadRequestAlertException("A new estadisticaFAB cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EstadisticaFABDTO result = estadisticaFABService.save(estadisticaFABDTO);
        return ResponseEntity.created(new URI("/api/estadistica-fabs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /estadistica-fabs} : Updates an existing estadisticaFAB.
     *
     * @param estadisticaFABDTO the estadisticaFABDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated estadisticaFABDTO,
     * or with status {@code 400 (Bad Request)} if the estadisticaFABDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the estadisticaFABDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/estadistica-fabs")
    public ResponseEntity<EstadisticaFABDTO> updateEstadisticaFAB(@Valid @RequestBody EstadisticaFABDTO estadisticaFABDTO) throws URISyntaxException {
        log.debug("REST request to update EstadisticaFAB : {}", estadisticaFABDTO);
        if (estadisticaFABDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EstadisticaFABDTO result = estadisticaFABService.save(estadisticaFABDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, estadisticaFABDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /estadistica-fabs} : get all the estadisticaFABS.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of estadisticaFABS in body.
     */
    @GetMapping("/estadistica-fabs")
    public List<EstadisticaFABDTO> getAllEstadisticaFABS() {
        log.debug("REST request to get all EstadisticaFABS");
        return estadisticaFABService.findAll();
    }

    /**
     * {@code GET  /estadistica-fabs/:id} : get the "id" estadisticaFAB.
     *
     * @param id the id of the estadisticaFABDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the estadisticaFABDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/estadistica-fabs/{id}")
    public ResponseEntity<EstadisticaFABDTO> getEstadisticaFAB(@PathVariable Long id) {
        log.debug("REST request to get EstadisticaFAB : {}", id);
        Optional<EstadisticaFABDTO> estadisticaFABDTO = estadisticaFABService.findOne(id);
        return ResponseUtil.wrapOrNotFound(estadisticaFABDTO);
    }

    /**
     * {@code DELETE  /estadistica-fabs/:id} : delete the "id" estadisticaFAB.
     *
     * @param id the id of the estadisticaFABDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/estadistica-fabs/{id}")
    public ResponseEntity<Void> deleteEstadisticaFAB(@PathVariable Long id) {
        log.debug("REST request to delete EstadisticaFAB : {}", id);
        estadisticaFABService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
