package com.mycompany.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class EstadisticaFABDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EstadisticaFABDTO.class);
        EstadisticaFABDTO estadisticaFABDTO1 = new EstadisticaFABDTO();
        estadisticaFABDTO1.setId(1L);
        EstadisticaFABDTO estadisticaFABDTO2 = new EstadisticaFABDTO();
        assertThat(estadisticaFABDTO1).isNotEqualTo(estadisticaFABDTO2);
        estadisticaFABDTO2.setId(estadisticaFABDTO1.getId());
        assertThat(estadisticaFABDTO1).isEqualTo(estadisticaFABDTO2);
        estadisticaFABDTO2.setId(2L);
        assertThat(estadisticaFABDTO1).isNotEqualTo(estadisticaFABDTO2);
        estadisticaFABDTO1.setId(null);
        assertThat(estadisticaFABDTO1).isNotEqualTo(estadisticaFABDTO2);
    }
}
