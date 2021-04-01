package com.treino.mercadolivre.compra;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.treino.mercadolivre.pagamento.CompraStatus;
import com.treino.mercadolivre.pagamento.PagamentoGateway;
import com.treino.mercadolivre.produto.Produto;
import com.treino.mercadolivre.usuario.Usuario;
import com.treino.mercadolivre.validation.annotations.ValidEnum;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class CompraRequest {
    @NotNull
    @Min(1)
    private Integer quantidade;

    @NotNull
    private PagamentoGateway pagamento;

    private CompraStatus status = CompraStatus.INICIADA;

    @JsonCreator
    public CompraRequest(@JsonProperty("quantidade")  @NotNull @Min(1) Integer quantidade,
                         @NotNull PagamentoGateway pagamento) {
        this.quantidade = quantidade;
        this.pagamento = pagamento;
    }

    public Compra toCompra(Produto produto, Usuario comprador){
        int quantidadeRestante = produto.getQuantidade() - quantidade;

        if(quantidadeRestante < 0)
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST,"Quantidade invalida");

        produto.setQuantidade(quantidadeRestante);
        return new Compra(quantidade,pagamento, produto, comprador, status);
    }
}
