package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.PuntuacionPrueba;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the PuntuacionPrueba entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PuntuacionPruebaRepository extends JpaRepository<PuntuacionPrueba, Long> {

}
