package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.EvaluacionService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.EvaluacionDTO;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.Evaluacion}.
 */
@RestController
@RequestMapping("/api")
public class EvaluacionResource {

    private final Logger log = LoggerFactory.getLogger(EvaluacionResource.class);

    private static final String ENTITY_NAME = "evaluacion";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EvaluacionService evaluacionService;

    public EvaluacionResource(EvaluacionService evaluacionService) {
        this.evaluacionService = evaluacionService;
    }

    /**
     * {@code POST  /evaluacions} : Create a new evaluacion.
     *
     * @param evaluacionDTO the evaluacionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new evaluacionDTO, or with status {@code 400 (Bad Request)} if the evaluacion has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/evaluacions")
    public ResponseEntity<EvaluacionDTO> createEvaluacion(@Valid @RequestBody EvaluacionDTO evaluacionDTO) throws URISyntaxException {
        log.debug("REST request to save Evaluacion : {}", evaluacionDTO);
        if (evaluacionDTO.getId() != null) {
            throw new BadRequestAlertException("A new evaluacion cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EvaluacionDTO result = evaluacionService.save(evaluacionDTO);
        return ResponseEntity.created(new URI("/api/evaluacions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /evaluacions} : Updates an existing evaluacion.
     *
     * @param evaluacionDTO the evaluacionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated evaluacionDTO,
     * or with status {@code 400 (Bad Request)} if the evaluacionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the evaluacionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/evaluacions")
    public ResponseEntity<EvaluacionDTO> updateEvaluacion(@Valid @RequestBody EvaluacionDTO evaluacionDTO) throws URISyntaxException {
        log.debug("REST request to update Evaluacion : {}", evaluacionDTO);
        if (evaluacionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EvaluacionDTO result = evaluacionService.save(evaluacionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, evaluacionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /evaluacions} : get all the evaluacions.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of evaluacions in body.
     */
    @GetMapping("/evaluacions")
    public List<EvaluacionDTO> getAllEvaluacions() {
        log.debug("REST request to get all Evaluacions");
        return evaluacionService.findAll();
    }

    /**
     * {@code GET  /evaluacions/:id} : get the "id" evaluacion.
     *
     * @param id the id of the evaluacionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the evaluacionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/evaluacions/{id}")
    public ResponseEntity<EvaluacionDTO> getEvaluacion(@PathVariable Long id) {
        log.debug("REST request to get Evaluacion : {}", id);
        Optional<EvaluacionDTO> evaluacionDTO = evaluacionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(evaluacionDTO);
    }

    /**
     * {@code DELETE  /evaluacions/:id} : delete the "id" evaluacion.
     *
     * @param id the id of the evaluacionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/evaluacions/{id}")
    public ResponseEntity<Void> deleteEvaluacion(@PathVariable Long id) {
        log.debug("REST request to delete Evaluacion : {}", id);
        evaluacionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
