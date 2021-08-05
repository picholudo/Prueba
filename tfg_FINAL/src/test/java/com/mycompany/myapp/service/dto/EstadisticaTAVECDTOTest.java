package com.mycompany.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class EstadisticaTAVECDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EstadisticaTAVECDTO.class);
        EstadisticaTAVECDTO estadisticaTAVECDTO1 = new EstadisticaTAVECDTO();
        estadisticaTAVECDTO1.setId(1L);
        EstadisticaTAVECDTO estadisticaTAVECDTO2 = new EstadisticaTAVECDTO();
        assertThat(estadisticaTAVECDTO1).isNotEqualTo(estadisticaTAVECDTO2);
        estadisticaTAVECDTO2.setId(estadisticaTAVECDTO1.getId());
        assertThat(estadisticaTAVECDTO1).isEqualTo(estadisticaTAVECDTO2);
        estadisticaTAVECDTO2.setId(2L);
        assertThat(estadisticaTAVECDTO1).isNotEqualTo(estadisticaTAVECDTO2);
        estadisticaTAVECDTO1.setId(null);
        assertThat(estadisticaTAVECDTO1).isNotEqualTo(estadisticaTAVECDTO2);
    }
}
