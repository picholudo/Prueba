package com.mycompany.myapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mycompany.myapp.domain.Grupo;
import com.mycompany.myapp.domain.User;

/**
 * Spring Data  repository for the Grupo entity.
 */
@Repository
public interface GrupoRepository extends JpaRepository<Grupo, Long> {

    @Query(value = "select distinct grupo from Grupo grupo left join fetch grupo.users",
        countQuery = "select count(distinct grupo) from Grupo grupo")
    Page<Grupo> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct grupo from Grupo grupo left join fetch grupo.users")
    List<Grupo> findAllWithEagerRelationships();
    
    @Query("select distinct grupo from Grupo grupo left join fetch grupo.users where :user in grupo.users")
    List<Grupo> findByUserWithEagerRelationships(@Param("user") User user);

    @Query("select grupo from Grupo grupo left join fetch grupo.users where grupo.id =:id")
    Optional<Grupo> findOneWithEagerRelationships(@Param("id") Long id);

}
