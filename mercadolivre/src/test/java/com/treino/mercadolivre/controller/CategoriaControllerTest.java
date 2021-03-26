package com.treino.mercadolivre.controller;

import com.treino.mercadolivre.categoria.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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
