package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.ResultadoPruebaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ResultadoPrueba} and its DTO {@link ResultadoPruebaDTO}.
 */
@Mapper(componentModel = "spring", uses = {PruebaMapper.class, InformeMapper.class})
public interface ResultadoPruebaMapper extends EntityMapper<ResultadoPruebaDTO, ResultadoPrueba> {

    @Mapping(source = "prueba.id", target = "pruebaId")
    @Mapping(source = "prueba.nombre", target = "pruebaNombre")
    @Mapping(source = "prueba.tipoPrueba", target = "tipoPrueba")
    @Mapping(source = "informe.id", target = "informeId")
    @Mapping(source = "informe.nombre", target = "informeNombre")
    ResultadoPruebaDTO toDto(ResultadoPrueba resultadoPrueba);

    @Mapping(source = "pruebaId", target = "prueba")
    @Mapping(source = "informeId", target = "informe")
    ResultadoPrueba toEntity(ResultadoPruebaDTO resultadoPruebaDTO);

    default ResultadoPrueba fromId(Long id) {
        if (id == null) {
            return null;
        }
        ResultadoPrueba resultadoPrueba = new ResultadoPrueba();
        resultadoPrueba.setId(id);
        return resultadoPrueba;
    }
}
