package com.treino.mercadolivre.produto;

import com.treino.mercadolivre.caracteristica.Caracteristica;
import com.treino.mercadolivre.caracteristica.CaracteristicaRequest;
import com.treino.mercadolivre.categoria.Categoria;
import com.treino.mercadolivre.categoria.CategoriaRepository;
import com.treino.mercadolivre.usuario.Usuario;
import com.treino.mercadolivre.validation.annotations.ExistInDatabase;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class ProdutoRequest {
    @NotBlank
    private String nome;

    @NotNull
    @Min(1)
    private BigDecimal valor;

    @NotNull
    @Min(1)
    private Integer quantidade;

    @Valid
    @Size(min = 1, message = "Seu produto precisa ter pelo menos uma caracteristica!")
    private HashSet<CaracteristicaRequest> caracteristicas;

    @NotBlank
    @Size(max = 1000)
    private String descricao;

    @NotNull
    @ExistInDatabase(fieldName = "id", klass = Categoria.class)
    private Integer categoriaId;

    public ProdutoRequest(@NotBlank String nome, @NotNull @Min(1) BigDecimal valor,
                          @NotNull @Min(1) Integer quantidade, HashSet<CaracteristicaRequest> caracteristicas,
                          @NotBlank @Size(max = 1000) String descricao, @NotNull Integer categoriaId) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.caracteristicas = caracteristicas;
        this.descricao = descricao;
        this.categoriaId = categoriaId;
    }

    public Produto toProduto(CategoriaRepository categoriaRepository, @NotNull Usuario usuario) {
        Categoria categoria = categoriaRepository.findById(categoriaId).get();

        List<Caracteristica> listaDeCaracteristicas = caracteristicas.stream()
                .map(CaracteristicaRequest::toCaracteristica).collect(Collectors.toList());

        return new Produto(nome, valor, quantidade, listaDeCaracteristicas, descricao, categoria, usuario);
    }
}
