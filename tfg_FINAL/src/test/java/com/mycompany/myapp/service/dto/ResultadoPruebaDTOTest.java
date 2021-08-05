package com.mycompany.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class ResultadoPruebaDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ResultadoPruebaDTO.class);
        ResultadoPruebaDTO resultadoPruebaDTO1 = new ResultadoPruebaDTO();
        resultadoPruebaDTO1.setId(1L);
        ResultadoPruebaDTO resultadoPruebaDTO2 = new ResultadoPruebaDTO();
        assertThat(resultadoPruebaDTO1).isNotEqualTo(resultadoPruebaDTO2);
        resultadoPruebaDTO2.setId(resultadoPruebaDTO1.getId());
        assertThat(resultadoPruebaDTO1).isEqualTo(resultadoPruebaDTO2);
        resultadoPruebaDTO2.setId(2L);
        assertThat(resultadoPruebaDTO1).isNotEqualTo(resultadoPruebaDTO2);
        resultadoPruebaDTO1.setId(null);
        assertThat(resultadoPruebaDTO1).isNotEqualTo(resultadoPruebaDTO2);
    }
}
