package com.mycompany.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class EstadisticaTAVECMapperTest {

    private EstadisticaTAVECMapper estadisticaTAVECMapper;

    @BeforeEach
    public void setUp() {
        estadisticaTAVECMapper = new EstadisticaTAVECMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(estadisticaTAVECMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(estadisticaTAVECMapper.fromId(null)).isNull();
    }
}
