package com.mycompany.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class EdadTipoPruebaDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EdadTipoPruebaDTO.class);
        EdadTipoPruebaDTO edadTipoPruebaDTO1 = new EdadTipoPruebaDTO();
        edadTipoPruebaDTO1.setId(1L);
        EdadTipoPruebaDTO edadTipoPruebaDTO2 = new EdadTipoPruebaDTO();
        assertThat(edadTipoPruebaDTO1).isNotEqualTo(edadTipoPruebaDTO2);
        edadTipoPruebaDTO2.setId(edadTipoPruebaDTO1.getId());
        assertThat(edadTipoPruebaDTO1).isEqualTo(edadTipoPruebaDTO2);
        edadTipoPruebaDTO2.setId(2L);
        assertThat(edadTipoPruebaDTO1).isNotEqualTo(edadTipoPruebaDTO2);
        edadTipoPruebaDTO1.setId(null);
        assertThat(edadTipoPruebaDTO1).isNotEqualTo(edadTipoPruebaDTO2);
    }
}
