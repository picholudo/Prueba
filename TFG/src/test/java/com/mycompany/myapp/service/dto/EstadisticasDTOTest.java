package com.mycompany.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class EstadisticasDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EstadisticasDTO.class);
        EstadisticasDTO estadisticasDTO1 = new EstadisticasDTO();
        estadisticasDTO1.setId(1L);
        EstadisticasDTO estadisticasDTO2 = new EstadisticasDTO();
        assertThat(estadisticasDTO1).isNotEqualTo(estadisticasDTO2);
        estadisticasDTO2.setId(estadisticasDTO1.getId());
        assertThat(estadisticasDTO1).isEqualTo(estadisticasDTO2);
        estadisticasDTO2.setId(2L);
        assertThat(estadisticasDTO1).isNotEqualTo(estadisticasDTO2);
        estadisticasDTO1.setId(null);
        assertThat(estadisticasDTO1).isNotEqualTo(estadisticasDTO2);
    }
}
