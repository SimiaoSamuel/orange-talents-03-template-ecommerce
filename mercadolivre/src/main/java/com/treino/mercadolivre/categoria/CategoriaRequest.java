package com.treino.mercadolivre.categoria;

import com.treino.mercadolivre.validation.annotations.ExistInDatabase;
import com.treino.mercadolivre.validation.annotations.UniqueValue;

import javax.validation.constraints.NotBlank;

public class CategoriaRequest {
    @NotBlank @UniqueValue(fieldName = "nome", klass = Categoria.class)
    private String nome;

    @ExistInDatabase(fieldName = "id", klass = Categoria.class, message = "categoria mãe não encontrada no banco")
    private Integer categoriaMae;

    public CategoriaRequest(@NotBlank String nome, Integer categoriaMae) {
        this.nome = nome;
        this.categoriaMae = categoriaMae;
    }

    public Categoria toCategoria(CategoriaRepository categoriaRepository){
        Categoria categoria = new Categoria(nome);
        if(categoriaMae != null) {
            Categoria categoriaEncontrada = categoriaRepository.findById(categoriaMae).get();
            categoria.setCategoriaAssociada(categoriaEncontrada);
        }
        return categoria;
    }
}
