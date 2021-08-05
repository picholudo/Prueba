package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.EdadTipoPrueba;
import com.mycompany.myapp.domain.enumeration.TipoPrueba;

import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the EdadTipoPrueba entity.
 */
@Repository
public interface EdadTipoPruebaRepository extends JpaRepository<EdadTipoPrueba, Long> {
	
	public List<EdadTipoPrueba> findByTipoPrueba(TipoPrueba tipoPrueba);
	
	@Query("select etp from EdadTipoPrueba etp where etp.tipoPrueba = ?1 and etp.edadMinima <= ?2 and etp.edadMaxima >= ?2")
	public EdadTipoPrueba findByTipoPruebaAndEdad(TipoPrueba tipoPrueba, Integer edad);
	
}
