package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.EstadisticaTBADTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EstadisticaTBA} and its DTO {@link EstadisticaTBADTO}.
 */
@Mapper(componentModel = "spring", uses = {PruebaMapper.class, CodigoEstudioMapper.class, EdadTipoPruebaMapper.class})
public interface EstadisticaTBAMapper extends EntityMapper<EstadisticaTBADTO, EstadisticaTBA> {

    @Mapping(source = "prueba.id", target = "pruebaId")
    @Mapping(source = "prueba.nombre", target = "pruebaNombre")
    @Mapping(source = "codigoEstudio.id", target = "codigoEstudioId")
    @Mapping(source = "codigoEstudio.nombre", target = "codigoEstudioNivelEstudios")
    @Mapping(source = "edadTipoPrueba.id", target = "edadTipoPruebaId")
    @Mapping(source = "edadTipoPrueba.nombre", target = "edadTipoPruebaTipoPrueba")
    EstadisticaTBADTO toDto(EstadisticaTBA estadisticaTBA);

    @Mapping(source = "pruebaId", target = "prueba")
    @Mapping(source = "codigoEstudioId", target = "codigoEstudio")
    @Mapping(source = "edadTipoPruebaId", target = "edadTipoPrueba")
    EstadisticaTBA toEntity(EstadisticaTBADTO estadisticaTBADTO);

    default EstadisticaTBA fromId(Long id) {
        if (id == null) {
            return null;
        }
        EstadisticaTBA estadisticaTBA = new EstadisticaTBA();
        estadisticaTBA.setId(id);
        return estadisticaTBA;
    }
}
