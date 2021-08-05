package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.EstadisticaTAVECDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EstadisticaTAVEC} and its DTO {@link EstadisticaTAVECDTO}.
 */
@Mapper(componentModel = "spring", uses = {PruebaMapper.class, EdadTipoPruebaMapper.class})
public interface EstadisticaTAVECMapper extends EntityMapper<EstadisticaTAVECDTO, EstadisticaTAVEC> {

    @Mapping(source = "prueba.id", target = "pruebaId")
    @Mapping(source = "prueba.nombre", target = "pruebaNombre")
    @Mapping(source = "edadTipoPrueba.id", target = "edadTipoPruebaId")
    @Mapping(source = "edadTipoPrueba.nombre", target = "edadTipoPruebaTipoPrueba")
    EstadisticaTAVECDTO toDto(EstadisticaTAVEC estadisticaTAVEC);

    @Mapping(source = "pruebaId", target = "prueba")
    @Mapping(source = "edadTipoPruebaId", target = "edadTipoPrueba")
    EstadisticaTAVEC toEntity(EstadisticaTAVECDTO estadisticaTAVECDTO);

    default EstadisticaTAVEC fromId(Long id) {
        if (id == null) {
            return null;
        }
        EstadisticaTAVEC estadisticaTAVEC = new EstadisticaTAVEC();
        estadisticaTAVEC.setId(id);
        return estadisticaTAVEC;
    }
}
