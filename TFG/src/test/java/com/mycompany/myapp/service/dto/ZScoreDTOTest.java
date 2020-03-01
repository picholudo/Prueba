package com.mycompany.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class ZScoreDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ZScoreDTO.class);
        ZScoreDTO zScoreDTO1 = new ZScoreDTO();
        zScoreDTO1.setId(1L);
        ZScoreDTO zScoreDTO2 = new ZScoreDTO();
        assertThat(zScoreDTO1).isNotEqualTo(zScoreDTO2);
        zScoreDTO2.setId(zScoreDTO1.getId());
        assertThat(zScoreDTO1).isEqualTo(zScoreDTO2);
        zScoreDTO2.setId(2L);
        assertThat(zScoreDTO1).isNotEqualTo(zScoreDTO2);
        zScoreDTO1.setId(null);
        assertThat(zScoreDTO1).isNotEqualTo(zScoreDTO2);
    }
}
