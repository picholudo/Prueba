package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.EstadisticaSSNeuronormaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EstadisticaSSNeuronorma} and its DTO {@link EstadisticaSSNeuronormaDTO}.
 */
@Mapper(componentModel = "spring", uses = {PruebaMapper.class, EdadTipoPruebaMapper.class})
public interface EstadisticaSSNeuronormaMapper extends EntityMapper<EstadisticaSSNeuronormaDTO, EstadisticaSSNeuronorma> {

    @Mapping(source = "prueba.id", target = "pruebaId")
    @Mapping(source = "prueba.nombre", target = "pruebaNombre")
    @Mapping(source = "edadTipoPrueba.id", target = "edadTipoPruebaId")
    @Mapping(source = "edadTipoPrueba.nombre", target = "edadTipoPruebaTipoPrueba")
    EstadisticaSSNeuronormaDTO toDto(EstadisticaSSNeuronorma estadisticaSSNeuronorma);

    @Mapping(source = "pruebaId", target = "prueba")
    @Mapping(source = "edadTipoPruebaId", target = "edadTipoPrueba")
    EstadisticaSSNeuronorma toEntity(EstadisticaSSNeuronormaDTO estadisticaSSNeuronormaDTO);

    default EstadisticaSSNeuronorma fromId(Long id) {
        if (id == null) {
            return null;
        }
        EstadisticaSSNeuronorma estadisticaSSNeuronorma = new EstadisticaSSNeuronorma();
        estadisticaSSNeuronorma.setId(id);
        return estadisticaSSNeuronorma;
    }
}
