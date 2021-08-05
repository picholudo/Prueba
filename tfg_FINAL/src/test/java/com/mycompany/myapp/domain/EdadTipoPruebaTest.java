package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class EdadTipoPruebaTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EdadTipoPrueba.class);
        EdadTipoPrueba edadTipoPrueba1 = new EdadTipoPrueba();
        edadTipoPrueba1.setId(1L);
        EdadTipoPrueba edadTipoPrueba2 = new EdadTipoPrueba();
        edadTipoPrueba2.setId(edadTipoPrueba1.getId());
        assertThat(edadTipoPrueba1).isEqualTo(edadTipoPrueba2);
        edadTipoPrueba2.setId(2L);
        assertThat(edadTipoPrueba1).isNotEqualTo(edadTipoPrueba2);
        edadTipoPrueba1.setId(null);
        assertThat(edadTipoPrueba1).isNotEqualTo(edadTipoPrueba2);
    }
}
