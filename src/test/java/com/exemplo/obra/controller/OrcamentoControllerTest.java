package com.exemplo.obra.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrcamentoControllerTest {

    @Autowired
    private OrcamentoController orcamentoController;

    @Test
    void PreencherDadosSimularOrcamentoWeb() {
        assertNull(orcamentoController.getConcretoResponse());
        assertNull(orcamentoController.getTijoloResponse());

        orcamentoController.simularDadosPreFeitos();

        assertNotNull(orcamentoController.getConcretoResponse(), "O response do concreto deveria ter sido gerado");
        assertNotNull(orcamentoController.getTijoloResponse(), "O response dos tijolos deveria ter sido gerado");

        assertEquals(0.4050, orcamentoController.getConcretoResponse().getVolumeTotal().doubleValue());
        
        assertEquals(2, orcamentoController.getConcretoResponse().getQuantidadeArestasProcessadas());
        
        assertEquals("Quantidade de tijolos calculada com sucesso.", orcamentoController.getTijoloResponse().getMensagem());
    }
}