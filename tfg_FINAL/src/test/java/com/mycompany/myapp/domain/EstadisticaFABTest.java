package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class EstadisticaFABTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EstadisticaFAB.class);
        EstadisticaFAB estadisticaFAB1 = new EstadisticaFAB();
        estadisticaFAB1.setId(1L);
        EstadisticaFAB estadisticaFAB2 = new EstadisticaFAB();
        estadisticaFAB2.setId(estadisticaFAB1.getId());
        assertThat(estadisticaFAB1).isEqualTo(estadisticaFAB2);
        estadisticaFAB2.setId(2L);
        assertThat(estadisticaFAB1).isNotEqualTo(estadisticaFAB2);
        estadisticaFAB1.setId(null);
        assertThat(estadisticaFAB1).isNotEqualTo(estadisticaFAB2);
    }
}
