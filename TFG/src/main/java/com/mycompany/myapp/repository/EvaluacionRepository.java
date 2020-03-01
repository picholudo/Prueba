package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Evaluacion;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Evaluacion entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EvaluacionRepository extends JpaRepository<Evaluacion, Long> {

}
