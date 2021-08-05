package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.EstadisticaAjusteNeuronormaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EstadisticaAjusteNeuronorma} and its DTO {@link EstadisticaAjusteNeuronormaDTO}.
 */
@Mapper(componentModel = "spring", uses = {PruebaMapper.class, CodigoEstudioMapper.class})
public interface EstadisticaAjusteNeuronormaMapper extends EntityMapper<EstadisticaAjusteNeuronormaDTO, EstadisticaAjusteNeuronorma> {

    @Mapping(source = "prueba.id", target = "pruebaId")
    @Mapping(source = "prueba.nombre", target = "pruebaNombre")
    @Mapping(source = "codigoEstudio.id", target = "codigoEstudioId")
    @Mapping(source = "codigoEstudio.nombre", target = "codigoEstudioNivelEstudios")
    EstadisticaAjusteNeuronormaDTO toDto(EstadisticaAjusteNeuronorma estadisticaAjusteNeuronorma);

    @Mapping(source = "pruebaId", target = "prueba")
    @Mapping(source = "codigoEstudioId", target = "codigoEstudio")
    EstadisticaAjusteNeuronorma toEntity(EstadisticaAjusteNeuronormaDTO estadisticaAjusteNeuronormaDTO);

    default EstadisticaAjusteNeuronorma fromId(Long id) {
        if (id == null) {
            return null;
        }
        EstadisticaAjusteNeuronorma estadisticaAjusteNeuronorma = new EstadisticaAjusteNeuronorma();
        estadisticaAjusteNeuronorma.setId(id);
        return estadisticaAjusteNeuronorma;
    }
}
