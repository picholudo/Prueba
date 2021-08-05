package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.EstadisticaMMSEService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.EstadisticaMMSEDTO;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.EstadisticaMMSE}.
 */
@RestController
@RequestMapping("/api")
public class EstadisticaMMSEResource {

    private final Logger log = LoggerFactory.getLogger(EstadisticaMMSEResource.class);

    private static final String ENTITY_NAME = "estadisticaMMSE";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EstadisticaMMSEService estadisticaMMSEService;

    public EstadisticaMMSEResource(EstadisticaMMSEService estadisticaMMSEService) {
        this.estadisticaMMSEService = estadisticaMMSEService;
    }

    /**
     * {@code POST  /estadistica-mmses} : Create a new estadisticaMMSE.
     *
     * @param estadisticaMMSEDTO the estadisticaMMSEDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new estadisticaMMSEDTO, or with status {@code 400 (Bad Request)} if the estadisticaMMSE has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/estadistica-mmses")
    public ResponseEntity<EstadisticaMMSEDTO> createEstadisticaMMSE(@Valid @RequestBody EstadisticaMMSEDTO estadisticaMMSEDTO) throws URISyntaxException {
        log.debug("REST request to save EstadisticaMMSE : {}", estadisticaMMSEDTO);
        if (estadisticaMMSEDTO.getId() != null) {
            throw new BadRequestAlertException("A new estadisticaMMSE cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EstadisticaMMSEDTO result = estadisticaMMSEService.save(estadisticaMMSEDTO);
        return ResponseEntity.created(new URI("/api/estadistica-mmses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /estadistica-mmses} : Updates an existing estadisticaMMSE.
     *
     * @param estadisticaMMSEDTO the estadisticaMMSEDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated estadisticaMMSEDTO,
     * or with status {@code 400 (Bad Request)} if the estadisticaMMSEDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the estadisticaMMSEDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/estadistica-mmses")
    public ResponseEntity<EstadisticaMMSEDTO> updateEstadisticaMMSE(@Valid @RequestBody EstadisticaMMSEDTO estadisticaMMSEDTO) throws URISyntaxException {
        log.debug("REST request to update EstadisticaMMSE : {}", estadisticaMMSEDTO);
        if (estadisticaMMSEDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EstadisticaMMSEDTO result = estadisticaMMSEService.save(estadisticaMMSEDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, estadisticaMMSEDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /estadistica-mmses} : get all the estadisticaMMSES.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of estadisticaMMSES in body.
     */
    @GetMapping("/estadistica-mmses")
    public List<EstadisticaMMSEDTO> getAllEstadisticaMMSES() {
        log.debug("REST request to get all EstadisticaMMSES");
        return estadisticaMMSEService.findAll();
    }

    /**
     * {@code GET  /estadistica-mmses/:id} : get the "id" estadisticaMMSE.
     *
     * @param id the id of the estadisticaMMSEDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the estadisticaMMSEDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/estadistica-mmses/{id}")
    public ResponseEntity<EstadisticaMMSEDTO> getEstadisticaMMSE(@PathVariable Long id) {
        log.debug("REST request to get EstadisticaMMSE : {}", id);
        Optional<EstadisticaMMSEDTO> estadisticaMMSEDTO = estadisticaMMSEService.findOne(id);
        return ResponseUtil.wrapOrNotFound(estadisticaMMSEDTO);
    }

    /**
     * {@code DELETE  /estadistica-mmses/:id} : delete the "id" estadisticaMMSE.
     *
     * @param id the id of the estadisticaMMSEDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/estadistica-mmses/{id}")
    public ResponseEntity<Void> deleteEstadisticaMMSE(@PathVariable Long id) {
        log.debug("REST request to delete EstadisticaMMSE : {}", id);
        estadisticaMMSEService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
