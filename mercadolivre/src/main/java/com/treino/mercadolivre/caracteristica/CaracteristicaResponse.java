package com.treino.mercadolivre.caracteristica;

import java.util.List;
import java.util.stream.Collectors;

public class CaracteristicaResponse {
    private String nome;

    private String descricao;

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public CaracteristicaResponse(Caracteristica caracteristica) {
        this.nome = caracteristica.getNome();
        this.descricao = caracteristica.getDescricao();
    }

    public List<CaracteristicaResponse> toListaDeCaracteristicaResponse(List<Caracteristica> caracteristicas){
        return caracteristicas.stream().map(CaracteristicaResponse::new).collect(Collectors.toList());
    }
}
