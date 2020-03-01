package com.mycompany.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class PuntosCorteMapperTest {

    private PuntosCorteMapper puntosCorteMapper;

    @BeforeEach
    public void setUp() {
        puntosCorteMapper = new PuntosCorteMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(puntosCorteMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(puntosCorteMapper.fromId(null)).isNull();
    }
}
