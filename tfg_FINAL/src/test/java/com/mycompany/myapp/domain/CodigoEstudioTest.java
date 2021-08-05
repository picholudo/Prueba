package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class CodigoEstudioTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CodigoEstudio.class);
        CodigoEstudio codigoEstudio1 = new CodigoEstudio();
        codigoEstudio1.setId(1L);
        CodigoEstudio codigoEstudio2 = new CodigoEstudio();
        codigoEstudio2.setId(codigoEstudio1.getId());
        assertThat(codigoEstudio1).isEqualTo(codigoEstudio2);
        codigoEstudio2.setId(2L);
        assertThat(codigoEstudio1).isNotEqualTo(codigoEstudio2);
        codigoEstudio1.setId(null);
        assertThat(codigoEstudio1).isNotEqualTo(codigoEstudio2);
    }
}
