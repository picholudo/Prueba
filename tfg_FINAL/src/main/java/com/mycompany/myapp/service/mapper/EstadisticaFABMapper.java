package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.EstadisticaFABDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EstadisticaFAB} and its DTO {@link EstadisticaFABDTO}.
 */
@Mapper(componentModel = "spring", uses = {CodigoEstudioMapper.class, EdadTipoPruebaMapper.class})
public interface EstadisticaFABMapper extends EntityMapper<EstadisticaFABDTO, EstadisticaFAB> {

    @Mapping(source = "codigoEstudio.id", target = "codigoEstudioId")
    @Mapping(source = "codigoEstudio.nombre", target = "codigoEstudioNivelEstudios")
    @Mapping(source = "edadTipoPrueba.id", target = "edadTipoPruebaId")
    @Mapping(source = "edadTipoPrueba.nombre", target = "edadTipoPruebaTipoPrueba")
    EstadisticaFABDTO toDto(EstadisticaFAB estadisticaFAB);

    @Mapping(source = "codigoEstudioId", target = "codigoEstudio")
    @Mapping(source = "edadTipoPruebaId", target = "edadTipoPrueba")
    EstadisticaFAB toEntity(EstadisticaFABDTO estadisticaFABDTO);

    default EstadisticaFAB fromId(Long id) {
        if (id == null) {
            return null;
        }
        EstadisticaFAB estadisticaFAB = new EstadisticaFAB();
        estadisticaFAB.setId(id);
        return estadisticaFAB;
    }
}
