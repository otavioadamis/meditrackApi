package com.meditrackapi.Meditrack.api.Controller;

import com.meditrackapi.Meditrack.domain.DTOs.UsuarioTOs.*;
import com.meditrackapi.Meditrack.domain.Entities.Usuario;
import com.meditrackapi.Meditrack.domain.Interfaces.IUsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    private final IUsuarioService _usuarioService;

    public UsuarioController(IUsuarioService usuarioService){
        _usuarioService = usuarioService;
    }

    @PostMapping("/cadastro")
    public ResponseEntity<LoginResponseDTO> cadastrarUsuario(@RequestBody @Valid PostUsuarioDTO novoUsuario){
        LoginResponseDTO response = _usuarioService.cadastrarUsuario(novoUsuario);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/editar")
    public ResponseEntity<UsuarioResponseDTO> editarUsuario(@RequestBody @Valid EditUsuarioDTO usuarioInfos){
        UsuarioResponseDTO response = _usuarioService.editarUsuario(usuarioInfos);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid UserLoginDTO userLogin){
        LoginResponseDTO response = _usuarioService.login(userLogin);
        return  ResponseEntity.ok(response);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Usuario>> listarTodosUsuarios() {
        List<Usuario> usuarios = _usuarioService.listarTodosUsuarios();
        return ResponseEntity.ok(usuarios);
    }
}
