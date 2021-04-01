package com.treino.mercadolivre.pagamento;

import com.treino.mercadolivre.compra.Compra;

import java.net.URI;
import java.net.URISyntaxException;

public enum PagamentoGateway {
    PAYPAL(new PayPalGateway()),
    PAGSEGURO(new PagSeguroGateway());

    private Gateway gateway;

    PagamentoGateway(Gateway gateway){
        this.gateway = gateway;
    }

    public URI getGateway(Compra compra) throws URISyntaxException {
        return gateway.build(compra);
    }
}
