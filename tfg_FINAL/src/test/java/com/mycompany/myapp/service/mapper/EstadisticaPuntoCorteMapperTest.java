package com.mycompany.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class EstadisticaPuntoCorteMapperTest {

    private EstadisticaPuntoCorteMapper estadisticaPuntoCorteMapper;

    @BeforeEach
    public void setUp() {
        estadisticaPuntoCorteMapper = new EstadisticaPuntoCorteMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(estadisticaPuntoCorteMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(estadisticaPuntoCorteMapper.fromId(null)).isNull();
    }
}
