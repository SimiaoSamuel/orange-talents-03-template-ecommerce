package com.treino.mercadolivre.compra;

import java.math.BigDecimal;

public class CompraResponse {
    private String produto;
    private Integer quantidade;
    private String comprador;
    private String vendedor;
    private BigDecimal valor;
    private String status;


    public String getProduto() {
        return produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public String getComprador() {
        return comprador;
    }

    public String getVendedor() {
        return vendedor;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public String getStatus() {
        return status;
    }

    public CompraResponse(Compra compra) {
        this.produto = compra.getProduto().getNome();
        this.quantidade = compra.getQuantidade();
        this.comprador = compra.getComprador().getLogin();
        this.valor = compra.getValor();
        this.status = compra.getStatus().name();
        this.vendedor = compra.getProduto().getUsuario().getLogin();
    }
}
