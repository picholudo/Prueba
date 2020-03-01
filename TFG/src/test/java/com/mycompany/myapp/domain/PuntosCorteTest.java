package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class PuntosCorteTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PuntosCorte.class);
        PuntosCorte puntosCorte1 = new PuntosCorte();
        puntosCorte1.setId(1L);
        PuntosCorte puntosCorte2 = new PuntosCorte();
        puntosCorte2.setId(puntosCorte1.getId());
        assertThat(puntosCorte1).isEqualTo(puntosCorte2);
        puntosCorte2.setId(2L);
        assertThat(puntosCorte1).isNotEqualTo(puntosCorte2);
        puntosCorte1.setId(null);
        assertThat(puntosCorte1).isNotEqualTo(puntosCorte2);
    }
}
