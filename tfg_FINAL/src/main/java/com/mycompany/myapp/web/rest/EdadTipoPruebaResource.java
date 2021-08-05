package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.enumeration.TipoPrueba;
import com.mycompany.myapp.service.EdadTipoPruebaService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.EdadTipoPruebaDTO;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.EdadTipoPrueba}.
 */
@RestController
@RequestMapping("/api")
public class EdadTipoPruebaResource {

    private final Logger log = LoggerFactory.getLogger(EdadTipoPruebaResource.class);

    private static final String ENTITY_NAME = "edadTipoPrueba";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EdadTipoPruebaService edadTipoPruebaService;

    public EdadTipoPruebaResource(EdadTipoPruebaService edadTipoPruebaService) {
        this.edadTipoPruebaService = edadTipoPruebaService;
    }

    /**
     * {@code POST  /edad-tipo-pruebas} : Create a new edadTipoPrueba.
     *
     * @param edadTipoPruebaDTO the edadTipoPruebaDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new edadTipoPruebaDTO, or with status {@code 400 (Bad Request)} if the edadTipoPrueba has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/edad-tipo-pruebas")
    public ResponseEntity<EdadTipoPruebaDTO> createEdadTipoPrueba(@Valid @RequestBody EdadTipoPruebaDTO edadTipoPruebaDTO) throws URISyntaxException {
        log.debug("REST request to save EdadTipoPrueba : {}", edadTipoPruebaDTO);
        if (edadTipoPruebaDTO.getId() != null) {
            throw new BadRequestAlertException("A new edadTipoPrueba cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EdadTipoPruebaDTO result = edadTipoPruebaService.save(edadTipoPruebaDTO);
        return ResponseEntity.created(new URI("/api/edad-tipo-pruebas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /edad-tipo-pruebas} : Updates an existing edadTipoPrueba.
     *
     * @param edadTipoPruebaDTO the edadTipoPruebaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated edadTipoPruebaDTO,
     * or with status {@code 400 (Bad Request)} if the edadTipoPruebaDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the edadTipoPruebaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/edad-tipo-pruebas")
    public ResponseEntity<EdadTipoPruebaDTO> updateEdadTipoPrueba(@Valid @RequestBody EdadTipoPruebaDTO edadTipoPruebaDTO) throws URISyntaxException {
        log.debug("REST request to update EdadTipoPrueba : {}", edadTipoPruebaDTO);
        if (edadTipoPruebaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EdadTipoPruebaDTO result = edadTipoPruebaService.save(edadTipoPruebaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, edadTipoPruebaDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /edad-tipo-pruebas} : get all the edadTipoPruebas.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of edadTipoPruebas in body.
     */
    @GetMapping("/edad-tipo-pruebas")
    public List<EdadTipoPruebaDTO> getAllEdadTipoPruebas(@RequestParam(required = false) TipoPrueba tipoPrueba) {        
    	if(tipoPrueba == null) {
    		log.debug("REST request to get all EdadTipoPruebas");
            return edadTipoPruebaService.findAll();	
    	} else {
    		log.debug("REST request to get all EdadTipoPruebas by {}", tipoPrueba);
            return edadTipoPruebaService.findByTipoPrueba(tipoPrueba);
    	}
    }

    /**
     * {@code GET  /edad-tipo-pruebas/:id} : get the "id" edadTipoPrueba.
     *
     * @param id the id of the edadTipoPruebaDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the edadTipoPruebaDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/edad-tipo-pruebas/{id}")
    public ResponseEntity<EdadTipoPruebaDTO> getEdadTipoPrueba(@PathVariable Long id) {
        log.debug("REST request to get EdadTipoPrueba : {}", id);
        Optional<EdadTipoPruebaDTO> edadTipoPruebaDTO = edadTipoPruebaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(edadTipoPruebaDTO);
    }

    /**
     * {@code DELETE  /edad-tipo-pruebas/:id} : delete the "id" edadTipoPrueba.
     *
     * @param id the id of the edadTipoPruebaDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/edad-tipo-pruebas/{id}")
    public ResponseEntity<Void> deleteEdadTipoPrueba(@PathVariable Long id) {
        log.debug("REST request to delete EdadTipoPrueba : {}", id);
        edadTipoPruebaService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
