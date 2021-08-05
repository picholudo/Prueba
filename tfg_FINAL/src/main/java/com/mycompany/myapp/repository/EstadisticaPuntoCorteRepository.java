package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.EstadisticaPuntoCorte;
import com.mycompany.myapp.domain.Prueba;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the EstadisticaPuntoCorte entity.
 */
@Repository
public interface EstadisticaPuntoCorteRepository extends JpaRepository<EstadisticaPuntoCorte, Long> {

	EstadisticaPuntoCorte findByPrueba(Prueba prueba);

}
