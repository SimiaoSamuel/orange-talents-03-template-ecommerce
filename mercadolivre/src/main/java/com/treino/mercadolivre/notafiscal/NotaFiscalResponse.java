package com.treino.mercadolivre.notafiscal;

import com.treino.mercadolivre.compra.Compra;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class NotaFiscalResponse {
    private String produto;
    private Integer quantidade;
    private BigDecimal valor;
    private String comprador;
    private String vendedor;
    private LocalDateTime emissao = LocalDateTime.now().withNano(0);

    public String getProduto() {
        return produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public String getComprador() {
        return comprador;
    }

    public String getVendedor() {
        return vendedor;
    }

    public LocalDateTime getEmissao() {
        return emissao;
    }

    public NotaFiscalResponse(Compra compra) {
        this.produto = compra.getProduto().getNome();
        this.quantidade = compra.getQuantidade();
        this.valor = compra.getValor();
        this.vendedor = compra.getProduto().getUsuario().getLogin();
        this.comprador = compra.getComprador().getLogin();
    }
}
