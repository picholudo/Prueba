package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class EstadisticaMMSETest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EstadisticaMMSE.class);
        EstadisticaMMSE estadisticaMMSE1 = new EstadisticaMMSE();
        estadisticaMMSE1.setId(1L);
        EstadisticaMMSE estadisticaMMSE2 = new EstadisticaMMSE();
        estadisticaMMSE2.setId(estadisticaMMSE1.getId());
        assertThat(estadisticaMMSE1).isEqualTo(estadisticaMMSE2);
        estadisticaMMSE2.setId(2L);
        assertThat(estadisticaMMSE1).isNotEqualTo(estadisticaMMSE2);
        estadisticaMMSE1.setId(null);
        assertThat(estadisticaMMSE1).isNotEqualTo(estadisticaMMSE2);
    }
}
