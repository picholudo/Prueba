package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.PuntuacionPruebaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link PuntuacionPrueba} and its DTO {@link PuntuacionPruebaDTO}.
 */
@Mapper(componentModel = "spring", uses = {ZScoreMapper.class, EvaluacionMapper.class})
public interface PuntuacionPruebaMapper extends EntityMapper<PuntuacionPruebaDTO, PuntuacionPrueba> {

    @Mapping(source = "zscore.id", target = "zscoreId")
    @Mapping(source = "zscore.nombre", target = "zscoreNombre")
    @Mapping(source = "paciente.id", target = "pacienteId")
    PuntuacionPruebaDTO toDto(PuntuacionPrueba puntuacionPrueba);

    @Mapping(source = "zscoreId", target = "zscore")
    @Mapping(source = "pacienteId", target = "paciente")
    PuntuacionPrueba toEntity(PuntuacionPruebaDTO puntuacionPruebaDTO);

    default PuntuacionPrueba fromId(Long id) {
        if (id == null) {
            return null;
        }
        PuntuacionPrueba puntuacionPrueba = new PuntuacionPrueba();
        puntuacionPrueba.setId(id);
        return puntuacionPrueba;
    }
}
