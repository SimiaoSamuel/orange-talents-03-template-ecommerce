package com.treino.mercadolivre.pagamento;

import com.treino.mercadolivre.compra.Compra;

public interface IPagamento {
    Integer getTransacaoId();
    Transacao toTransacao(Compra compra);
}
