package com.treino.mercadolivre.pagamento;

import com.treino.mercadolivre.compra.Compra;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

public class DefaultGateway {
    @NotNull
    private Integer transacaoId;

    @NotNull
    private CompraStatus status;

    private Map<String, IPagamento> tiposDePagamento = new HashMap<>();

    public DefaultGateway(Integer transacaoId, CompraStatus status) {
        this.transacaoId = transacaoId;
        this.status = status;
    }

    public IPagamento toNewGateway(String tipoPagamento){
        populateMap();
        IPagamento iPagamento = tiposDePagamento.get(tipoPagamento);
        if(iPagamento == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Tipo de pagamento inválido");

        return iPagamento;
    }

    public void populateMap(){
        tiposDePagamento.put("pagseguro",new PagSeguroRequest(transacaoId,status.name()));
        tiposDePagamento.put("paypal",new PayPalRequest(transacaoId,status.ordinal()));
    }
}
