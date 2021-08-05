package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.EstadisticaTAVECService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.EstadisticaTAVECDTO;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.EstadisticaTAVEC}.
 */
@RestController
@RequestMapping("/api")
public class EstadisticaTAVECResource {

    private final Logger log = LoggerFactory.getLogger(EstadisticaTAVECResource.class);

    private static final String ENTITY_NAME = "estadisticaTAVEC";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EstadisticaTAVECService estadisticaTAVECService;

    public EstadisticaTAVECResource(EstadisticaTAVECService estadisticaTAVECService) {
        this.estadisticaTAVECService = estadisticaTAVECService;
    }

    /**
     * {@code POST  /estadistica-tavecs} : Create a new estadisticaTAVEC.
     *
     * @param estadisticaTAVECDTO the estadisticaTAVECDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new estadisticaTAVECDTO, or with status {@code 400 (Bad Request)} if the estadisticaTAVEC has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/estadistica-tavecs")
    public ResponseEntity<EstadisticaTAVECDTO> createEstadisticaTAVEC(@Valid @RequestBody EstadisticaTAVECDTO estadisticaTAVECDTO) throws URISyntaxException {
        log.debug("REST request to save EstadisticaTAVEC : {}", estadisticaTAVECDTO);
        if (estadisticaTAVECDTO.getId() != null) {
            throw new BadRequestAlertException("A new estadisticaTAVEC cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EstadisticaTAVECDTO result = estadisticaTAVECService.save(estadisticaTAVECDTO);
        return ResponseEntity.created(new URI("/api/estadistica-tavecs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /estadistica-tavecs} : Updates an existing estadisticaTAVEC.
     *
     * @param estadisticaTAVECDTO the estadisticaTAVECDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated estadisticaTAVECDTO,
     * or with status {@code 400 (Bad Request)} if the estadisticaTAVECDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the estadisticaTAVECDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/estadistica-tavecs")
    public ResponseEntity<EstadisticaTAVECDTO> updateEstadisticaTAVEC(@Valid @RequestBody EstadisticaTAVECDTO estadisticaTAVECDTO) throws URISyntaxException {
        log.debug("REST request to update EstadisticaTAVEC : {}", estadisticaTAVECDTO);
        if (estadisticaTAVECDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EstadisticaTAVECDTO result = estadisticaTAVECService.save(estadisticaTAVECDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, estadisticaTAVECDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /estadistica-tavecs} : get all the estadisticaTAVECS.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of estadisticaTAVECS in body.
     */
    @GetMapping("/estadistica-tavecs")
    public List<EstadisticaTAVECDTO> getAllEstadisticaTAVECS() {
        log.debug("REST request to get all EstadisticaTAVECS");
        return estadisticaTAVECService.findAll();
    }

    /**
     * {@code GET  /estadistica-tavecs/:id} : get the "id" estadisticaTAVEC.
     *
     * @param id the id of the estadisticaTAVECDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the estadisticaTAVECDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/estadistica-tavecs/{id}")
    public ResponseEntity<EstadisticaTAVECDTO> getEstadisticaTAVEC(@PathVariable Long id) {
        log.debug("REST request to get EstadisticaTAVEC : {}", id);
        Optional<EstadisticaTAVECDTO> estadisticaTAVECDTO = estadisticaTAVECService.findOne(id);
        return ResponseUtil.wrapOrNotFound(estadisticaTAVECDTO);
    }

    /**
     * {@code DELETE  /estadistica-tavecs/:id} : delete the "id" estadisticaTAVEC.
     *
     * @param id the id of the estadisticaTAVECDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/estadistica-tavecs/{id}")
    public ResponseEntity<Void> deleteEstadisticaTAVEC(@PathVariable Long id) {
        log.debug("REST request to delete EstadisticaTAVEC : {}", id);
        estadisticaTAVECService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
