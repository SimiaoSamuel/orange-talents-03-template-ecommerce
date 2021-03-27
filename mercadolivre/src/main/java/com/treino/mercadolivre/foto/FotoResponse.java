package com.treino.mercadolivre.foto;

import java.util.List;
import java.util.stream.Collectors;

public class FotoResponse {
    private String uri;

    public String getUri() {
        return uri;
    }

    public FotoResponse(String uri) {
        this.uri = uri;
    }

    public static List<FotoResponse> toResponse(List<Foto> fotos){
        return fotos.stream().map(f -> new FotoResponse(f.getUri())).collect(Collectors.toList());
    }
}
