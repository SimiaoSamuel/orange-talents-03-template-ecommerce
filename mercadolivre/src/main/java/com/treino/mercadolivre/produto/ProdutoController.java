package com.treino.mercadolivre.produto;

import com.treino.mercadolivre.caracteristica.CaracteristicaRepository;
import com.treino.mercadolivre.categoria.CategoriaRepository;
import com.treino.mercadolivre.usuario.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;
    private final CaracteristicaRepository caracteristicaRepository;

    public ProdutoController(ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository,
                              CaracteristicaRepository caracteristicaRepository) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
        this.caracteristicaRepository = caracteristicaRepository;
    }

    @PostMapping
    public ResponseEntity<ProdutoResponse> salvandoProduto(@RequestBody @Valid ProdutoRequest produtoRequest, @AuthenticationPrincipal Usuario user){
        Produto produto = produtoRequest.toProduto(categoriaRepository,user);

        caracteristicaRepository.saveAll(produto.getCaracteristicas());
        produtoRepository.save(produto);
        ProdutoResponse produtoResponse = new ProdutoResponse(produto);
        return ResponseEntity.ok(produtoResponse);
    }
}
