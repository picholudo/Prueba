package com.mycompany.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class PuntuacionPruebaMapperTest {

    private PuntuacionPruebaMapper puntuacionPruebaMapper;

    @BeforeEach
    public void setUp() {
        puntuacionPruebaMapper = new PuntuacionPruebaMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(puntuacionPruebaMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(puntuacionPruebaMapper.fromId(null)).isNull();
    }
}
