package com.treino.mercadolivre.produto;

import com.treino.mercadolivre.caracteristica.Caracteristica;
import com.treino.mercadolivre.categoria.Categoria;
import com.treino.mercadolivre.usuario.Senha;
import com.treino.mercadolivre.usuario.Usuario;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

public class ProdutoTeste {

    @Test
    void teste(){
        Categoria categoria = new Categoria("categoria");
        Usuario dono = new Usuario("samuel@gmail.com",new Senha("senha"));

        new Produto("nome", BigDecimal.TEN, 10, List.of(new Caracteristica("oi","dale")),"descricao",categoria,dono);
    }
}
