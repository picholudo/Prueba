package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.PacienteDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Paciente} and its DTO {@link PacienteDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PacienteMapper extends EntityMapper<PacienteDTO, Paciente> {


    @Mapping(target = "evaluacions", ignore = true)
    @Mapping(target = "removeEvaluacion", ignore = true)
    Paciente toEntity(PacienteDTO pacienteDTO);

    default Paciente fromId(Long id) {
        if (id == null) {
            return null;
        }
        Paciente paciente = new Paciente();
        paciente.setId(id);
        return paciente;
    }
}
