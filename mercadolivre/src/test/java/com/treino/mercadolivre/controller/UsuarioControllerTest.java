package com.treino.mercadolivre.controller;

import com.treino.mercadolivre.usuario.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;

import javax.validation.*;
import java.util.Arrays;
import java.util.Set;


@ExtendWith(SpringExtension.class)
public class UsuarioControllerTest {
    @InjectMocks
    private UsuarioController usuarioController;

    @Mock
    private UsuarioRepository usuarioRepository;

    private ValidatorFactory validatorFactory;
    private  Validator validator;

    @BeforeEach
    public void createValidator() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @AfterEach
    public void close() {
        validatorFactory.close();
    }

    @Test
    @DisplayName("Salva usuario no banco de dados se tudo estiver certo!")
    void saveUserOnDatabaseWhenSucessful(){
        UsuarioBuilder user = new UsuarioBuilder()
                .setLogin("samuel@gmail.com")
                .setSenha("samuel2011");
        ResponseEntity<Void> response = usuarioController.salvandoUsuario(new UsuarioRequest(user.getLogin(), user.getSenha()));
        Assertions.assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    @DisplayName("Não salva usuario no banco de dados se estiver algum erro de validação!")
    void dontSaveUserOnDatabaseWhenUnsucessful(){
        UsuarioBuilder user = new UsuarioBuilder()
                .setLogin("samuelgmail.com")
                .setSenha("");
        UsuarioRequest usuarioRequest = new UsuarioRequest(user.getLogin(), user.getSenha());

        Set<ConstraintViolation<UsuarioRequest>> constraintViolations = validator.validate(usuarioRequest);

        Assertions.assertFalse(constraintViolations.isEmpty());
    }
}
