package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.CodigoEstudio;
import com.mycompany.myapp.domain.EdadTipoPrueba;
import com.mycompany.myapp.domain.EstadisticaTBA;
import com.mycompany.myapp.domain.Prueba;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the EstadisticaTBA entity.
 */
@Repository
public interface EstadisticaTBARepository extends JpaRepository<EstadisticaTBA, Long> {

	EstadisticaTBA findByPruebaAndCodigoEstudioAndEdadTipoPrueba(Prueba prueba, CodigoEstudio codigoEstudio, EdadTipoPrueba etp);

}
