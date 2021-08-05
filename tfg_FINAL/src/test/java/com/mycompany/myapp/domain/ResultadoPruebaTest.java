package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class ResultadoPruebaTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ResultadoPrueba.class);
        ResultadoPrueba resultadoPrueba1 = new ResultadoPrueba();
        resultadoPrueba1.setId(1L);
        ResultadoPrueba resultadoPrueba2 = new ResultadoPrueba();
        resultadoPrueba2.setId(resultadoPrueba1.getId());
        assertThat(resultadoPrueba1).isEqualTo(resultadoPrueba2);
        resultadoPrueba2.setId(2L);
        assertThat(resultadoPrueba1).isNotEqualTo(resultadoPrueba2);
        resultadoPrueba1.setId(null);
        assertThat(resultadoPrueba1).isNotEqualTo(resultadoPrueba2);
    }
}
