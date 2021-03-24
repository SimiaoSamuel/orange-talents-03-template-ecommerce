package com.treino.mercadolivre.categoria;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String nome;

    @ManyToOne
    private Categoria categoriaAssociada;

    public String getNome() {
        return nome;
    }

    public Categoria getCategoriaAssociada() {
        return categoriaAssociada;
    }

    public Integer getId() {
        return id;
    }

    public Categoria(String nome) {
        this.nome = nome;
    }

    @Deprecated
    public Categoria(){
    }

    public void setCategoriaAssociada(Categoria categoriaMae) {
        this.categoriaAssociada = categoriaMae;
    }
}
