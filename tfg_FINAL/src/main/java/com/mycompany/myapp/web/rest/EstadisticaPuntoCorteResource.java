package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.EstadisticaPuntoCorteService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.EstadisticaPuntoCorteDTO;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.EstadisticaPuntoCorte}.
 */
@RestController
@RequestMapping("/api")
public class EstadisticaPuntoCorteResource {

    private final Logger log = LoggerFactory.getLogger(EstadisticaPuntoCorteResource.class);

    private static final String ENTITY_NAME = "estadisticaPuntoCorte";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EstadisticaPuntoCorteService estadisticaPuntoCorteService;

    public EstadisticaPuntoCorteResource(EstadisticaPuntoCorteService estadisticaPuntoCorteService) {
        this.estadisticaPuntoCorteService = estadisticaPuntoCorteService;
    }

    /**
     * {@code POST  /estadistica-punto-cortes} : Create a new estadisticaPuntoCorte.
     *
     * @param estadisticaPuntoCorteDTO the estadisticaPuntoCorteDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new estadisticaPuntoCorteDTO, or with status {@code 400 (Bad Request)} if the estadisticaPuntoCorte has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/estadistica-punto-cortes")
    public ResponseEntity<EstadisticaPuntoCorteDTO> createEstadisticaPuntoCorte(@Valid @RequestBody EstadisticaPuntoCorteDTO estadisticaPuntoCorteDTO) throws URISyntaxException {
        log.debug("REST request to save EstadisticaPuntoCorte : {}", estadisticaPuntoCorteDTO);
        if (estadisticaPuntoCorteDTO.getId() != null) {
            throw new BadRequestAlertException("A new estadisticaPuntoCorte cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EstadisticaPuntoCorteDTO result = estadisticaPuntoCorteService.save(estadisticaPuntoCorteDTO);
        return ResponseEntity.created(new URI("/api/estadistica-punto-cortes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /estadistica-punto-cortes} : Updates an existing estadisticaPuntoCorte.
     *
     * @param estadisticaPuntoCorteDTO the estadisticaPuntoCorteDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated estadisticaPuntoCorteDTO,
     * or with status {@code 400 (Bad Request)} if the estadisticaPuntoCorteDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the estadisticaPuntoCorteDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/estadistica-punto-cortes")
    public ResponseEntity<EstadisticaPuntoCorteDTO> updateEstadisticaPuntoCorte(@Valid @RequestBody EstadisticaPuntoCorteDTO estadisticaPuntoCorteDTO) throws URISyntaxException {
        log.debug("REST request to update EstadisticaPuntoCorte : {}", estadisticaPuntoCorteDTO);
        if (estadisticaPuntoCorteDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EstadisticaPuntoCorteDTO result = estadisticaPuntoCorteService.save(estadisticaPuntoCorteDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, estadisticaPuntoCorteDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /estadistica-punto-cortes} : get all the estadisticaPuntoCortes.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of estadisticaPuntoCortes in body.
     */
    @GetMapping("/estadistica-punto-cortes")
    public List<EstadisticaPuntoCorteDTO> getAllEstadisticaPuntoCortes() {
        log.debug("REST request to get all EstadisticaPuntoCortes");
        return estadisticaPuntoCorteService.findAll();
    }

    /**
     * {@code GET  /estadistica-punto-cortes/:id} : get the "id" estadisticaPuntoCorte.
     *
     * @param id the id of the estadisticaPuntoCorteDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the estadisticaPuntoCorteDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/estadistica-punto-cortes/{id}")
    public ResponseEntity<EstadisticaPuntoCorteDTO> getEstadisticaPuntoCorte(@PathVariable Long id) {
        log.debug("REST request to get EstadisticaPuntoCorte : {}", id);
        Optional<EstadisticaPuntoCorteDTO> estadisticaPuntoCorteDTO = estadisticaPuntoCorteService.findOne(id);
        return ResponseUtil.wrapOrNotFound(estadisticaPuntoCorteDTO);
    }

    /**
     * {@code DELETE  /estadistica-punto-cortes/:id} : delete the "id" estadisticaPuntoCorte.
     *
     * @param id the id of the estadisticaPuntoCorteDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/estadistica-punto-cortes/{id}")
    public ResponseEntity<Void> deleteEstadisticaPuntoCorte(@PathVariable Long id) {
        log.debug("REST request to delete EstadisticaPuntoCorte : {}", id);
        estadisticaPuntoCorteService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
