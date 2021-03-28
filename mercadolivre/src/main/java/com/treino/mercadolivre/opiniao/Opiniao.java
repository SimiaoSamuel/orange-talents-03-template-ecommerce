package com.treino.mercadolivre.opiniao;

import com.treino.mercadolivre.produto.Produto;
import com.treino.mercadolivre.usuario.Usuario;

import javax.persistence.*;

@Entity
public class Opiniao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String titulo;

    private Byte nota;

    private String descricao;

    @OneToOne
    private Usuario usuario;

    @ManyToOne
    private Produto produto;

    public String getTitulo() {
        return titulo;
    }

    public Byte getNota() {
        return nota;
    }

    public String getDescricao() {
        return descricao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Opiniao(String titulo, Byte nota, String descricao, Usuario usuario, Produto produto) {
        this.titulo = titulo;
        this.nota = nota;
        this.descricao = descricao;
        this.usuario = usuario;
        this.produto = produto;
    }

    @Deprecated
    public Opiniao() {
    }
}
