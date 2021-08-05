package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.EstadisticaMMSEDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EstadisticaMMSE} and its DTO {@link EstadisticaMMSEDTO}.
 */
@Mapper(componentModel = "spring", uses = {CodigoEstudioMapper.class, EdadTipoPruebaMapper.class})
public interface EstadisticaMMSEMapper extends EntityMapper<EstadisticaMMSEDTO, EstadisticaMMSE> {

    @Mapping(source = "codigoEstudio.id", target = "codigoEstudioId")
    @Mapping(source = "codigoEstudio.nombre", target = "codigoEstudioNivelEstudios")
    @Mapping(source = "edadTipoPrueba.id", target = "edadTipoPruebaId")
    @Mapping(source = "edadTipoPrueba.nombre", target = "edadTipoPruebaTipoPrueba")
    EstadisticaMMSEDTO toDto(EstadisticaMMSE estadisticaMMSE);

    @Mapping(source = "codigoEstudioId", target = "codigoEstudio")
    @Mapping(source = "edadTipoPruebaId", target = "edadTipoPrueba")
    EstadisticaMMSE toEntity(EstadisticaMMSEDTO estadisticaMMSEDTO);

    default EstadisticaMMSE fromId(Long id) {
        if (id == null) {
            return null;
        }
        EstadisticaMMSE estadisticaMMSE = new EstadisticaMMSE();
        estadisticaMMSE.setId(id);
        return estadisticaMMSE;
    }
}
