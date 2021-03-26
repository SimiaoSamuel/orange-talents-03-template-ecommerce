package com.treino.mercadolivre.produto;

import com.treino.mercadolivre.caracteristica.Caracteristica;
import com.treino.mercadolivre.caracteristica.CaracteristicaResponse;
import com.treino.mercadolivre.categoria.CategoriaResponse;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class ProdutoResponse {
    private Integer id;

    private String vendedor;

    private String nome;

    private BigDecimal valor;

    private Integer quantidade;

    private List<CaracteristicaResponse> caracteristicas;

    private String descricao;

    private CategoriaResponse categoria;

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public List<CaracteristicaResponse> getCaracteristicas() {
        return caracteristicas;
    }

    public String getDescricao() {
        return descricao;
    }

    public CategoriaResponse getCategoria() {
        return categoria;
    }

    public String getVendedor() {
        return vendedor;
    }

    public ProdutoResponse(Produto produto) {
        this.caracteristicas = produto.getCaracteristicas().stream().map(CaracteristicaResponse::new)
                .collect(Collectors.toList());
        this.id = produto.getId();
        this.categoria = new CategoriaResponse(produto.getCategoria());
        this.descricao = produto.getDescricao();
        this.quantidade = produto.getQuantidade();
        this.valor = produto.getValor();
        this.nome = produto.getNome();
        this.vendedor = produto.getUsuario().getLogin();
    }
}
