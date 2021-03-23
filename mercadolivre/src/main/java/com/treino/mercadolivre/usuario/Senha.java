package com.treino.mercadolivre.usuario;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class Senha {
    @NotBlank
    @Size(min = 6)
    private String value;

    public String hash(){
        return new BCryptPasswordEncoder().encode(this.value);
    }

    public Senha(@NotBlank @Size(min = 6) String value) {
        this.value = value;
    }
}
