package com.treino.mercadolivre.pagamento;

import com.treino.mercadolivre.compra.Compra;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;

public class PayPalGateway implements Gateway{
    @Override
    public URI build(Compra compra) throws URISyntaxException {
        return new URI("http://www.paypal?buyerId="+compra.getId()+"&redirectUrl=");
    }
}
