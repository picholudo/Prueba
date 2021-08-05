package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class InformeTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Informe.class);
        Informe informe1 = new Informe();
        informe1.setId(1L);
        Informe informe2 = new Informe();
        informe2.setId(informe1.getId());
        assertThat(informe1).isEqualTo(informe2);
        informe2.setId(2L);
        assertThat(informe1).isNotEqualTo(informe2);
        informe1.setId(null);
        assertThat(informe1).isNotEqualTo(informe2);
    }
}
