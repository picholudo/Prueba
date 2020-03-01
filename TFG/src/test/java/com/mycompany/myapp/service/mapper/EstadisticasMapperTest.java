package com.mycompany.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class EstadisticasMapperTest {

    private EstadisticasMapper estadisticasMapper;

    @BeforeEach
    public void setUp() {
        estadisticasMapper = new EstadisticasMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(estadisticasMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(estadisticasMapper.fromId(null)).isNull();
    }
}
