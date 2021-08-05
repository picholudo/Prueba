package com.mycompany.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class EstadisticaPuntoCorteDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EstadisticaPuntoCorteDTO.class);
        EstadisticaPuntoCorteDTO estadisticaPuntoCorteDTO1 = new EstadisticaPuntoCorteDTO();
        estadisticaPuntoCorteDTO1.setId(1L);
        EstadisticaPuntoCorteDTO estadisticaPuntoCorteDTO2 = new EstadisticaPuntoCorteDTO();
        assertThat(estadisticaPuntoCorteDTO1).isNotEqualTo(estadisticaPuntoCorteDTO2);
        estadisticaPuntoCorteDTO2.setId(estadisticaPuntoCorteDTO1.getId());
        assertThat(estadisticaPuntoCorteDTO1).isEqualTo(estadisticaPuntoCorteDTO2);
        estadisticaPuntoCorteDTO2.setId(2L);
        assertThat(estadisticaPuntoCorteDTO1).isNotEqualTo(estadisticaPuntoCorteDTO2);
        estadisticaPuntoCorteDTO1.setId(null);
        assertThat(estadisticaPuntoCorteDTO1).isNotEqualTo(estadisticaPuntoCorteDTO2);
    }
}
