package com.mycompany.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class EstadisticaSSNeuronormaDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EstadisticaSSNeuronormaDTO.class);
        EstadisticaSSNeuronormaDTO estadisticaSSNeuronormaDTO1 = new EstadisticaSSNeuronormaDTO();
        estadisticaSSNeuronormaDTO1.setId(1L);
        EstadisticaSSNeuronormaDTO estadisticaSSNeuronormaDTO2 = new EstadisticaSSNeuronormaDTO();
        assertThat(estadisticaSSNeuronormaDTO1).isNotEqualTo(estadisticaSSNeuronormaDTO2);
        estadisticaSSNeuronormaDTO2.setId(estadisticaSSNeuronormaDTO1.getId());
        assertThat(estadisticaSSNeuronormaDTO1).isEqualTo(estadisticaSSNeuronormaDTO2);
        estadisticaSSNeuronormaDTO2.setId(2L);
        assertThat(estadisticaSSNeuronormaDTO1).isNotEqualTo(estadisticaSSNeuronormaDTO2);
        estadisticaSSNeuronormaDTO1.setId(null);
        assertThat(estadisticaSSNeuronormaDTO1).isNotEqualTo(estadisticaSSNeuronormaDTO2);
    }
}
