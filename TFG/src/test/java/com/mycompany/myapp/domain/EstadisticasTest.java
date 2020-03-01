package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class EstadisticasTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Estadisticas.class);
        Estadisticas estadisticas1 = new Estadisticas();
        estadisticas1.setId(1L);
        Estadisticas estadisticas2 = new Estadisticas();
        estadisticas2.setId(estadisticas1.getId());
        assertThat(estadisticas1).isEqualTo(estadisticas2);
        estadisticas2.setId(2L);
        assertThat(estadisticas1).isNotEqualTo(estadisticas2);
        estadisticas1.setId(null);
        assertThat(estadisticas1).isNotEqualTo(estadisticas2);
    }
}
