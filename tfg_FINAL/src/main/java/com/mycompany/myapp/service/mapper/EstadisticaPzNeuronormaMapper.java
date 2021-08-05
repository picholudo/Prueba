package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.EstadisticaPzNeuronormaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EstadisticaPzNeuronorma} and its DTO {@link EstadisticaPzNeuronormaDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EstadisticaPzNeuronormaMapper extends EntityMapper<EstadisticaPzNeuronormaDTO, EstadisticaPzNeuronorma> {



    default EstadisticaPzNeuronorma fromId(Long id) {
        if (id == null) {
            return null;
        }
        EstadisticaPzNeuronorma estadisticaPzNeuronorma = new EstadisticaPzNeuronorma();
        estadisticaPzNeuronorma.setId(id);
        return estadisticaPzNeuronorma;
    }
}
