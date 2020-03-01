package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class ZScoreTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ZScore.class);
        ZScore zScore1 = new ZScore();
        zScore1.setId(1L);
        ZScore zScore2 = new ZScore();
        zScore2.setId(zScore1.getId());
        assertThat(zScore1).isEqualTo(zScore2);
        zScore2.setId(2L);
        assertThat(zScore1).isNotEqualTo(zScore2);
        zScore1.setId(null);
        assertThat(zScore1).isNotEqualTo(zScore2);
    }
}
