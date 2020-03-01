package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class PuntuacionPruebaTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PuntuacionPrueba.class);
        PuntuacionPrueba puntuacionPrueba1 = new PuntuacionPrueba();
        puntuacionPrueba1.setId(1L);
        PuntuacionPrueba puntuacionPrueba2 = new PuntuacionPrueba();
        puntuacionPrueba2.setId(puntuacionPrueba1.getId());
        assertThat(puntuacionPrueba1).isEqualTo(puntuacionPrueba2);
        puntuacionPrueba2.setId(2L);
        assertThat(puntuacionPrueba1).isNotEqualTo(puntuacionPrueba2);
        puntuacionPrueba1.setId(null);
        assertThat(puntuacionPrueba1).isNotEqualTo(puntuacionPrueba2);
    }
}
