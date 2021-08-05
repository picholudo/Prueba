package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.EdadTipoPrueba;
import com.mycompany.myapp.domain.EstadisticaSSNeuronorma;
import com.mycompany.myapp.domain.Prueba;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the EstadisticaSSNeuronorma entity.
 */
@Repository
public interface EstadisticaSSNeuronormaRepository extends JpaRepository<EstadisticaSSNeuronorma, Long> {

	EstadisticaSSNeuronorma findByPruebaAndEdadTipoPrueba(Prueba prueba, EdadTipoPrueba edadTipoPrueba);

}
