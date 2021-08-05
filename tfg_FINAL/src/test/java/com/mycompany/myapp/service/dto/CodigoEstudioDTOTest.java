package com.mycompany.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class CodigoEstudioDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CodigoEstudioDTO.class);
        CodigoEstudioDTO codigoEstudioDTO1 = new CodigoEstudioDTO();
        codigoEstudioDTO1.setId(1L);
        CodigoEstudioDTO codigoEstudioDTO2 = new CodigoEstudioDTO();
        assertThat(codigoEstudioDTO1).isNotEqualTo(codigoEstudioDTO2);
        codigoEstudioDTO2.setId(codigoEstudioDTO1.getId());
        assertThat(codigoEstudioDTO1).isEqualTo(codigoEstudioDTO2);
        codigoEstudioDTO2.setId(2L);
        assertThat(codigoEstudioDTO1).isNotEqualTo(codigoEstudioDTO2);
        codigoEstudioDTO1.setId(null);
        assertThat(codigoEstudioDTO1).isNotEqualTo(codigoEstudioDTO2);
    }
}
