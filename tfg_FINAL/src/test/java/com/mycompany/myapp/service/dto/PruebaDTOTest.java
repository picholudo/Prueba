package com.mycompany.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class PruebaDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PruebaDTO.class);
        PruebaDTO pruebaDTO1 = new PruebaDTO();
        pruebaDTO1.setId(1L);
        PruebaDTO pruebaDTO2 = new PruebaDTO();
        assertThat(pruebaDTO1).isNotEqualTo(pruebaDTO2);
        pruebaDTO2.setId(pruebaDTO1.getId());
        assertThat(pruebaDTO1).isEqualTo(pruebaDTO2);
        pruebaDTO2.setId(2L);
        assertThat(pruebaDTO1).isNotEqualTo(pruebaDTO2);
        pruebaDTO1.setId(null);
        assertThat(pruebaDTO1).isNotEqualTo(pruebaDTO2);
    }
}
