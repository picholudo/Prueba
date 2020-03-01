package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.PuntosCorte;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the PuntosCorte entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PuntosCorteRepository extends JpaRepository<PuntosCorte, Long> {

}
