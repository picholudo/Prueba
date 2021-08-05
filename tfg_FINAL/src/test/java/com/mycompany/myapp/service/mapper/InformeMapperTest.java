package com.mycompany.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class InformeMapperTest {

    private InformeMapper informeMapper;

    @BeforeEach
    public void setUp() {
        informeMapper = new InformeMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(informeMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(informeMapper.fromId(null)).isNull();
    }
}
