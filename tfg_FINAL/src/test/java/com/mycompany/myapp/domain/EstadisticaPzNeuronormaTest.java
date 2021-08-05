package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class EstadisticaPzNeuronormaTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EstadisticaPzNeuronorma.class);
        EstadisticaPzNeuronorma estadisticaPzNeuronorma1 = new EstadisticaPzNeuronorma();
        estadisticaPzNeuronorma1.setId(1L);
        EstadisticaPzNeuronorma estadisticaPzNeuronorma2 = new EstadisticaPzNeuronorma();
        estadisticaPzNeuronorma2.setId(estadisticaPzNeuronorma1.getId());
        assertThat(estadisticaPzNeuronorma1).isEqualTo(estadisticaPzNeuronorma2);
        estadisticaPzNeuronorma2.setId(2L);
        assertThat(estadisticaPzNeuronorma1).isNotEqualTo(estadisticaPzNeuronorma2);
        estadisticaPzNeuronorma1.setId(null);
        assertThat(estadisticaPzNeuronorma1).isNotEqualTo(estadisticaPzNeuronorma2);
    }
}
