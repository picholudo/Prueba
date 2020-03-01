package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.EvaluacionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Evaluacion} and its DTO {@link EvaluacionDTO}.
 */
@Mapper(componentModel = "spring", uses = {PacienteMapper.class})
public interface EvaluacionMapper extends EntityMapper<EvaluacionDTO, Evaluacion> {

    @Mapping(source = "paciente.id", target = "pacienteId")
    EvaluacionDTO toDto(Evaluacion evaluacion);

    @Mapping(target = "puntuacionPruebas", ignore = true)
    @Mapping(target = "removePuntuacionPrueba", ignore = true)
    @Mapping(source = "pacienteId", target = "paciente")
    Evaluacion toEntity(EvaluacionDTO evaluacionDTO);

    default Evaluacion fromId(Long id) {
        if (id == null) {
            return null;
        }
        Evaluacion evaluacion = new Evaluacion();
        evaluacion.setId(id);
        return evaluacion;
    }
}
