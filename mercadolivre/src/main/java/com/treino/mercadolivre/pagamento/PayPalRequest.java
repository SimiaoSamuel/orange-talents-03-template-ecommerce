package com.treino.mercadolivre.pagamento;

import com.treino.mercadolivre.compra.Compra;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

public class PayPalRequest implements IPagamento{
    @NotNull
    private Integer transacaoId;

    @NotNull @Max(1)
    private Integer status = 0;

    public PayPalRequest(Integer transacaoId, Integer status) {
        this.transacaoId = transacaoId;
        this.status = status;
    }

    @Override
    public Integer getTransacaoId() {
        return transacaoId;
    }

    @Override
    public Transacao toTransacao(Compra compra){
        if(status == 0)
            return new Transacao(compra,CompraStatus.FALHA, transacaoId);

        return new Transacao(compra, CompraStatus.SUCESSO, transacaoId);
    }
}
