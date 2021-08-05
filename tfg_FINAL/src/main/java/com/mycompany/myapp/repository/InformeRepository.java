package com.mycompany.myapp.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mycompany.myapp.domain.Grupo;
import com.mycompany.myapp.domain.Informe;
import com.mycompany.myapp.domain.Paciente;

/**
 * Spring Data  repository for the Informe entity.
 */
@Repository
public interface InformeRepository extends JpaRepository<Informe, Long> {

	List<Informe> findByPaciente(Paciente paciente);
	
	@Query("select informe from Informe informe where informe.user.login = ?#{principal.username}")
    List<Informe> findByUserIsCurrentUser();
	
	@Query("select informe from Informe informe where informe.paciente = :paciente and informe.user.login = ?#{principal.username}")
    List<Informe> findByPacienteAndUserIsCurrentUser(@Param("paciente") Paciente paciente);
	
	
	@Query("select informe from Informe informe "
			+ "where informe.user.login = ?#{principal.username} "
			+ "or informe.user in ("
			+ "	select grupo.users"
			+ "	from Grupo grupo"
			+ "	inner join grupo.users user"
			+ "	where user.login = ?#{principal.username})")
    List<Informe> findByGrupoOrUserIsCurrentUser();
	
	
	@Query("select informe from Informe informe "
			+ "where informe.user.login = ?#{principal.username} "
			+ "or informe.user in ("
			+ "	select users"
			+ "	from Grupo grupo"
			+ " join grupo.users users"
			+ " where grupo in :grupos)")
    List<Informe> findByGruposOrUserIsCurrentUser(@Param("grupos") Set<Grupo> grupos);
	
	@Query("select informe from Informe informe "
			+ "where informe.paciente = :paciente and "
			+ "(informe.user.login = ?#{principal.username} "
			+ "or informe.user in ("
			+ "	select users"
			+ "	from Grupo grupo"
			+ " join grupo.users users"
			+ " where grupo in :grupos)"
			+ ")")
    List<Informe> findByPacienteAndGruposOrUserIsCurrentUser(@Param("paciente") Paciente paciente, @Param("grupos") Set<Grupo> grupos);


}
