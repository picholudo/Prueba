package com.mycompany.myapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mycompany.myapp.domain.CodigoEstudio;
import com.mycompany.myapp.domain.enumeration.NivelEstudios;


/**
 * Spring Data  repository for the CodigoEstudio entity.
 */
@Repository
public interface CodigoEstudioRepository extends JpaRepository<CodigoEstudio, Long> {
	
	CodigoEstudio findByNivelEstudios(NivelEstudios nivelEstudios);

}
