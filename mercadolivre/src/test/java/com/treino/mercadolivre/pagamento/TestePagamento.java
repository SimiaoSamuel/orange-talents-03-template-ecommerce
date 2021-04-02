package com.treino.mercadolivre.pagamento;

import com.treino.mercadolivre.categoria.Categoria;
import com.treino.mercadolivre.compra.Compra;
import com.treino.mercadolivre.compra.CompraRepository;
import com.treino.mercadolivre.email.EmailProvider;
import com.treino.mercadolivre.produto.Produto;
import com.treino.mercadolivre.usuario.Senha;
import com.treino.mercadolivre.usuario.Usuario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.validation.BindException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;


@ExtendWith(SpringExtension.class)
public class TestePagamento {

    @InjectMocks
    PagamentoController controller;

    @Mock
    TransacaoRepository transacaoRepository;

    @Mock
    CompraRepository compraRepository;

    @Mock
    EmailProvider provider;

    DefaultGateway defaultGateway;

    @BeforeEach
    public void setUp(){
        Optional<Compra> compra = Optional.of(novaCompra());
        Mockito.when(compraRepository.findById(1)).thenReturn(compra);
        Mockito.when(compraRepository.findById(2)).thenReturn(Optional.empty());
        defaultGateway = new DefaultGateway(1, CompraStatus.SUCESSO);
    }

    @Test
    public void teste() {
        Compra compra = novaCompra();
        Transacao transacao = new Transacao(compra, CompraStatus.SUCESSO, 1);
        compra.adicionaTransacaoFinalizada(transacao, transacaoRepository);
    }

    @Test
    public void retorna200OKSeEncontraCompra() throws BindException {
        ResponseEntity<Transacao> transacaoPagseguro = controller
                .processaPagamento(defaultGateway, "pagseguro", 1);

        Assertions.assertEquals(HttpStatus.OK,transacaoPagseguro.getStatusCode());
    }

    @Test
    public void retorna404NotFoundSeCompraNaoFoiEncontrada() throws BindException {
        ResponseEntity<Transacao> transacaoPagseguro = controller
                .processaPagamento(defaultGateway, "pagseguro", 2);

        Assertions.assertEquals(HttpStatus.NOT_FOUND,transacaoPagseguro.getStatusCode());
    }

    @Test
    public void retorna403ForbiddenSeMetodoDePagamentoForDiferenteDaCompra() throws BindException {
        ResponseEntity<Transacao> transacaoPagseguro = controller
                .processaPagamento(defaultGateway, "paypal", 1);

        Assertions.assertEquals(HttpStatus.FORBIDDEN,transacaoPagseguro.getStatusCode());
    }

    public Compra novaCompra() {
        return new Compra(5, PagamentoGateway.PAGSEGURO, novoProduto(), novoComprador(), CompraStatus.INICIADA);
    }

    public Produto novoProduto() {
        return new Produto("Beyblade", new BigDecimal(10), 10, new ArrayList<>(),
                "Produto bom", new Categoria("Brinquedo"), new Usuario("login", new Senha("senha")));
    }

    public Usuario novoComprador() {
        return new Usuario("login2", new Senha("senha2"));
    }
}
