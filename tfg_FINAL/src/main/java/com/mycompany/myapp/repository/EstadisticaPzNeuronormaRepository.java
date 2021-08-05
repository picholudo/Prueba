package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.EstadisticaPzNeuronorma;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the EstadisticaPzNeuronorma entity.
 */
@Repository
public interface EstadisticaPzNeuronormaRepository extends JpaRepository<EstadisticaPzNeuronorma, Long> {

	EstadisticaPzNeuronorma findByAjusteEstudios(Integer ajusteEstudios);

}
