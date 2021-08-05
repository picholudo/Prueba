package com.mycompany.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class EstadisticaSSNeuronormaMapperTest {

    private EstadisticaSSNeuronormaMapper estadisticaSSNeuronormaMapper;

    @BeforeEach
    public void setUp() {
        estadisticaSSNeuronormaMapper = new EstadisticaSSNeuronormaMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(estadisticaSSNeuronormaMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(estadisticaSSNeuronormaMapper.fromId(null)).isNull();
    }
}
