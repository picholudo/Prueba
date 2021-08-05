package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class EstadisticaAjusteNeuronormaTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EstadisticaAjusteNeuronorma.class);
        EstadisticaAjusteNeuronorma estadisticaAjusteNeuronorma1 = new EstadisticaAjusteNeuronorma();
        estadisticaAjusteNeuronorma1.setId(1L);
        EstadisticaAjusteNeuronorma estadisticaAjusteNeuronorma2 = new EstadisticaAjusteNeuronorma();
        estadisticaAjusteNeuronorma2.setId(estadisticaAjusteNeuronorma1.getId());
        assertThat(estadisticaAjusteNeuronorma1).isEqualTo(estadisticaAjusteNeuronorma2);
        estadisticaAjusteNeuronorma2.setId(2L);
        assertThat(estadisticaAjusteNeuronorma1).isNotEqualTo(estadisticaAjusteNeuronorma2);
        estadisticaAjusteNeuronorma1.setId(null);
        assertThat(estadisticaAjusteNeuronorma1).isNotEqualTo(estadisticaAjusteNeuronorma2);
    }
}
