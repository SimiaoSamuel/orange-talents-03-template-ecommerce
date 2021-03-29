package com.treino.mercadolivre.pergunta;

public class PerguntaResponse {
    private String titulo;
    private String produto;
    private String usuario;

    public String getTitulo() {
        return titulo;
    }

    public String getProduto() {
        return produto;
    }

    public String getUsuario() {
        return usuario;
    }

    public PerguntaResponse(Pergunta pergunta) {
        this.titulo = pergunta.getTitulo();
        this.produto = pergunta.getProduto().getNome();
        this.usuario = pergunta.getUsuario().getLogin();
    }
}
