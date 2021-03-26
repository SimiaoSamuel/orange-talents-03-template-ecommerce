package com.treino.mercadolivre.usuario;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String login;

    @Column(unique = true, length = 100, nullable = false)
    private String senha;

    @Column(nullable = false)
    private LocalDateTime instanteCadastro = LocalDateTime.now().withNano(0);

    public Integer getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    /**
     *
     * @param login email do usuario
     * @param senhaLimpa senha limpa do usuario
     */
    public Usuario(String login, Senha senhaLimpa) {
        this.login = login;
        this.senha = senhaLimpa.hash();
    }

    @Deprecated
    public Usuario() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
