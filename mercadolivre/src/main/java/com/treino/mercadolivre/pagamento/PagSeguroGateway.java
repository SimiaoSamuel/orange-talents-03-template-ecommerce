package com.treino.mercadolivre.pagamento;

import com.treino.mercadolivre.compra.Compra;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;

public class PagSeguroGateway implements GatewayUri {
    @Override
    public URI build(UriComponentsBuilder uriBuilder ,Compra compra) throws URISyntaxException {
        return uriBuilder.path("/retorno-pagseguro/{id}").buildAndExpand(compra.getId()).toUri();
    }

    @Override
    public String name() {
        return "pagseguro";
    }
}
