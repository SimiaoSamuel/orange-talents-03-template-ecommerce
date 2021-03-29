package com.treino.mercadolivre.pergunta;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.treino.mercadolivre.produto.Produto;
import com.treino.mercadolivre.usuario.Usuario;

import javax.validation.constraints.NotBlank;

public class PerguntaRequest {
    @NotBlank
    private String titulo;

    @JsonCreator
    public PerguntaRequest(@JsonProperty("titulo") @NotBlank String titulo) {
        this.titulo = titulo;
    }

    public Pergunta toPergunta(Usuario usuario, Produto produto){
        return new Pergunta(titulo,usuario,produto);
    }
}
