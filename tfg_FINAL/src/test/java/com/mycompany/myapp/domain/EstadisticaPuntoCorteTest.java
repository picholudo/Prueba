package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class EstadisticaPuntoCorteTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EstadisticaPuntoCorte.class);
        EstadisticaPuntoCorte estadisticaPuntoCorte1 = new EstadisticaPuntoCorte();
        estadisticaPuntoCorte1.setId(1L);
        EstadisticaPuntoCorte estadisticaPuntoCorte2 = new EstadisticaPuntoCorte();
        estadisticaPuntoCorte2.setId(estadisticaPuntoCorte1.getId());
        assertThat(estadisticaPuntoCorte1).isEqualTo(estadisticaPuntoCorte2);
        estadisticaPuntoCorte2.setId(2L);
        assertThat(estadisticaPuntoCorte1).isNotEqualTo(estadisticaPuntoCorte2);
        estadisticaPuntoCorte1.setId(null);
        assertThat(estadisticaPuntoCorte1).isNotEqualTo(estadisticaPuntoCorte2);
    }
}
