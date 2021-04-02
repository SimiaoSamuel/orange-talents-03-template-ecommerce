package com.treino.mercadolivre.pagamento;

import com.treino.mercadolivre.compra.Compra;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;

public class PayPalGateway implements GatewayUri {
    @Override
    public URI build(UriComponentsBuilder uriBuilder, Compra compra) throws URISyntaxException {
        return uriBuilder.path("/retorno-paypal/{id}").buildAndExpand(compra.getId()).toUri();
    }

    @Override
    public String name() {
        return "paypal";
    }
}
