package com.mycompany.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class PruebaMapperTest {

    private PruebaMapper pruebaMapper;

    @BeforeEach
    public void setUp() {
        pruebaMapper = new PruebaMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(pruebaMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(pruebaMapper.fromId(null)).isNull();
    }
}
