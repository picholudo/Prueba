package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.InformeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Informe} and its DTO {@link InformeDTO}.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, PacienteMapper.class})
public interface InformeMapper extends EntityMapper<InformeDTO, Informe> {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.email", target = "userEmail")
    @Mapping(source = "paciente.id", target = "pacienteId")
    @Mapping(source = "paciente.nombre", target = "pacienteNombre")
    InformeDTO toDto(Informe informe);

    @Mapping(target = "resultadoPruebas", ignore = true)
    @Mapping(target = "removeResultadoPrueba", ignore = true)
    @Mapping(source = "userId", target = "user")
    @Mapping(source = "pacienteId", target = "paciente")
    Informe toEntity(InformeDTO informeDTO);

    default Informe fromId(Long id) {
        if (id == null) {
            return null;
        }
        Informe informe = new Informe();
        informe.setId(id);
        return informe;
    }
}
