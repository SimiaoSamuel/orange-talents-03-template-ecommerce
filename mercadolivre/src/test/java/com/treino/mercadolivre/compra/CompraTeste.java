package com.treino.mercadolivre.compra;

import com.treino.mercadolivre.caracteristica.Caracteristica;
import com.treino.mercadolivre.categoria.Categoria;
import com.treino.mercadolivre.pagamento.PagamentoGateway;
import com.treino.mercadolivre.produto.Produto;
import com.treino.mercadolivre.usuario.Senha;
import com.treino.mercadolivre.usuario.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CompraTeste {

    private List<Caracteristica> caracteristicas;
    private Categoria categoria;
    private Usuario usuario;

    @BeforeEach
    public void setUp() {
        caracteristicas = List.of(new Caracteristica("cate1", "valor1"), new Caracteristica("cate2", "valor2"),
                new Caracteristica("cate3", "valor3"));

        categoria = new Categoria("categoria com mãe nula");
        usuario = new Usuario("emaildeteste@gmail.com", new Senha("senha123"));
    }

    @ParameterizedTest
    @CsvSource({"1,1", "4,4", "10,1"})
    public void abateEstoqueSeTiverAQuantidadeSolicitada(Integer estoque, Integer quantidadeSolicitada) {
        Produto produto = novoProduto(estoque);
        CompraRequest request = new CompraRequest(quantidadeSolicitada, PagamentoGateway.PAGSEGURO);
        Compra compra = request.toCompra(produto, novoComprador());
    }

    @ParameterizedTest()
    @CsvSource({"1,2", "4,5", "10,11"})
    public void retornaExceptionaoTentarAbateEstoqueSeQuantidadeSolicitaForMaior(Integer estoque, Integer quantidadeSolicitada) {
        assertThrows(ResponseStatusException.class, () -> {
            Produto produto = novoProduto(estoque);
            CompraRequest request = new CompraRequest(quantidadeSolicitada, PagamentoGateway.PAGSEGURO);
            Compra compra = request.toCompra(produto, novoComprador());
        });
    }

    public Produto novoProduto(Integer estoque) {
        return new Produto("Beyblade", new BigDecimal(10), estoque, new ArrayList<>(),
                "Produto bom", new Categoria("Brinquedo"), new Usuario("login", new Senha("senha")));
    }

    public Usuario novoComprador() {
        return new Usuario("login2", new Senha("senha2"));
    }
}
