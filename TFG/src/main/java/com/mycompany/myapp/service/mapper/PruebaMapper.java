package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.PruebaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Prueba} and its DTO {@link PruebaDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PruebaMapper extends EntityMapper<PruebaDTO, Prueba> {



    default Prueba fromId(Long id) {
        if (id == null) {
            return null;
        }
        Prueba prueba = new Prueba();
        prueba.setId(id);
        return prueba;
    }
}
