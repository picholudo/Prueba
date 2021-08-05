package com.mycompany.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class EstadisticaFABMapperTest {

    private EstadisticaFABMapper estadisticaFABMapper;

    @BeforeEach
    public void setUp() {
        estadisticaFABMapper = new EstadisticaFABMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(estadisticaFABMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(estadisticaFABMapper.fromId(null)).isNull();
    }
}
