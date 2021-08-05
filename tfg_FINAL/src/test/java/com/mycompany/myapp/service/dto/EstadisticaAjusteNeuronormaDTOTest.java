package com.mycompany.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class EstadisticaAjusteNeuronormaDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EstadisticaAjusteNeuronormaDTO.class);
        EstadisticaAjusteNeuronormaDTO estadisticaAjusteNeuronormaDTO1 = new EstadisticaAjusteNeuronormaDTO();
        estadisticaAjusteNeuronormaDTO1.setId(1L);
        EstadisticaAjusteNeuronormaDTO estadisticaAjusteNeuronormaDTO2 = new EstadisticaAjusteNeuronormaDTO();
        assertThat(estadisticaAjusteNeuronormaDTO1).isNotEqualTo(estadisticaAjusteNeuronormaDTO2);
        estadisticaAjusteNeuronormaDTO2.setId(estadisticaAjusteNeuronormaDTO1.getId());
        assertThat(estadisticaAjusteNeuronormaDTO1).isEqualTo(estadisticaAjusteNeuronormaDTO2);
        estadisticaAjusteNeuronormaDTO2.setId(2L);
        assertThat(estadisticaAjusteNeuronormaDTO1).isNotEqualTo(estadisticaAjusteNeuronormaDTO2);
        estadisticaAjusteNeuronormaDTO1.setId(null);
        assertThat(estadisticaAjusteNeuronormaDTO1).isNotEqualTo(estadisticaAjusteNeuronormaDTO2);
    }
}
