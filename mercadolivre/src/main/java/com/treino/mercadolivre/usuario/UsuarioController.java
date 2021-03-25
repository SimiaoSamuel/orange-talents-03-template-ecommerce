package com.treino.mercadolivre.usuario;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> listaDeUsuarios(){
        List<Usuario> users = usuarioRepository.findAll();
        List<UsuarioResponse> usuarioResponses = UsuarioResponse.toListUserResponse(users);
        return ResponseEntity.ok(usuarioResponses);
    }
}
