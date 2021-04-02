package com.treino.mercadolivre.pagamento;

import com.treino.mercadolivre.compra.Compra;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;

public interface GatewayUri {
    URI build(UriComponentsBuilder uriBuilder,Compra compra) throws URISyntaxException;
    String name();
}
