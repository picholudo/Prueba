package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.enumeration.TipoPrueba;
import com.mycompany.myapp.service.PruebaService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.PruebaDTO;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.Prueba}.
 */
@RestController
@RequestMapping("/api")
public class PruebaResource {

    private final Logger log = LoggerFactory.getLogger(PruebaResource.class);

    private static final String ENTITY_NAME = "prueba";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PruebaService pruebaService;

    public PruebaResource(PruebaService pruebaService) {
        this.pruebaService = pruebaService;
    }

    /**
     * {@code POST  /pruebas} : Create a new prueba.
     *
     * @param pruebaDTO the pruebaDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new pruebaDTO, or with status {@code 400 (Bad Request)} if the prueba has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pruebas")
    public ResponseEntity<PruebaDTO> createPrueba(@Valid @RequestBody PruebaDTO pruebaDTO) throws URISyntaxException {
        log.debug("REST request to save Prueba : {}", pruebaDTO);
        if (pruebaDTO.getId() != null) {
            throw new BadRequestAlertException("A new prueba cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PruebaDTO result = pruebaService.save(pruebaDTO);
        return ResponseEntity.created(new URI("/api/pruebas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /pruebas} : Updates an existing prueba.
     *
     * @param pruebaDTO the pruebaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pruebaDTO,
     * or with status {@code 400 (Bad Request)} if the pruebaDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the pruebaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pruebas")
    public ResponseEntity<PruebaDTO> updatePrueba(@Valid @RequestBody PruebaDTO pruebaDTO) throws URISyntaxException {
        log.debug("REST request to update Prueba : {}", pruebaDTO);
        if (pruebaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PruebaDTO result = pruebaService.save(pruebaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, pruebaDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /pruebas} : get all the pruebas.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of pruebas in body.
     */
    @GetMapping("/pruebas")
    public List<PruebaDTO> getAllPruebas(@RequestParam(required = false) TipoPrueba tipoPrueba) {
    	if(tipoPrueba == null) {
    		log.debug("REST request to get all Pruebas");
            return pruebaService.findAll();	
    	} else {
    		log.debug("REST request to get all Pruebas by {}", tipoPrueba);
            return pruebaService.findByTipoPrueba(tipoPrueba);
    	}        
    }

    /**
     * {@code GET  /pruebas/:id} : get the "id" prueba.
     *
     * @param id the id of the pruebaDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the pruebaDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pruebas/{id}")
    public ResponseEntity<PruebaDTO> getPrueba(@PathVariable Long id) {
        log.debug("REST request to get Prueba : {}", id);
        Optional<PruebaDTO> pruebaDTO = pruebaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(pruebaDTO);
    }

    /**
     * {@code DELETE  /pruebas/:id} : delete the "id" prueba.
     *
     * @param id the id of the pruebaDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pruebas/{id}")
    public ResponseEntity<Void> deletePrueba(@PathVariable Long id) {
        log.debug("REST request to delete Prueba : {}", id);
        pruebaService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
