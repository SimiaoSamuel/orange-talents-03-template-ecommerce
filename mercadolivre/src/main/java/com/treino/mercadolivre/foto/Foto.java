package com.treino.mercadolivre.foto;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Foto {
    @Id
    private String uri;

    public String getUri() {
        return uri;
    }

    public Foto(String uri) {
        this.uri = uri;
    }

    @Deprecated
    public Foto() {
    }
}
