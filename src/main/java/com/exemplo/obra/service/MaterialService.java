package com.exemplo.obra.service;

import com.exemplo.obra.dto.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

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
        BigDecimal areaTotalParedes = BigDecimal.ZERO;
        BigDecimal areaAberturas = BigDecimal.ZERO;

        for (ArestaRequest aresta : request.getArestas()) {
            double areaParede = aresta.getComprimento() * aresta.getAlturaParede();
            areaTotalParedes = areaTotalParedes.add(BigDecimal.valueOf(areaParede));

            if (aresta.isPossuiPorta()) {
                double areaPorta = aresta.getAlturaPorta() * aresta.getLarguraPorta();
                areaAberturas = areaAberturas.add(BigDecimal.valueOf(areaPorta));
            }

            if (aresta.isPossuiJanela()) {
                double areaJanela = aresta.getAlturaJanela() * aresta.getLarguraJanela();
                areaAberturas = areaAberturas.add(BigDecimal.valueOf(areaJanela));
            }
        }

        BigDecimal areaLiquida = areaTotalParedes.subtract(areaAberturas);

        double areaTijolo = request.getAlturaTijolo() * request.getLarguraTijolo();
        
        int quantidadeTijolos = 0;
        int quantidadeTijolosComPerda = 0;

        if (areaTijolo > 0) {
            BigDecimal qtdBase = areaLiquida.divide(BigDecimal.valueOf(areaTijolo), 0, RoundingMode.CEILING);
            quantidadeTijolos = qtdBase.intValue();
            
            double fatorPerda = 1 + (request.getPercentualPerda() / 100.0);
            quantidadeTijolosComPerda = (int) Math.ceil(quantidadeTijolos * fatorPerda);
        }

        return new TijoloResponse(
                areaTotalParedes.setScale(2, RoundingMode.HALF_UP),
                areaLiquida.setScale(2, RoundingMode.HALF_UP),
                areaAberturas.setScale(2, RoundingMode.HALF_UP),
                quantidadeTijolos,
                quantidadeTijolosComPerda,
                "Quantidade de tijolos calculada com sucesso."
        );
    }
}
