package com.mycompany.myapp.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.mycompany.myapp.domain.naiveBayesDiagnosis;
import com.mycompany.myapp.service.InformeService;
import com.mycompany.myapp.service.dto.InformeDTO;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.Informe}.
 */
@RestController
@RequestMapping("/api")
public class InformeResource {

    private final Logger log = LoggerFactory.getLogger(InformeResource.class);

    private static final String ENTITY_NAME = "informe";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InformeService informeService;

    public InformeResource(InformeService informeService) {
        this.informeService = informeService;
    }

    /**
     * {@code POST  /informes} : Create a new informe.
     *
     * @param informeDTO the informeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new informeDTO, or with status {@code 400 (Bad Request)} if the informe has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/informes")
    public ResponseEntity<InformeDTO> createInforme(@Valid @RequestBody InformeDTO informeDTO) throws URISyntaxException {
        log.debug("REST request to save Informe : {}", informeDTO);
        if (informeDTO.getId() != null) {
            throw new BadRequestAlertException("A new informe cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InformeDTO result = informeService.save(informeDTO);
        return ResponseEntity.created(new URI("/api/informes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /informes} : Updates an existing informe.
     *
     * @param informeDTO the informeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated informeDTO,
     * or with status {@code 400 (Bad Request)} if the informeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the informeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/informes")
    public ResponseEntity<InformeDTO> updateInforme(@Valid @RequestBody InformeDTO informeDTO) throws URISyntaxException {
        log.debug("REST request to update Informe : {}", informeDTO);
        if (informeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        InformeDTO result = informeService.save(informeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, informeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /informes} : get all the informes.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of informes in body.
     */
    @GetMapping("/informes")
    public List<InformeDTO> getAllInformes(@RequestParam(required = false) Long pacienteId) {
    	if(pacienteId == null) {
    		log.debug("REST request to get all Informes");
            return informeService.findAll();
    	} else {
    		log.debug("REST request to get all Informes by Informed ID: {}", pacienteId);
            return informeService.findByPacienteId(pacienteId);
    	}
    }

    /**
     * {@code GET  /informes/:id} : get the "id" informe.
     *
     * @param id the id of the informeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the informeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/informes/{id}")
    public ResponseEntity<InformeDTO> getInforme(@PathVariable Long id) {
        log.debug("REST request to get Informe : {}", id);
        Optional<InformeDTO> informeDTO = informeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(informeDTO);
    }

    /**
     * {@code DELETE  /informes/:id} : delete the "id" informe.
     *
     * @param id the id of the informeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/informes/{id}")
    public ResponseEntity<Void> deleteInforme(@PathVariable Long id) {
        log.debug("REST request to delete Informe : {}", id);
        informeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }



    @GetMapping("/sospechaClinicaSugerida")
    public  String getSospechaClinicaSugerida() throws Exception {
        return naiveBayesDiagnosis.getPrediction();
    }
}
