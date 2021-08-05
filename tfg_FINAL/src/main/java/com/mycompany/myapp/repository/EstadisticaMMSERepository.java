package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.CodigoEstudio;
import com.mycompany.myapp.domain.EdadTipoPrueba;
import com.mycompany.myapp.domain.EstadisticaMMSE;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the EstadisticaMMSE entity.
 */
@Repository
public interface EstadisticaMMSERepository extends JpaRepository<EstadisticaMMSE, Long> {

	EstadisticaMMSE findByCodigoEstudioAndEdadTipoPrueba(CodigoEstudio codigoEstudio, EdadTipoPrueba etp);
}
