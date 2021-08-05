package com.mycompany.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class CodigoEstudioMapperTest {

    private CodigoEstudioMapper codigoEstudioMapper;

    @BeforeEach
    public void setUp() {
        codigoEstudioMapper = new CodigoEstudioMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(codigoEstudioMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(codigoEstudioMapper.fromId(null)).isNull();
    }
}
