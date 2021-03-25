package com.treino.mercadolivre.controller;

import com.treino.mercadolivre.categoria.*;
import com.treino.mercadolivre.usuario.Senha;
import com.treino.mercadolivre.usuario.Usuario;
import com.treino.mercadolivre.usuario.UsuarioRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.SpringConstraintValidatorFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Set;

@ExtendWith(SpringExtension.class)
public class CategoriaControllerTest {
    @InjectMocks
    CategoriaController categoriaController;

    @Mock
    CategoriaRepository categoriaRepository;

    @Test
    public void saveCategoriaAndReturnResponseWhenSucessful(){
        CategoriaRequest celular = new CategoriaRequest("Celular", null);
        Categoria categoria = celular.toCategoria(categoriaRepository);

        ResponseEntity<CategoriaResponse> categoriaResponse = categoriaController.salvarCategoria(celular);

        Assertions.assertTrue(categoriaResponse.hasBody());
        Assertions.assertEquals(categoria.getNome(), categoriaResponse.getBody().getNome());
    }

}
