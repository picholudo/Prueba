package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class EstadisticaSSNeuronormaTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EstadisticaSSNeuronorma.class);
        EstadisticaSSNeuronorma estadisticaSSNeuronorma1 = new EstadisticaSSNeuronorma();
        estadisticaSSNeuronorma1.setId(1L);
        EstadisticaSSNeuronorma estadisticaSSNeuronorma2 = new EstadisticaSSNeuronorma();
        estadisticaSSNeuronorma2.setId(estadisticaSSNeuronorma1.getId());
        assertThat(estadisticaSSNeuronorma1).isEqualTo(estadisticaSSNeuronorma2);
        estadisticaSSNeuronorma2.setId(2L);
        assertThat(estadisticaSSNeuronorma1).isNotEqualTo(estadisticaSSNeuronorma2);
        estadisticaSSNeuronorma1.setId(null);
        assertThat(estadisticaSSNeuronorma1).isNotEqualTo(estadisticaSSNeuronorma2);
    }
}
