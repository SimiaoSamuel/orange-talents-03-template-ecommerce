package com.treino.mercadolivre.annotation;

import com.treino.mercadolivre.caracteristica.Caracteristica;
import com.treino.mercadolivre.caracteristica.CaracteristicaRequest;
import com.treino.mercadolivre.usuario.Senha;
import com.treino.mercadolivre.usuario.Usuario;
import com.treino.mercadolivre.usuario.UsuarioRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.SpringConstraintValidatorFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UniqueValueTest {
    @PersistenceContext
    private EntityManager em;
    private LocalValidatorFactoryBean validator;
    @Autowired
    private ConfigurableApplicationContext applicationContext;

    @BeforeEach
    private void setUpEach() {
        SpringConstraintValidatorFactory springConstraintValidatorFactory =
                new SpringConstraintValidatorFactory(
                        applicationContext.getAutowireCapableBeanFactory()
                );

        validator = new LocalValidatorFactoryBean();
        validator.setConstraintValidatorFactory(springConstraintValidatorFactory);
        validator.setApplicationContext(applicationContext);
        validator.afterPropertiesSet();
    }

    @Test
    @Transactional
    @DisplayName("Deve retornar erro na constraint se for passado um login que já tenha no banco")
    public void naoDeveTerLoginRepetido() {
        Usuario usuario = new Usuario("samuel@gmail.com", new Senha("xpto"));
        em.persist(usuario);

        UsuarioRequest usuarioRequest = new UsuarioRequest("samuel@gmail.com", "password");
        Set<ConstraintViolation<Object>> validate = validator.validate(usuarioRequest);
        ConstraintViolation<Object> errors = validate.iterator().next();

        assertEquals("login", errors.getPropertyPath().toString());
    }

    @Test
    @Transactional
    @DisplayName("Não deve dar erro se o login passado não tiver no banco de dados")
    public void deveDeixarPassarLoginDiferentes() {
        Usuario usuario = new Usuario("samuel@gmail.com", new Senha("xpto"));
        em.persist(usuario);

        UsuarioRequest usuarioRequest = new UsuarioRequest("sam@gmail.com", "password");
        Set<ConstraintViolation<UsuarioRequest>> errors = validator.validate(usuarioRequest);

        assertTrue(errors.isEmpty());
    }

    @Test
    @Transactional
    @DisplayName("Não deve deixar passar uma lista de categorias que tenha caracteristicas iguais ou que já existem")
    public void deveDeixarPassarCaracteristicasRepetidas() {
        Caracteristica caracteristica = new Caracteristica("nome", "desc");
        em.persist(caracteristica);

        CaracteristicaRequest caracteristicaRequest = new CaracteristicaRequest("nome", "descrição");
        Set<ConstraintViolation<CaracteristicaRequest>> errors = validator.validate(caracteristicaRequest);

        assertFalse(errors.isEmpty());
    }
}
