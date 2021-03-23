package com.treino.mercadolivre.usuario;

import java.time.LocalDateTime;

public class UsuarioBuilder {
    private String login;
    private String senha;
    private LocalDateTime instanteCadastro = LocalDateTime.now().withNano(0);

    public UsuarioBuilder setLogin(String login) {
        this.login = login;
        return this;
    }

    public UsuarioBuilder setSenha(String senha) {
        this.senha = senha;
        return this;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public Usuario build(){
        return new Usuario(login,new Senha(senha));
    }
}
