package com.mycompany.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class PuntuacionPruebaDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PuntuacionPruebaDTO.class);
        PuntuacionPruebaDTO puntuacionPruebaDTO1 = new PuntuacionPruebaDTO();
        puntuacionPruebaDTO1.setId(1L);
        PuntuacionPruebaDTO puntuacionPruebaDTO2 = new PuntuacionPruebaDTO();
        assertThat(puntuacionPruebaDTO1).isNotEqualTo(puntuacionPruebaDTO2);
        puntuacionPruebaDTO2.setId(puntuacionPruebaDTO1.getId());
        assertThat(puntuacionPruebaDTO1).isEqualTo(puntuacionPruebaDTO2);
        puntuacionPruebaDTO2.setId(2L);
        assertThat(puntuacionPruebaDTO1).isNotEqualTo(puntuacionPruebaDTO2);
        puntuacionPruebaDTO1.setId(null);
        assertThat(puntuacionPruebaDTO1).isNotEqualTo(puntuacionPruebaDTO2);
    }
}
