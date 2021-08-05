package com.mycompany.myapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mycompany.myapp.domain.Informe;
import com.mycompany.myapp.domain.ResultadoPrueba;


/**
 * Spring Data  repository for the ResultadoPrueba entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ResultadoPruebaRepository extends JpaRepository<ResultadoPrueba, Long> {

	List<ResultadoPrueba> findByInforme(Informe informe);
}
