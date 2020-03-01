package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.ZScore;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ZScore entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ZScoreRepository extends JpaRepository<ZScore, Long> {

}
