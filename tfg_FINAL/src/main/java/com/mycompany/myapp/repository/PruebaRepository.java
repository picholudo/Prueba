package com.mycompany.myapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mycompany.myapp.domain.Prueba;
import com.mycompany.myapp.domain.enumeration.TipoPrueba;


/**
 * Spring Data  repository for the Prueba entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PruebaRepository extends JpaRepository<Prueba, Long> {

	List<Prueba> findByTipoPrueba(TipoPrueba tipoPrueba);

}
