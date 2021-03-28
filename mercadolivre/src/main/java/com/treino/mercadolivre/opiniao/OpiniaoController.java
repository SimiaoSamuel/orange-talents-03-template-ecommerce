package com.treino.mercadolivre.opiniao;

import com.treino.mercadolivre.produto.Produto;
import com.treino.mercadolivre.produto.ProdutoRepository;
import com.treino.mercadolivre.usuario.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/produtos/{id}/opinioes")
public class OpiniaoController {

    private final OpiniaoRepository opiniaoRepository;
    private final ProdutoRepository produtoRepository;

    public OpiniaoController(OpiniaoRepository opiniaoRepository, ProdutoRepository produtoRepository) {
        this.opiniaoRepository = opiniaoRepository;
        this.produtoRepository = produtoRepository;
    }

    @PostMapping
    public ResponseEntity<OpiniaoResponse> salvaOpiniao(@PathVariable Integer id,
                                                       @AuthenticationPrincipal Usuario usuario,
                                                       @RequestBody @Valid OpiniaoRequest opiniaoRequest){
        Optional<Produto> produtoOptional = produtoRepository.findById(id);

        if(produtoOptional.isEmpty())
            return ResponseEntity.notFound().build();

        Produto produto = produtoOptional.get();

        if(usuario.equals(produto.getUsuario()))
           return new ResponseEntity<>(HttpStatus.FORBIDDEN);

        Opiniao opiniao = opiniaoRequest.toOpiniao(usuario, produto);
        opiniaoRepository.save(opiniao);
        OpiniaoResponse opiniaoResponse = new OpiniaoResponse(opiniao);

        return ResponseEntity.ok(opiniaoResponse);
    }
}
