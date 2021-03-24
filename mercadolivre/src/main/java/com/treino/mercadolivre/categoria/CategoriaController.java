package com.treino.mercadolivre.categoria;

import com.treino.mercadolivre.usuario.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaRepository categoriaRepository;

    public CategoriaController(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @PostMapping
    public ResponseEntity<CategoriaResponse> salvarCategoria(@RequestBody @Valid CategoriaRequest categoriaRequest){
        Categoria categoria = categoriaRequest.toCategoria(categoriaRepository);
        categoriaRepository.save(categoria);
        CategoriaResponse categoriaResponse = new CategoriaResponse(categoria);
        return ResponseEntity.ok(categoriaResponse);
    }

    @GetMapping
    public List<CategoriaResponse> listaCategoria(){
        List<Categoria> categorias = categoriaRepository.findAll();
        return CategoriaResponse.toListCategoriaResponse(categorias);
    }
}
