package com.treino.mercadolivre.pergunta;

import com.treino.mercadolivre.email.EmailProvider;
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
@RequestMapping("produtos/{id}/perguntas")
public class PerguntaController {

    private final ProdutoRepository produtoRepository;
    private final PerguntaRepository perguntaRepository;
    private final EmailProvider emailProvider;

    public PerguntaController(ProdutoRepository produtoRepository, PerguntaRepository perguntaRepository, EmailProvider emailProvider) {
        this.produtoRepository = produtoRepository;
        this.perguntaRepository = perguntaRepository;
        this.emailProvider = emailProvider;
    }

    @PostMapping
    public ResponseEntity<PerguntaResponse> enviaPergunta(@PathVariable Integer id,
                                                          @AuthenticationPrincipal Usuario usuario,
                                                          @Valid @RequestBody PerguntaRequest perguntaRequest){
        Optional<Produto> produtoOptional = produtoRepository.findById(id);

        if(produtoOptional.isEmpty())
            return ResponseEntity.notFound().build();

        Produto produto = produtoOptional.get();

        if(usuario == null)
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);

        Pergunta pergunta = perguntaRequest.toPergunta(usuario, produto);
        perguntaRepository.save(pergunta);
        emailProvider.sendEmail(produto, pergunta);
        PerguntaResponse perguntaResponse = new PerguntaResponse(pergunta);

        return ResponseEntity.ok(perguntaResponse);
    }
}
