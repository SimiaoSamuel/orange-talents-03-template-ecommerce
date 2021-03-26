package com.treino.mercadolivre.caracteristica;

import com.treino.mercadolivre.validation.annotations.UniqueValue;

import javax.validation.constraints.NotBlank;

public class CaracteristicaRequest {
    @NotBlank
    @UniqueValue(klass = Caracteristica.class, fieldName = "nome")
    private String nome;

    @NotBlank
    private String descricao;

    public CaracteristicaRequest(@NotBlank @UniqueValue(klass = Caracteristica.class, fieldName = "nome") String nome, @NotBlank String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public Caracteristica toCaracteristica() {
        return new Caracteristica(nome, descricao);
    }
}
