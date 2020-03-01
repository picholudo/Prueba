package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.EstadisticasService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.EstadisticasDTO;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.Estadisticas}.
 */
@RestController
@RequestMapping("/api")
public class EstadisticasResource {

    private final Logger log = LoggerFactory.getLogger(EstadisticasResource.class);

    private static final String ENTITY_NAME = "estadisticas";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EstadisticasService estadisticasService;

    public EstadisticasResource(EstadisticasService estadisticasService) {
        this.estadisticasService = estadisticasService;
    }

    /**
     * {@code POST  /estadisticas} : Create a new estadisticas.
     *
     * @param estadisticasDTO the estadisticasDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new estadisticasDTO, or with status {@code 400 (Bad Request)} if the estadisticas has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/estadisticas")
    public ResponseEntity<EstadisticasDTO> createEstadisticas(@Valid @RequestBody EstadisticasDTO estadisticasDTO) throws URISyntaxException {
        log.debug("REST request to save Estadisticas : {}", estadisticasDTO);
        if (estadisticasDTO.getId() != null) {
            throw new BadRequestAlertException("A new estadisticas cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EstadisticasDTO result = estadisticasService.save(estadisticasDTO);
        return ResponseEntity.created(new URI("/api/estadisticas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /estadisticas} : Updates an existing estadisticas.
     *
     * @param estadisticasDTO the estadisticasDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated estadisticasDTO,
     * or with status {@code 400 (Bad Request)} if the estadisticasDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the estadisticasDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/estadisticas")
    public ResponseEntity<EstadisticasDTO> updateEstadisticas(@Valid @RequestBody EstadisticasDTO estadisticasDTO) throws URISyntaxException {
        log.debug("REST request to update Estadisticas : {}", estadisticasDTO);
        if (estadisticasDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EstadisticasDTO result = estadisticasService.save(estadisticasDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, estadisticasDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /estadisticas} : get all the estadisticas.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of estadisticas in body.
     */
    @GetMapping("/estadisticas")
    public List<EstadisticasDTO> getAllEstadisticas() {
        log.debug("REST request to get all Estadisticas");
        return estadisticasService.findAll();
    }

    /**
     * {@code GET  /estadisticas/:id} : get the "id" estadisticas.
     *
     * @param id the id of the estadisticasDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the estadisticasDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/estadisticas/{id}")
    public ResponseEntity<EstadisticasDTO> getEstadisticas(@PathVariable Long id) {
        log.debug("REST request to get Estadisticas : {}", id);
        Optional<EstadisticasDTO> estadisticasDTO = estadisticasService.findOne(id);
        return ResponseUtil.wrapOrNotFound(estadisticasDTO);
    }

    /**
     * {@code DELETE  /estadisticas/:id} : delete the "id" estadisticas.
     *
     * @param id the id of the estadisticasDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/estadisticas/{id}")
    public ResponseEntity<Void> deleteEstadisticas(@PathVariable Long id) {
        log.debug("REST request to delete Estadisticas : {}", id);
        estadisticasService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
