package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.PuntosCorteDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link PuntosCorte} and its DTO {@link PuntosCorteDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PuntosCorteMapper extends EntityMapper<PuntosCorteDTO, PuntosCorte> {



    default PuntosCorte fromId(Long id) {
        if (id == null) {
            return null;
        }
        PuntosCorte puntosCorte = new PuntosCorte();
        puntosCorte.setId(id);
        return puntosCorte;
    }
}
