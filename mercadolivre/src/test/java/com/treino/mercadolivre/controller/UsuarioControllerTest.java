package com.treino.mercadolivre.controller;

import com.treino.mercadolivre.usuario.*;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.SpringConstraintValidatorFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class UsuarioControllerTest {
    @InjectMocks
    private UsuarioController usuarioController;

    @Mock
    private UsuarioRepository usuarioRepository;

    @PersistenceContext
    private EntityManager em;
    @Autowired
    private ConfigurableApplicationContext applicationContext;
    private LocalValidatorFactoryBean validator;

    @BeforeEach
    private void setUpEach() {
        SpringConstraintValidatorFactory springConstraintValidatorFactory =
                new SpringConstraintValidatorFactory(
                        applicationContext.getAutowireCapableBeanFactory());

        validator = new LocalValidatorFactoryBean();
        validator.setConstraintValidatorFactory(springConstraintValidatorFactory);
        validator.setApplicationContext(applicationContext);
        validator.afterPropertiesSet();
    }

    @Test
    @DisplayName("Salva usuario no banco de dados se tudo estiver certo!")
    void saveUserOnDatabaseWhenSucessful(){
        UsuarioBuilder user = new UsuarioBuilder()
                .setLogin("samuel@gmail.com")
                .setSenha("samuel2011");

        UsuarioRequest usuarioRequest = new UsuarioRequest(user.getLogin(), user.getSenha());
        ResponseEntity < Void > response = usuarioController.salvandoUsuario(usuarioRequest);

        Assertions.assertEquals(200, response.getStatusCodeValue());
    }

    /*@Test
    @DisplayName("Não salva usuario no banco de dados se tiver algum erro de validação!")
    void dontSaveUserOnDatabaseWhenUnsucessful(){
        UsuarioRequest usuarioRequest = new UsuarioRequest("samuelgmail.com","");

        Set<ConstraintViolation<UsuarioRequest>> constraintViolations = validator.validate(usuarioRequest);

        Usuario user = usuarioRequest.toUsuario();

        usuarioController.salvandoUsuario(usuarioRequest);

        usuarioRepository.save(user);
        List<Usuario> lista = usuarioRepository.findAll();

        Assertions.assertTrue(lista.isEmpty());
    }*/
}
