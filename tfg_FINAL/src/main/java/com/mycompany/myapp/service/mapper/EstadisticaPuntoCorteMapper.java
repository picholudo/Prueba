package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.EstadisticaPuntoCorteDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EstadisticaPuntoCorte} and its DTO {@link EstadisticaPuntoCorteDTO}.
 */
@Mapper(componentModel = "spring", uses = {PruebaMapper.class})
public interface EstadisticaPuntoCorteMapper extends EntityMapper<EstadisticaPuntoCorteDTO, EstadisticaPuntoCorte> {

    @Mapping(source = "prueba.id", target = "pruebaId")
    @Mapping(source = "prueba.nombre", target = "pruebaNombre")
    EstadisticaPuntoCorteDTO toDto(EstadisticaPuntoCorte estadisticaPuntoCorte);

    @Mapping(source = "pruebaId", target = "prueba")
    EstadisticaPuntoCorte toEntity(EstadisticaPuntoCorteDTO estadisticaPuntoCorteDTO);

    default EstadisticaPuntoCorte fromId(Long id) {
        if (id == null) {
            return null;
        }
        EstadisticaPuntoCorte estadisticaPuntoCorte = new EstadisticaPuntoCorte();
        estadisticaPuntoCorte.setId(id);
        return estadisticaPuntoCorte;
    }
}
