package com.mycompany.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class EstadisticaPzNeuronormaMapperTest {

    private EstadisticaPzNeuronormaMapper estadisticaPzNeuronormaMapper;

    @BeforeEach
    public void setUp() {
        estadisticaPzNeuronormaMapper = new EstadisticaPzNeuronormaMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(estadisticaPzNeuronormaMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(estadisticaPzNeuronormaMapper.fromId(null)).isNull();
    }
}
