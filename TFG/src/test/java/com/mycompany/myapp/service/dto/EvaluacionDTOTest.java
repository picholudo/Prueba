package com.mycompany.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class EvaluacionDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EvaluacionDTO.class);
        EvaluacionDTO evaluacionDTO1 = new EvaluacionDTO();
        evaluacionDTO1.setId(1L);
        EvaluacionDTO evaluacionDTO2 = new EvaluacionDTO();
        assertThat(evaluacionDTO1).isNotEqualTo(evaluacionDTO2);
        evaluacionDTO2.setId(evaluacionDTO1.getId());
        assertThat(evaluacionDTO1).isEqualTo(evaluacionDTO2);
        evaluacionDTO2.setId(2L);
        assertThat(evaluacionDTO1).isNotEqualTo(evaluacionDTO2);
        evaluacionDTO1.setId(null);
        assertThat(evaluacionDTO1).isNotEqualTo(evaluacionDTO2);
    }
}
