package com.mycompany.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class EvaluacionMapperTest {

    private EvaluacionMapper evaluacionMapper;

    @BeforeEach
    public void setUp() {
        evaluacionMapper = new EvaluacionMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(evaluacionMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(evaluacionMapper.fromId(null)).isNull();
    }
}
