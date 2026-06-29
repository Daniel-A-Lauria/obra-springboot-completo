package com.exemplo.obra.controller;

import com.exemplo.obra.dto.*;
import com.exemplo.obra.service.MaterialService;
import jakarta.faces.view.ViewScoped;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.Serializable;
import java.util.ArrayList;

@Component
@ViewScoped
public class OrcamentoController implements Serializable {

    @Autowired
    private MaterialService materialService;

    private ConcretoRequest concretoRequest = new ConcretoRequest();
    private TijoloRequest tijoloRequest = new TijoloRequest();
    private ConcretoResponse concretoResponse;
    private TijoloResponse tijoloResponse;

    public OrcamentoController() {
        concretoRequest.setArestas(new ArrayList<>());
        tijoloRequest.setArestas(new ArrayList<>());
    }

    public void simularDadosPreFeitos() {
        this.concretoRequest.getArestas().clear();
        this.tijoloRequest.getArestas().clear();

        ArestaRequest p1 = new ArestaRequest();
        p1.setId("P1_SALA"); 
        p1.setComprimento(4.0); 
        p1.setEspessura(0.15); 
        p1.setAlturaParede(3.0);
        p1.setPossuiJanela(true); 
        p1.setAlturaJanela(1.0); 
        p1.setLarguraJanela(2.0);

        ArestaRequest p2 = new ArestaRequest();
        p2.setId("P2_QUARTO"); 
        p2.setComprimento(4.0); 
        p2.setEspessura(0.15); 
        p2.setAlturaParede(3.0);
        p2.setPossuiPorta(true); 
        p2.setAlturaPorta(2.0); 
        p2.setLarguraPorta(1.0);

        this.concretoRequest.getArestas().add(p1); this.concretoRequest.getArestas().add(p2);
        this.tijoloRequest.getArestas().add(p1); this.tijoloRequest.getArestas().add(p2);

        this.concretoRequest.setAlturaViga(0.30);
        this.tijoloRequest.setAlturaTijolo(0.19);
        this.tijoloRequest.setLarguraTijolo(0.19);
        this.tijoloRequest.setPercentualPerda(10.0);

        this.concretoResponse = materialService.calcularVolumeConcreto(concretoRequest);
        this.tijoloResponse = materialService.calcularQuantidadeTijolos(tijoloRequest);
    }

    public ConcretoRequest getConcretoRequest() { return concretoRequest; }
    public TijoloRequest getTijoloRequest() { return tijoloRequest; }
    public ConcretoResponse getConcretoResponse() { return concretoResponse; }
    public TijoloResponse getTijoloResponse() { return tijoloResponse; }
}