package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.CodigoEstudioDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link CodigoEstudio} and its DTO {@link CodigoEstudioDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CodigoEstudioMapper extends EntityMapper<CodigoEstudioDTO, CodigoEstudio> {



    default CodigoEstudio fromId(Long id) {
        if (id == null) {
            return null;
        }
        CodigoEstudio codigoEstudio = new CodigoEstudio();
        codigoEstudio.setId(id);
        return codigoEstudio;
    }
}
