package com.treino.mercadolivre.usuario;

import java.util.List;
import java.util.stream.Collectors;

public class UsuarioResponse {
    private String login;

    public String getUser() {
        return login;
    }

    public UsuarioResponse(Usuario user) {
        this.login = user.getLogin();
    }

    public static List<UsuarioResponse> toListUserResponse(List<Usuario> usuarios){
        return usuarios.stream().map(UsuarioResponse::new).collect(Collectors.toList());
    }
}
