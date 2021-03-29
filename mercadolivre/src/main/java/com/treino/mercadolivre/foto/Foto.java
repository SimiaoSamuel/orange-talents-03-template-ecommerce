package com.treino.mercadolivre.foto;

import com.treino.mercadolivre.validation.annotations.UniqueValue;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Foto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

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
