package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Estadisticas;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Estadisticas entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EstadisticasRepository extends JpaRepository<Estadisticas, Long> {

}
