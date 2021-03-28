package com.treino.mercadolivre.opiniao;

import com.treino.mercadolivre.produto.Produto;
import com.treino.mercadolivre.usuario.Usuario;

import javax.validation.constraints.*;

public class OpiniaoRequest {
    @NotBlank
    private String titulo;

    @NotNull
    @Min(1)
    @Max(5)
    private Byte nota;

    @NotBlank
    @Size(max = 500)
    private String descricao;

    public OpiniaoRequest(@NotBlank String titulo, @NotNull @Min(1) @Max(5) Byte nota, @NotBlank @Size(max = 500) String descricao) {
        this.titulo = titulo;
        this.nota = nota;
        this.descricao = descricao;
    }

    public Opiniao toOpiniao(Usuario usuario, Produto produto){
        return new Opiniao(titulo,nota,descricao,usuario,produto);
    }
}
