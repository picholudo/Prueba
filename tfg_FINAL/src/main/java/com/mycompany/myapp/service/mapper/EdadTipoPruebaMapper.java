package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.EdadTipoPruebaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EdadTipoPrueba} and its DTO {@link EdadTipoPruebaDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EdadTipoPruebaMapper extends EntityMapper<EdadTipoPruebaDTO, EdadTipoPrueba> {



    default EdadTipoPrueba fromId(Long id) {
        if (id == null) {
            return null;
        }
        EdadTipoPrueba edadTipoPrueba = new EdadTipoPrueba();
        edadTipoPrueba.setId(id);
        return edadTipoPrueba;
    }
}
