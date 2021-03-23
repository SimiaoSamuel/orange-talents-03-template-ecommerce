package com.treino.mercadolivre.usuario;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping
    public ResponseEntity<Void> salvandoUsuario(@RequestBody @Valid UsuarioRequest usuarioRequest){
        Usuario usuario = usuarioRequest.toUsuario();
        usuarioRepository.save(usuario);

        return ResponseEntity.ok().build();
    }
}
