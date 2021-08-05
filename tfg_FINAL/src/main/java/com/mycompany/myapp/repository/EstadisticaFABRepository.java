package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.CodigoEstudio;
import com.mycompany.myapp.domain.EdadTipoPrueba;
import com.mycompany.myapp.domain.EstadisticaFAB;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the EstadisticaFAB entity.
 */
@Repository
public interface EstadisticaFABRepository extends JpaRepository<EstadisticaFAB, Long> {

	EstadisticaFAB findByCodigoEstudioAndEdadTipoPrueba(CodigoEstudio codigoEstudio, EdadTipoPrueba edadTipoPrueba);

}
