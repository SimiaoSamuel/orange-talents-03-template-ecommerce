package com.treino.mercadolivre.pagamento;

import com.treino.mercadolivre.compra.Compra;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PagSeguroRequest implements IPagamento{
    @NotNull
    private Integer transacaoId;

    @NotBlank
    private String status = CompraStatus.FALHA.name();

    public PagSeguroRequest(Integer transacaoId, String status) {
        this.transacaoId = transacaoId;
        this.status = status.toUpperCase();
    }

    @Override
    public Integer getTransacaoId() {
        return transacaoId;
    }

    @Override
    public Transacao toTransacao(Compra compra){
        if(status.equalsIgnoreCase("FALHA"))
            return new Transacao(compra,CompraStatus.FALHA, transacaoId);
        else if(status.equalsIgnoreCase("SUCESSO"))
            return new Transacao(compra, CompraStatus.SUCESSO, transacaoId);

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Status inválido!");
    }
}
