package com.treino.mercadolivre.usuario;

import com.treino.mercadolivre.validation.annotations.UniqueValue;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UsuarioRequest {
    @NotBlank(message = "Não pode deixar o email em branco")
    @Email(message = "Email inválido")
    @UniqueValue(fieldName = "login", klass = Usuario.class)
    private String login;

    @NotBlank(message = "Não pode deixar a senha em branco")
    @Size(min = 6)
    private String senha;

    public UsuarioRequest(@NotBlank @Email String login, @NotBlank @Size(min = 6) String senha) {
        this.login = login;
        this.senha = senha;
    }

    public Usuario toUsuario() {
        return new Usuario(login,new Senha(senha));
    }

    public UsernamePasswordAuthenticationToken toAuthToken(){
        return new UsernamePasswordAuthenticationToken(login,senha);
    }
}
