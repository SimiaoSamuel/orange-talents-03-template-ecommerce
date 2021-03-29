package com.treino.mercadolivre.produto;

import com.treino.mercadolivre.caracteristica.CaracteristicaRepository;
import com.treino.mercadolivre.categoria.CategoriaRepository;
import com.treino.mercadolivre.opiniao.Opiniao;
import com.treino.mercadolivre.opiniao.OpiniaoRepository;
import com.treino.mercadolivre.opiniao.OpiniaoResponse;
import com.treino.mercadolivre.pergunta.Pergunta;
import com.treino.mercadolivre.pergunta.PerguntaRepository;
import com.treino.mercadolivre.pergunta.PerguntaResponse;
import com.treino.mercadolivre.usuario.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;
    private final CaracteristicaRepository caracteristicaRepository;
    private final OpiniaoRepository opiniaoRepository;
    private final PerguntaRepository perguntaRepository;

    public ProdutoController(ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository,
                             CaracteristicaRepository caracteristicaRepository,
                             OpiniaoRepository opiniaoRepository, PerguntaRepository perguntaRepository) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
        this.caracteristicaRepository = caracteristicaRepository;
        this.opiniaoRepository = opiniaoRepository;
        this.perguntaRepository = perguntaRepository;
    }

    @PostMapping
    public ResponseEntity<Integer> salvandoProduto(@RequestBody @Valid ProdutoRequest produtoRequest, @AuthenticationPrincipal Usuario user){
        Produto produto = produtoRequest.toProduto(categoriaRepository,user);

        caracteristicaRepository.saveAll(produto.getCaracteristicas());
        produtoRepository.save(produto);
        return ResponseEntity.ok(produto.getId());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponse> retornandoProdutoPorId(@PathVariable Integer id){
        Optional<Produto> produtoOptional = produtoRepository.findById(id);
        if(produtoOptional.isEmpty())
            return ResponseEntity.notFound().build();

        List<Opiniao> opinioes = opiniaoRepository.findByProdutoId(id);
        List<Pergunta> perguntas = perguntaRepository.findByProdutoId(id);

        Produto produto = produtoOptional.get();

        return ResponseEntity.ok(new ProdutoResponse(produto, opinioes, perguntas));
    }
}
