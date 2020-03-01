package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.PuntuacionPruebaService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.PuntuacionPruebaDTO;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.PuntuacionPrueba}.
 */
@RestController
@RequestMapping("/api")
public class PuntuacionPruebaResource {

    private final Logger log = LoggerFactory.getLogger(PuntuacionPruebaResource.class);

    private static final String ENTITY_NAME = "puntuacionPrueba";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PuntuacionPruebaService puntuacionPruebaService;

    public PuntuacionPruebaResource(PuntuacionPruebaService puntuacionPruebaService) {
        this.puntuacionPruebaService = puntuacionPruebaService;
    }

    /**
     * {@code POST  /puntuacion-pruebas} : Create a new puntuacionPrueba.
     *
     * @param puntuacionPruebaDTO the puntuacionPruebaDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new puntuacionPruebaDTO, or with status {@code 400 (Bad Request)} if the puntuacionPrueba has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/puntuacion-pruebas")
    public ResponseEntity<PuntuacionPruebaDTO> createPuntuacionPrueba(@Valid @RequestBody PuntuacionPruebaDTO puntuacionPruebaDTO) throws URISyntaxException {
        log.debug("REST request to save PuntuacionPrueba : {}", puntuacionPruebaDTO);
        if (puntuacionPruebaDTO.getId() != null) {
            throw new BadRequestAlertException("A new puntuacionPrueba cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PuntuacionPruebaDTO result = puntuacionPruebaService.save(puntuacionPruebaDTO);
        return ResponseEntity.created(new URI("/api/puntuacion-pruebas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /puntuacion-pruebas} : Updates an existing puntuacionPrueba.
     *
     * @param puntuacionPruebaDTO the puntuacionPruebaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated puntuacionPruebaDTO,
     * or with status {@code 400 (Bad Request)} if the puntuacionPruebaDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the puntuacionPruebaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/puntuacion-pruebas")
    public ResponseEntity<PuntuacionPruebaDTO> updatePuntuacionPrueba(@Valid @RequestBody PuntuacionPruebaDTO puntuacionPruebaDTO) throws URISyntaxException {
        log.debug("REST request to update PuntuacionPrueba : {}", puntuacionPruebaDTO);
        if (puntuacionPruebaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PuntuacionPruebaDTO result = puntuacionPruebaService.save(puntuacionPruebaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, puntuacionPruebaDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /puntuacion-pruebas} : get all the puntuacionPruebas.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of puntuacionPruebas in body.
     */
    @GetMapping("/puntuacion-pruebas")
    public List<PuntuacionPruebaDTO> getAllPuntuacionPruebas() {
        log.debug("REST request to get all PuntuacionPruebas");
        return puntuacionPruebaService.findAll();
    }

    /**
     * {@code GET  /puntuacion-pruebas/:id} : get the "id" puntuacionPrueba.
     *
     * @param id the id of the puntuacionPruebaDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the puntuacionPruebaDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/puntuacion-pruebas/{id}")
    public ResponseEntity<PuntuacionPruebaDTO> getPuntuacionPrueba(@PathVariable Long id) {
        log.debug("REST request to get PuntuacionPrueba : {}", id);
        Optional<PuntuacionPruebaDTO> puntuacionPruebaDTO = puntuacionPruebaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(puntuacionPruebaDTO);
    }

    /**
     * {@code DELETE  /puntuacion-pruebas/:id} : delete the "id" puntuacionPrueba.
     *
     * @param id the id of the puntuacionPruebaDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/puntuacion-pruebas/{id}")
    public ResponseEntity<Void> deletePuntuacionPrueba(@PathVariable Long id) {
        log.debug("REST request to delete PuntuacionPrueba : {}", id);
        puntuacionPruebaService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
