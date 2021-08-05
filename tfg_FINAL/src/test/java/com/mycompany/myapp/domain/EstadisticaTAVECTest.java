package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class EstadisticaTAVECTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EstadisticaTAVEC.class);
        EstadisticaTAVEC estadisticaTAVEC1 = new EstadisticaTAVEC();
        estadisticaTAVEC1.setId(1L);
        EstadisticaTAVEC estadisticaTAVEC2 = new EstadisticaTAVEC();
        estadisticaTAVEC2.setId(estadisticaTAVEC1.getId());
        assertThat(estadisticaTAVEC1).isEqualTo(estadisticaTAVEC2);
        estadisticaTAVEC2.setId(2L);
        assertThat(estadisticaTAVEC1).isNotEqualTo(estadisticaTAVEC2);
        estadisticaTAVEC1.setId(null);
        assertThat(estadisticaTAVEC1).isNotEqualTo(estadisticaTAVEC2);
    }
}
