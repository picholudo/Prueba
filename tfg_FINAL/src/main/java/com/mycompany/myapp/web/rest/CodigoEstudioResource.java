package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.CodigoEstudioService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.CodigoEstudioDTO;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.CodigoEstudio}.
 */
@RestController
@RequestMapping("/api")
public class CodigoEstudioResource {

    private final Logger log = LoggerFactory.getLogger(CodigoEstudioResource.class);

    private static final String ENTITY_NAME = "codigoEstudio";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CodigoEstudioService codigoEstudioService;

    public CodigoEstudioResource(CodigoEstudioService codigoEstudioService) {
        this.codigoEstudioService = codigoEstudioService;
    }

    /**
     * {@code POST  /codigo-estudios} : Create a new codigoEstudio.
     *
     * @param codigoEstudioDTO the codigoEstudioDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new codigoEstudioDTO, or with status {@code 400 (Bad Request)} if the codigoEstudio has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/codigo-estudios")
    public ResponseEntity<CodigoEstudioDTO> createCodigoEstudio(@Valid @RequestBody CodigoEstudioDTO codigoEstudioDTO) throws URISyntaxException {
        log.debug("REST request to save CodigoEstudio : {}", codigoEstudioDTO);
        if (codigoEstudioDTO.getId() != null) {
            throw new BadRequestAlertException("A new codigoEstudio cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CodigoEstudioDTO result = codigoEstudioService.save(codigoEstudioDTO);
        return ResponseEntity.created(new URI("/api/codigo-estudios/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /codigo-estudios} : Updates an existing codigoEstudio.
     *
     * @param codigoEstudioDTO the codigoEstudioDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated codigoEstudioDTO,
     * or with status {@code 400 (Bad Request)} if the codigoEstudioDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the codigoEstudioDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/codigo-estudios")
    public ResponseEntity<CodigoEstudioDTO> updateCodigoEstudio(@Valid @RequestBody CodigoEstudioDTO codigoEstudioDTO) throws URISyntaxException {
        log.debug("REST request to update CodigoEstudio : {}", codigoEstudioDTO);
        if (codigoEstudioDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CodigoEstudioDTO result = codigoEstudioService.save(codigoEstudioDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, codigoEstudioDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /codigo-estudios} : get all the codigoEstudios.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of codigoEstudios in body.
     */
    @GetMapping("/codigo-estudios")
    public List<CodigoEstudioDTO> getAllCodigoEstudios() {
        log.debug("REST request to get all CodigoEstudios");
        return codigoEstudioService.findAll();
    }

    /**
     * {@code GET  /codigo-estudios/:id} : get the "id" codigoEstudio.
     *
     * @param id the id of the codigoEstudioDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the codigoEstudioDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/codigo-estudios/{id}")
    public ResponseEntity<CodigoEstudioDTO> getCodigoEstudio(@PathVariable Long id) {
        log.debug("REST request to get CodigoEstudio : {}", id);
        Optional<CodigoEstudioDTO> codigoEstudioDTO = codigoEstudioService.findOne(id);
        return ResponseUtil.wrapOrNotFound(codigoEstudioDTO);
    }

    /**
     * {@code DELETE  /codigo-estudios/:id} : delete the "id" codigoEstudio.
     *
     * @param id the id of the codigoEstudioDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/codigo-estudios/{id}")
    public ResponseEntity<Void> deleteCodigoEstudio(@PathVariable Long id) {
        log.debug("REST request to delete CodigoEstudio : {}", id);
        codigoEstudioService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
