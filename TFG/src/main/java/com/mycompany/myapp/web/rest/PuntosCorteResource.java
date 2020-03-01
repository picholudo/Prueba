package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.security.SecurityUtils;
import com.mycompany.myapp.service.PuntosCorteService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.PuntosCorteDTO;

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

import static com.mycompany.myapp.security.AuthoritiesConstants.ADMIN;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.PuntosCorte}.
 */
@RestController
@RequestMapping("/api")
public class PuntosCorteResource {

    private final Logger log = LoggerFactory.getLogger(PuntosCorteResource.class);

    private static final String ENTITY_NAME = "puntosCorte";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PuntosCorteService puntosCorteService;

    public PuntosCorteResource(PuntosCorteService puntosCorteService) {
        this.puntosCorteService = puntosCorteService;
    }

    /**
     * {@code POST  /puntos-cortes} : Create a new puntosCorte.
     *
     * @param puntosCorteDTO the puntosCorteDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new puntosCorteDTO, or with status {@code 400 (Bad Request)} if the puntosCorte has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/puntos-cortes")
    public ResponseEntity<PuntosCorteDTO> createPuntosCorte(@Valid @RequestBody PuntosCorteDTO puntosCorteDTO) throws URISyntaxException {
        if (!SecurityUtils.isCurrentUserInRole(ADMIN)){
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(applicationName,true, ENTITY_NAME,"No autorizado", "No autorizado")).body(null);
        }
        log.debug("REST request to save PuntosCorte : {}", puntosCorteDTO);
        if (puntosCorteDTO.getId() != null) {
            throw new BadRequestAlertException("A new puntosCorte cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PuntosCorteDTO result = puntosCorteService.save(puntosCorteDTO);
        return ResponseEntity.created(new URI("/api/puntos-cortes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /puntos-cortes} : Updates an existing puntosCorte.
     *
     * @param puntosCorteDTO the puntosCorteDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated puntosCorteDTO,
     * or with status {@code 400 (Bad Request)} if the puntosCorteDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the puntosCorteDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/puntos-cortes")
    public ResponseEntity<PuntosCorteDTO> updatePuntosCorte(@Valid @RequestBody PuntosCorteDTO puntosCorteDTO) throws URISyntaxException {
        log.debug("REST request to update PuntosCorte : {}", puntosCorteDTO);
        if (puntosCorteDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PuntosCorteDTO result = puntosCorteService.save(puntosCorteDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, puntosCorteDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /puntos-cortes} : get all the puntosCortes.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of puntosCortes in body.
     */
    @GetMapping("/puntos-cortes")
    public List<PuntosCorteDTO> getAllPuntosCortes() {
        log.debug("REST request to get all PuntosCortes");
        return puntosCorteService.findAll();
    }

    /**
     * {@code GET  /puntos-cortes/:id} : get the "id" puntosCorte.
     *
     * @param id the id of the puntosCorteDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the puntosCorteDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/puntos-cortes/{id}")
    public ResponseEntity<PuntosCorteDTO> getPuntosCorte(@PathVariable Long id) {
        log.debug("REST request to get PuntosCorte : {}", id);
        Optional<PuntosCorteDTO> puntosCorteDTO = puntosCorteService.findOne(id);
        return ResponseUtil.wrapOrNotFound(puntosCorteDTO);
    }

    /**
     * {@code DELETE  /puntos-cortes/:id} : delete the "id" puntosCorte.
     *
     * @param id the id of the puntosCorteDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/puntos-cortes/{id}")
    public ResponseEntity<Void> deletePuntosCorte(@PathVariable Long id) {
        if (!SecurityUtils.isCurrentUserInRole(ADMIN)){
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(applicationName,true, ENTITY_NAME,"No autorizado", "No autorizado")).body(null);
        }

        log.debug("REST request to delete PuntosCorte : {}", id);
        puntosCorteService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

}
