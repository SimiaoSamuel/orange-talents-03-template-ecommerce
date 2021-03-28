package com.treino.mercadolivre.opiniao;

public class OpiniaoResponse {
    private String titulo;
    private Byte nota;
    private String descricao;
    private String usuario;

    public String getTitulo() {
        return titulo;
    }

    public Byte getNota() {
        return nota;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getUsuario() {
        return usuario;
    }

    public OpiniaoResponse(Opiniao opiniao) {
        this.titulo = opiniao.getTitulo();
        this.descricao = opiniao.getDescricao();
        this.nota = opiniao.getNota();
        this.usuario = opiniao.getUsuario().getLogin();
    }
}
