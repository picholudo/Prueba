package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.GrupoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Grupo} and its DTO {@link GrupoDTO}.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface GrupoMapper extends EntityMapper<GrupoDTO, Grupo> {


    @Mapping(target = "removeUser", ignore = true)

    default Grupo fromId(Long id) {
        if (id == null) {
            return null;
        }
        Grupo grupo = new Grupo();
        grupo.setId(id);
        return grupo;
    }
}
