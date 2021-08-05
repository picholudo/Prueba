package com.mycompany.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class EstadisticaAjusteNeuronormaMapperTest {

    private EstadisticaAjusteNeuronormaMapper estadisticaAjusteNeuronormaMapper;

    @BeforeEach
    public void setUp() {
        estadisticaAjusteNeuronormaMapper = new EstadisticaAjusteNeuronormaMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(estadisticaAjusteNeuronormaMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(estadisticaAjusteNeuronormaMapper.fromId(null)).isNull();
    }
}
