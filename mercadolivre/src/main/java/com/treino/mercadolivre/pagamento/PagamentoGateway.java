package com.treino.mercadolivre.pagamento;

import com.treino.mercadolivre.compra.Compra;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;

public enum PagamentoGateway {
    PAYPAL(new PayPalGateway()),
    PAGSEGURO(new PagSeguroGateway());

    private GatewayUri gateway;

    PagamentoGateway(GatewayUri gateway){
        this.gateway = gateway;
    }

    public URI getGateway(UriComponentsBuilder uriBuilder, Compra compra) throws URISyntaxException {
        return gateway.build(uriBuilder ,compra);
    }

    public String getName(){
        return gateway.name();
    }
}
