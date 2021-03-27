package com.treino.mercadolivre.caracteristica;

import com.treino.mercadolivre.validation.annotations.UniqueValue;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class CaracteristicaRequest {
    @NotBlank
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CaracteristicaRequest that = (CaracteristicaRequest) o;
        return Objects.equals(nome, that.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }
}
