package com.mycompany.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class EstadisticaMMSEMapperTest {

    private EstadisticaMMSEMapper estadisticaMMSEMapper;

    @BeforeEach
    public void setUp() {
        estadisticaMMSEMapper = new EstadisticaMMSEMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(estadisticaMMSEMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(estadisticaMMSEMapper.fromId(null)).isNull();
    }
}
