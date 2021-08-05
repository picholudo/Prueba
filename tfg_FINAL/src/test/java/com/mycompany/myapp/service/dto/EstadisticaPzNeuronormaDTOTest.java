package com.mycompany.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class EstadisticaPzNeuronormaDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EstadisticaPzNeuronormaDTO.class);
        EstadisticaPzNeuronormaDTO estadisticaPzNeuronormaDTO1 = new EstadisticaPzNeuronormaDTO();
        estadisticaPzNeuronormaDTO1.setId(1L);
        EstadisticaPzNeuronormaDTO estadisticaPzNeuronormaDTO2 = new EstadisticaPzNeuronormaDTO();
        assertThat(estadisticaPzNeuronormaDTO1).isNotEqualTo(estadisticaPzNeuronormaDTO2);
        estadisticaPzNeuronormaDTO2.setId(estadisticaPzNeuronormaDTO1.getId());
        assertThat(estadisticaPzNeuronormaDTO1).isEqualTo(estadisticaPzNeuronormaDTO2);
        estadisticaPzNeuronormaDTO2.setId(2L);
        assertThat(estadisticaPzNeuronormaDTO1).isNotEqualTo(estadisticaPzNeuronormaDTO2);
        estadisticaPzNeuronormaDTO1.setId(null);
        assertThat(estadisticaPzNeuronormaDTO1).isNotEqualTo(estadisticaPzNeuronormaDTO2);
    }
}
