package com.mycompany.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class EdadTipoPruebaMapperTest {

    private EdadTipoPruebaMapper edadTipoPruebaMapper;

    @BeforeEach
    public void setUp() {
        edadTipoPruebaMapper = new EdadTipoPruebaMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(edadTipoPruebaMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(edadTipoPruebaMapper.fromId(null)).isNull();
    }
}
