package com.mycompany.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class EstadisticaTBAMapperTest {

    private EstadisticaTBAMapper estadisticaTBAMapper;

    @BeforeEach
    public void setUp() {
        estadisticaTBAMapper = new EstadisticaTBAMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(estadisticaTBAMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(estadisticaTBAMapper.fromId(null)).isNull();
    }
}
