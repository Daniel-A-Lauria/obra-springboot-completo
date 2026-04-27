package com.exemplo.obra.service;

import com.exemplo.obra.dto.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class MaterialService {

    public ConcretoResponse calcularVolumeConcreto(ConcretoRequest request) {
        // para fazer: incluir o código de calculo do concreto

        int totalProcessado = 0;
        BigDecimal volumeTotal = BigDecimal.ZERO;

        for (ArestaRequest arestaRequest : request.getArestas()) {
         totalProcessado++;
         double surface = arestaRequest.getComprimento() * arestaRequest.getAlturaParede();

         if (arestaRequest.isPossuiPorta()) {
            surface -= arestaRequest.getAlturaPorta() * arestaRequest.getLarguraPorta();
        }

        if (arestaRequest.isPossuiJanela()) {
            surface -= arestaRequest.getAlturaJanela() * arestaRequest.getLarguraJanela();
        }
        
        double volume = surface * arestaRequest.getEspessura();
        volumeTotal = volumeTotal.add(new BigDecimal(volume));
        
        }
        return new ConcretoResponse(volumeTotal , totalProcessado ,
                "Volume de concreto calculado com sucesso."

        );
    }

    public TijoloResponse calcularQuantidadeTijolos(TijoloRequest request) {
        // para fazer: incluir o código de calculo do consumo de tijolos
        return new TijoloResponse(
                new BigDecimal(0),
                new BigDecimal(0),
                new BigDecimal(0),
                0,
                0,
                "Quantidade de tijolos calculada com sucesso."
        );
    }
}
