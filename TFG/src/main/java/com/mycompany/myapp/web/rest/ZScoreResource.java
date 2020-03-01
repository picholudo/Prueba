package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.ZScoreService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.ZScoreDTO;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.ZScore}.
 */
@RestController
@RequestMapping("/api")
public class ZScoreResource {

    private final Logger log = LoggerFactory.getLogger(ZScoreResource.class);

    private static final String ENTITY_NAME = "zScore";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ZScoreService zScoreService;

    public ZScoreResource(ZScoreService zScoreService) {
        this.zScoreService = zScoreService;
    }

    /**
     * {@code POST  /z-scores} : Create a new zScore.
     *
     * @param zScoreDTO the zScoreDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new zScoreDTO, or with status {@code 400 (Bad Request)} if the zScore has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/z-scores")
    public ResponseEntity<ZScoreDTO> createZScore(@Valid @RequestBody ZScoreDTO zScoreDTO) throws URISyntaxException {
        log.debug("REST request to save ZScore : {}", zScoreDTO);
        if (zScoreDTO.getId() != null) {
            throw new BadRequestAlertException("A new zScore cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ZScoreDTO result = zScoreService.save(zScoreDTO);
        return ResponseEntity.created(new URI("/api/z-scores/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /z-scores} : Updates an existing zScore.
     *
     * @param zScoreDTO the zScoreDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated zScoreDTO,
     * or with status {@code 400 (Bad Request)} if the zScoreDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the zScoreDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/z-scores")
    public ResponseEntity<ZScoreDTO> updateZScore(@Valid @RequestBody ZScoreDTO zScoreDTO) throws URISyntaxException {
        log.debug("REST request to update ZScore : {}", zScoreDTO);
        if (zScoreDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ZScoreDTO result = zScoreService.save(zScoreDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, zScoreDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /z-scores} : get all the zScores.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of zScores in body.
     */
    @GetMapping("/z-scores")
    public List<ZScoreDTO> getAllZScores() {
        log.debug("REST request to get all ZScores");
        return zScoreService.findAll();
    }

    /**
     * {@code GET  /z-scores/:id} : get the "id" zScore.
     *
     * @param id the id of the zScoreDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the zScoreDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/z-scores/{id}")
    public ResponseEntity<ZScoreDTO> getZScore(@PathVariable Long id) {
        log.debug("REST request to get ZScore : {}", id);
        Optional<ZScoreDTO> zScoreDTO = zScoreService.findOne(id);
        return ResponseUtil.wrapOrNotFound(zScoreDTO);
    }

    /**
     * {@code DELETE  /z-scores/:id} : delete the "id" zScore.
     *
     * @param id the id of the zScoreDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/z-scores/{id}")
    public ResponseEntity<Void> deleteZScore(@PathVariable Long id) {
        log.debug("REST request to delete ZScore : {}", id);
        zScoreService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
