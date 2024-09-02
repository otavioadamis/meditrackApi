package com.meditrackapi.Meditrack.api.Controller;

import com.meditrackapi.Meditrack.domain.DTOs.UsuarioTOs.LoginResponseDTO;
import com.meditrackapi.Meditrack.domain.DTOs.UsuarioTOs.PostUsuarioDTO;
import com.meditrackapi.Meditrack.domain.DTOs.UsuarioTOs.UserLoginDTO;
import com.meditrackapi.Meditrack.domain.DTOs.UsuarioTOs.UsuarioResponseDTO;
import com.meditrackapi.Meditrack.domain.Interfaces.IUsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid UserLoginDTO userLogin){
        LoginResponseDTO response = _usuarioService.login(userLogin);
        return  ResponseEntity.ok(response);
    }

    @GetMapping("/auth")
    public ResponseEntity teste(){
        return ResponseEntity.ok("bla");
    }
}
