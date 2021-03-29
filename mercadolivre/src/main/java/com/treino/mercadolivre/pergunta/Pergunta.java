package com.treino.mercadolivre.pergunta;

import com.treino.mercadolivre.produto.Produto;
import com.treino.mercadolivre.usuario.Usuario;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Pergunta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String titulo;

    private LocalDateTime instanteCriacao = LocalDateTime.now().withNano(0);

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Produto produto;

    public String getTitulo() {
        return titulo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Produto getProduto() {
        return produto;
    }

    public Pergunta(String titulo, Usuario usuario, Produto produto) {
        this.titulo = titulo;
        this.usuario = usuario;
        this.produto = produto;
    }

    @Deprecated
    public Pergunta() {
    }
}
