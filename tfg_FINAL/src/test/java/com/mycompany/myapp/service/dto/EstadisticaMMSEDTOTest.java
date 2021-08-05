package com.mycompany.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class EstadisticaMMSEDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EstadisticaMMSEDTO.class);
        EstadisticaMMSEDTO estadisticaMMSEDTO1 = new EstadisticaMMSEDTO();
        estadisticaMMSEDTO1.setId(1L);
        EstadisticaMMSEDTO estadisticaMMSEDTO2 = new EstadisticaMMSEDTO();
        assertThat(estadisticaMMSEDTO1).isNotEqualTo(estadisticaMMSEDTO2);
        estadisticaMMSEDTO2.setId(estadisticaMMSEDTO1.getId());
        assertThat(estadisticaMMSEDTO1).isEqualTo(estadisticaMMSEDTO2);
        estadisticaMMSEDTO2.setId(2L);
        assertThat(estadisticaMMSEDTO1).isNotEqualTo(estadisticaMMSEDTO2);
        estadisticaMMSEDTO1.setId(null);
        assertThat(estadisticaMMSEDTO1).isNotEqualTo(estadisticaMMSEDTO2);
    }
}
