package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class EstadisticaTBATest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EstadisticaTBA.class);
        EstadisticaTBA estadisticaTBA1 = new EstadisticaTBA();
        estadisticaTBA1.setId(1L);
        EstadisticaTBA estadisticaTBA2 = new EstadisticaTBA();
        estadisticaTBA2.setId(estadisticaTBA1.getId());
        assertThat(estadisticaTBA1).isEqualTo(estadisticaTBA2);
        estadisticaTBA2.setId(2L);
        assertThat(estadisticaTBA1).isNotEqualTo(estadisticaTBA2);
        estadisticaTBA1.setId(null);
        assertThat(estadisticaTBA1).isNotEqualTo(estadisticaTBA2);
    }
}
