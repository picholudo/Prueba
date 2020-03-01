package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.EstadisticasDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Estadisticas} and its DTO {@link EstadisticasDTO}.
 */
@Mapper(componentModel = "spring", uses = {ZScoreMapper.class})
public interface EstadisticasMapper extends EntityMapper<EstadisticasDTO, Estadisticas> {

    @Mapping(source = "zscore.id", target = "zscoreId")
    @Mapping(source = "zscore.nombre", target = "zscoreNombre")
    EstadisticasDTO toDto(Estadisticas estadisticas);

    @Mapping(source = "zscoreId", target = "zscore")
    Estadisticas toEntity(EstadisticasDTO estadisticasDTO);

    default Estadisticas fromId(Long id) {
        if (id == null) {
            return null;
        }
        Estadisticas estadisticas = new Estadisticas();
        estadisticas.setId(id);
        return estadisticas;
    }
}
