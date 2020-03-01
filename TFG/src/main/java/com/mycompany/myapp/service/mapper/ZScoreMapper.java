package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.ZScoreDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ZScore} and its DTO {@link ZScoreDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ZScoreMapper extends EntityMapper<ZScoreDTO, ZScore> {


    @Mapping(target = "estadisticas", ignore = true)
    @Mapping(target = "removeEstadisticas", ignore = true)
    @Mapping(target = "puntuacionPruebas", ignore = true)
    @Mapping(target = "removePuntuacionPrueba", ignore = true)
    ZScore toEntity(ZScoreDTO zScoreDTO);

    default ZScore fromId(Long id) {
        if (id == null) {
            return null;
        }
        ZScore zScore = new ZScore();
        zScore.setId(id);
        return zScore;
    }
}
