package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.EstadisticaAjusteNeuronormaService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.EstadisticaAjusteNeuronormaDTO;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.EstadisticaAjusteNeuronorma}.
 */
@RestController
@RequestMapping("/api")
public class EstadisticaAjusteNeuronormaResource {

    private final Logger log = LoggerFactory.getLogger(EstadisticaAjusteNeuronormaResource.class);

    private static final String ENTITY_NAME = "estadisticaAjusteNeuronorma";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EstadisticaAjusteNeuronormaService estadisticaAjusteNeuronormaService;

    public EstadisticaAjusteNeuronormaResource(EstadisticaAjusteNeuronormaService estadisticaAjusteNeuronormaService) {
        this.estadisticaAjusteNeuronormaService = estadisticaAjusteNeuronormaService;
    }

    /**
     * {@code POST  /estadistica-ajuste-neuronormas} : Create a new estadisticaAjusteNeuronorma.
     *
     * @param estadisticaAjusteNeuronormaDTO the estadisticaAjusteNeuronormaDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new estadisticaAjusteNeuronormaDTO, or with status {@code 400 (Bad Request)} if the estadisticaAjusteNeuronorma has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/estadistica-ajuste-neuronormas")
    public ResponseEntity<EstadisticaAjusteNeuronormaDTO> createEstadisticaAjusteNeuronorma(@Valid @RequestBody EstadisticaAjusteNeuronormaDTO estadisticaAjusteNeuronormaDTO) throws URISyntaxException {
        log.debug("REST request to save EstadisticaAjusteNeuronorma : {}", estadisticaAjusteNeuronormaDTO);
        if (estadisticaAjusteNeuronormaDTO.getId() != null) {
            throw new BadRequestAlertException("A new estadisticaAjusteNeuronorma cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EstadisticaAjusteNeuronormaDTO result = estadisticaAjusteNeuronormaService.save(estadisticaAjusteNeuronormaDTO);
        return ResponseEntity.created(new URI("/api/estadistica-ajuste-neuronormas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /estadistica-ajuste-neuronormas} : Updates an existing estadisticaAjusteNeuronorma.
     *
     * @param estadisticaAjusteNeuronormaDTO the estadisticaAjusteNeuronormaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated estadisticaAjusteNeuronormaDTO,
     * or with status {@code 400 (Bad Request)} if the estadisticaAjusteNeuronormaDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the estadisticaAjusteNeuronormaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/estadistica-ajuste-neuronormas")
    public ResponseEntity<EstadisticaAjusteNeuronormaDTO> updateEstadisticaAjusteNeuronorma(@Valid @RequestBody EstadisticaAjusteNeuronormaDTO estadisticaAjusteNeuronormaDTO) throws URISyntaxException {
        log.debug("REST request to update EstadisticaAjusteNeuronorma : {}", estadisticaAjusteNeuronormaDTO);
        if (estadisticaAjusteNeuronormaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EstadisticaAjusteNeuronormaDTO result = estadisticaAjusteNeuronormaService.save(estadisticaAjusteNeuronormaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, estadisticaAjusteNeuronormaDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /estadistica-ajuste-neuronormas} : get all the estadisticaAjusteNeuronormas.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of estadisticaAjusteNeuronormas in body.
     */
    @GetMapping("/estadistica-ajuste-neuronormas")
    public List<EstadisticaAjusteNeuronormaDTO> getAllEstadisticaAjusteNeuronormas() {
        log.debug("REST request to get all EstadisticaAjusteNeuronormas");
        return estadisticaAjusteNeuronormaService.findAll();
    }

    /**
     * {@code GET  /estadistica-ajuste-neuronormas/:id} : get the "id" estadisticaAjusteNeuronorma.
     *
     * @param id the id of the estadisticaAjusteNeuronormaDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the estadisticaAjusteNeuronormaDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/estadistica-ajuste-neuronormas/{id}")
    public ResponseEntity<EstadisticaAjusteNeuronormaDTO> getEstadisticaAjusteNeuronorma(@PathVariable Long id) {
        log.debug("REST request to get EstadisticaAjusteNeuronorma : {}", id);
        Optional<EstadisticaAjusteNeuronormaDTO> estadisticaAjusteNeuronormaDTO = estadisticaAjusteNeuronormaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(estadisticaAjusteNeuronormaDTO);
    }

    /**
     * {@code DELETE  /estadistica-ajuste-neuronormas/:id} : delete the "id" estadisticaAjusteNeuronorma.
     *
     * @param id the id of the estadisticaAjusteNeuronormaDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/estadistica-ajuste-neuronormas/{id}")
    public ResponseEntity<Void> deleteEstadisticaAjusteNeuronorma(@PathVariable Long id) {
        log.debug("REST request to delete EstadisticaAjusteNeuronorma : {}", id);
        estadisticaAjusteNeuronormaService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
