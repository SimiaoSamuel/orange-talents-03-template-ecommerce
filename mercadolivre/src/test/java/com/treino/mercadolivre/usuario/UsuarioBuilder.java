package com.treino.mercadolivre.usuario;

import java.time.LocalDateTime;

public class UsuarioBuilder {
    private String login;
    private String senha;
    private LocalDateTime instanteCadastro = LocalDateTime.now().withNano(0);

    public String getLogin() {
        return login;
    }

    public UsuarioBuilder setLogin(String login) {
        this.login = login;
        return this;
    }

    public String getSenha() {
        return senha;
    }

    public UsuarioBuilder setSenha(String senha) {
        this.senha = senha;
        return this;
    }

    public LocalDateTime getInstanteCadastro() {
        return instanteCadastro;
    }

    public UsuarioBuilder setInstanteCadastro(LocalDateTime instanteCadastro) {
        this.instanteCadastro = instanteCadastro;
        return this;
    }
}
