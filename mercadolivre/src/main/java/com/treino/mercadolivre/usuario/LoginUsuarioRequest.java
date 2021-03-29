package com.treino.mercadolivre.usuario;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.NotBlank;

public class LoginUsuarioRequest {
    @NotBlank
    private String login;

    @NotBlank
    private String senha;

    public LoginUsuarioRequest(@NotBlank String login, @NotBlank String senha) {
        this.login = login;
        this.senha = senha;
    }

    public UsernamePasswordAuthenticationToken toAuthToken(){
        return new UsernamePasswordAuthenticationToken(login,senha);
    }
}
