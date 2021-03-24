package com.treino.mercadolivre.categoria;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CategoriaResponse {
    private Integer id;

    private String nome;


    private List<String> categorias = new ArrayList<>();

    public List<String> getCategorias() {
        return categorias;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }


    public CategoriaResponse(Categoria categoria) {
        Categoria categoriaSuporte = categoria.getCategoriaAssociada();
        while(categoriaSuporte != null){
            categorias.add(categoriaSuporte.getNome());
            categoriaSuporte = categoriaSuporte.getCategoriaAssociada();
        }
        this.id = categoria.getId();
        this.nome = categoria.getNome();
    }

    public static List<CategoriaResponse> toListCategoriaResponse(List<Categoria> categorias) {
        return categorias.stream().map(CategoriaResponse::new).collect(Collectors.toList());
    }
}
