package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Prueba;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Prueba entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PruebaRepository extends JpaRepository<Prueba, Long> {

}
