package com.mycompany.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class EstadisticaTBADTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EstadisticaTBADTO.class);
        EstadisticaTBADTO estadisticaTBADTO1 = new EstadisticaTBADTO();
        estadisticaTBADTO1.setId(1L);
        EstadisticaTBADTO estadisticaTBADTO2 = new EstadisticaTBADTO();
        assertThat(estadisticaTBADTO1).isNotEqualTo(estadisticaTBADTO2);
        estadisticaTBADTO2.setId(estadisticaTBADTO1.getId());
        assertThat(estadisticaTBADTO1).isEqualTo(estadisticaTBADTO2);
        estadisticaTBADTO2.setId(2L);
        assertThat(estadisticaTBADTO1).isNotEqualTo(estadisticaTBADTO2);
        estadisticaTBADTO1.setId(null);
        assertThat(estadisticaTBADTO1).isNotEqualTo(estadisticaTBADTO2);
    }
}
