package com.treino.mercadolivre.usuario;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String login;

    @Column(unique = true, length = 100, nullable = false)
    private String senha;

    @Column(nullable = false)
    private LocalDateTime instanteCadastro = LocalDateTime.now().withNano(0);

    /**
     *
     * @param login email do usuario
     * @param senhaLimpa senha limpa do usuario
     */
    public Usuario(String login, Senha senhaLimpa) {
        this.login = login;
        this.senha = senhaLimpa.hash();
    }
}
