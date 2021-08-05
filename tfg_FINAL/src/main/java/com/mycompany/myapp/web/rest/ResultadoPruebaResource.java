package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.CalculadorResultadoPruebaException;
import com.mycompany.myapp.service.ResultadoPruebaService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.ResultadoPruebaDTO;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.ResultadoPrueba}.
 */
@RestController
@RequestMapping("/api")
public class ResultadoPruebaResource {

    private final Logger log = LoggerFactory.getLogger(ResultadoPruebaResource.class);

    private static final String ENTITY_NAME = "resultadoPrueba";
    private static final String ERROR_CALCULATING = "could.not.calculate";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ResultadoPruebaService resultadoPruebaService;

    public ResultadoPruebaResource(ResultadoPruebaService resultadoPruebaService) {
        this.resultadoPruebaService = resultadoPruebaService;
    }

    /**
     * {@code POST  /resultado-pruebas} : Create a new resultadoPrueba.
     *
     * @param resultadoPruebaDTO the resultadoPruebaDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new resultadoPruebaDTO, or with status {@code 400 (Bad Request)} if the resultadoPrueba has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/resultado-pruebas")
    public ResponseEntity<ResultadoPruebaDTO> createResultadoPrueba(@Valid @RequestBody ResultadoPruebaDTO resultadoPruebaDTO) throws URISyntaxException {
        log.debug("REST request to save ResultadoPrueba : {}", resultadoPruebaDTO);
        if (resultadoPruebaDTO.getId() != null) {
            throw new BadRequestAlertException("A new resultadoPrueba cannot already have an ID", ENTITY_NAME, "idexists");
        }

        ResultadoPruebaDTO result;
        try {
        	result = resultadoPruebaService.save(resultadoPruebaDTO);
        } catch(CalculadorResultadoPruebaException e) {
        	log.error("Error while trying to calculate ResultadoPrueba", e);
        	throw new BadRequestAlertException(e.getMessage(), ENTITY_NAME, ERROR_CALCULATING);
        }
        
        return ResponseEntity.created(new URI("/api/resultado-pruebas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /resultado-pruebas} : Updates an existing resultadoPrueba.
     *
     * @param resultadoPruebaDTO the resultadoPruebaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated resultadoPruebaDTO,
     * or with status {@code 400 (Bad Request)} if the resultadoPruebaDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the resultadoPruebaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/resultado-pruebas")
    public ResponseEntity<ResultadoPruebaDTO> updateResultadoPrueba(@Valid @RequestBody ResultadoPruebaDTO resultadoPruebaDTO) throws URISyntaxException {
        log.debug("REST request to update ResultadoPrueba : {}", resultadoPruebaDTO);
        if (resultadoPruebaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        
        ResultadoPruebaDTO result;
        try {
        	result = resultadoPruebaService.save(resultadoPruebaDTO);
        } catch(CalculadorResultadoPruebaException e) {
        	log.error("Error while trying to calculate ResultadoPrueba", e);
        	throw new BadRequestAlertException(e.getMessage(), ENTITY_NAME, ERROR_CALCULATING);
        }
        
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, resultadoPruebaDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /resultado-pruebas} : get all the resultadoPruebas.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of resultadoPruebas in body.
     */
    @GetMapping("/resultado-pruebas")
    public List<ResultadoPruebaDTO> getAllResultadoPruebas(@RequestParam(required = false) Long informeId) {
    	if(informeId == null) {
    		log.debug("REST request to get all ResultadoPruebas");
            return resultadoPruebaService.findAll();	
    	} else {
    		log.debug("REST request to get all ResultadoPruebas by Informed ID: {}", informeId);
            return resultadoPruebaService.findByInformeId(informeId);
    	}        
    }

    /**
     * {@code GET  /resultado-pruebas/:id} : get the "id" resultadoPrueba.
     *
     * @param id the id of the resultadoPruebaDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the resultadoPruebaDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/resultado-pruebas/{id}")
    public ResponseEntity<ResultadoPruebaDTO> getResultadoPrueba(@PathVariable Long id) {
        log.debug("REST request to get ResultadoPrueba : {}", id);
        Optional<ResultadoPruebaDTO> resultadoPruebaDTO = resultadoPruebaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(resultadoPruebaDTO);
    }

    /**
     * {@code DELETE  /resultado-pruebas/:id} : delete the "id" resultadoPrueba.
     *
     * @param id the id of the resultadoPruebaDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/resultado-pruebas/{id}")
    public ResponseEntity<Void> deleteResultadoPrueba(@PathVariable Long id) {
        log.debug("REST request to delete ResultadoPrueba : {}", id);
        resultadoPruebaService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
