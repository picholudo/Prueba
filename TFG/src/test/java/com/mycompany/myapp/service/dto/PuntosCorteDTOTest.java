package com.mycompany.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class PuntosCorteDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PuntosCorteDTO.class);
        PuntosCorteDTO puntosCorteDTO1 = new PuntosCorteDTO();
        puntosCorteDTO1.setId(1L);
        PuntosCorteDTO puntosCorteDTO2 = new PuntosCorteDTO();
        assertThat(puntosCorteDTO1).isNotEqualTo(puntosCorteDTO2);
        puntosCorteDTO2.setId(puntosCorteDTO1.getId());
        assertThat(puntosCorteDTO1).isEqualTo(puntosCorteDTO2);
        puntosCorteDTO2.setId(2L);
        assertThat(puntosCorteDTO1).isNotEqualTo(puntosCorteDTO2);
        puntosCorteDTO1.setId(null);
        assertThat(puntosCorteDTO1).isNotEqualTo(puntosCorteDTO2);
    }
}
