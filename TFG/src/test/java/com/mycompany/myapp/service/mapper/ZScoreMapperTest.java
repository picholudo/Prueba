package com.mycompany.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class ZScoreMapperTest {

    private ZScoreMapper zScoreMapper;

    @BeforeEach
    public void setUp() {
        zScoreMapper = new ZScoreMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(zScoreMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(zScoreMapper.fromId(null)).isNull();
    }
}
