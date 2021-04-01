package com.treino.mercadolivre.compra;

import com.treino.mercadolivre.email.EmailProvider;
import com.treino.mercadolivre.produto.Produto;
import com.treino.mercadolivre.produto.ProdutoRepository;
import com.treino.mercadolivre.usuario.Usuario;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

@RestController
@RequestMapping("/produtos/{id}/compras")
public class CompraController {

    private final ProdutoRepository produtoRepository;
    private final EmailProvider email;
    private final CompraRepository compraRepository;

    public CompraController(ProdutoRepository produtoRepository, EmailProvider email,
                            CompraRepository compraRepository) {
        this.produtoRepository = produtoRepository;
        this.email = email;
        this.compraRepository = compraRepository;
    }

    @PostMapping
    public ResponseEntity<CompraResponse> efetuarCompra(@PathVariable Integer id,
                                                        @RequestBody @Valid CompraRequest compraRequest,
                                                        @AuthenticationPrincipal Usuario comprador) throws IOException, URISyntaxException {
        Optional<Produto> produtoOptional = produtoRepository.findById(id);

        if (produtoOptional.isEmpty())
            return ResponseEntity.notFound().build();

        Produto produto = produtoOptional.get();

        Compra compra = compraRequest.toCompra(produto,comprador);
        compraRepository.save(compra);
        produtoRepository.save(compra.getProduto());
        email.sendEmail(compra);
        URI uriBuild = compra.getPagamento().getGateway(compra);

        CompraResponse compraResponse = new CompraResponse(compra);

        return ResponseEntity.created(uriBuild).body(compraResponse);
    }
}
