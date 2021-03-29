package com.treino.mercadolivre.foto;

import com.treino.mercadolivre.produto.Produto;
import com.treino.mercadolivre.produto.ProdutoRepository;
import com.treino.mercadolivre.uploader.Uploader;
import com.treino.mercadolivre.usuario.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos/{id}/fotos")
@Validated
public class FotoController {
    private final ProdutoRepository produtoRepository;
    private final FotoRepository fotoRepository;
    private final Uploader uploader;

    public FotoController(ProdutoRepository produtoRepository, FotoRepository fotoRepository, Uploader uploader) {
        this.produtoRepository = produtoRepository;
        this.fotoRepository = fotoRepository;
        this.uploader = uploader;
    }

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<FotoResponse>> insereFotos(@PathVariable Integer id,
                                                          @AuthenticationPrincipal Usuario usuario,
                                                          @RequestParam(value = "photo") @Size(min = 1) List<MultipartFile> files){
        FotoRequest fotoRequest = new FotoRequest(files);
        Optional<Produto> produtoOptional = produtoRepository.findById(id);

        if(produtoOptional.isEmpty())
            return ResponseEntity.notFound().build();

        Produto produto = produtoOptional.get();

        if(!produto.getUsuario().equals(usuario))
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);

        List<String> uris = uploader.sendToCloud(fotoRequest.getFotos());
        List<Foto> foto = fotoRequest.toListOfUri(uris);

        fotoRepository.saveAll(foto);

        produto.setFotos(foto);
        produtoRepository.save(produto);

        List<FotoResponse> fotosResponse = FotoResponse.toResponse(foto);

        return ResponseEntity.ok(fotosResponse);
    }
}
