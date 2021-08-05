package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.EdadTipoPrueba;
import com.mycompany.myapp.domain.EstadisticaTAVEC;
import com.mycompany.myapp.domain.Prueba;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the EstadisticaTAVEC entity.
 */
@Repository
public interface EstadisticaTAVECRepository extends JpaRepository<EstadisticaTAVEC, Long> {

	EstadisticaTAVEC findByPruebaAndEdadTipoPrueba(Prueba prueba, EdadTipoPrueba etp);

}
