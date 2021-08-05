package com.mycompany.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class ResultadoPruebaMapperTest {

    private ResultadoPruebaMapper resultadoPruebaMapper;

    @BeforeEach
    public void setUp() {
        resultadoPruebaMapper = new ResultadoPruebaMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(resultadoPruebaMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(resultadoPruebaMapper.fromId(null)).isNull();
    }
}
