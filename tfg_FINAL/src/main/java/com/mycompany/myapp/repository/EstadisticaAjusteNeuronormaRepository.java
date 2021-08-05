package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.CodigoEstudio;
import com.mycompany.myapp.domain.EstadisticaAjusteNeuronorma;
import com.mycompany.myapp.domain.Prueba;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the EstadisticaAjusteNeuronorma entity.
 */
@Repository
public interface EstadisticaAjusteNeuronormaRepository extends JpaRepository<EstadisticaAjusteNeuronorma, Long> {

	EstadisticaAjusteNeuronorma findByPruebaAndCodigoEstudioAndScaledScore(Prueba prueba, CodigoEstudio codigoEstudio, Integer scaledScore);

}
