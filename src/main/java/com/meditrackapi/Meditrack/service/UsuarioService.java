package com.meditrackapi.Meditrack.service;

import com.meditrackapi.Meditrack.dao.Repositories.UsuarioRepository;
import com.meditrackapi.Meditrack.domain.DTOs.UsuarioTOs.LoginResponseDTO;
import com.meditrackapi.Meditrack.domain.DTOs.UsuarioTOs.PostUsuarioDTO;
import com.meditrackapi.Meditrack.domain.DTOs.UsuarioTOs.UserLoginDTO;
import com.meditrackapi.Meditrack.domain.DTOs.UsuarioTOs.UsuarioResponseDTO;
import com.meditrackapi.Meditrack.domain.Entities.Usuario;
import com.meditrackapi.Meditrack.domain.Interfaces.IAuthenticationService;
import com.meditrackapi.Meditrack.domain.Interfaces.IUsuarioService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService implements IUsuarioService {

    private final UsuarioRepository _usuarioRepo;
    private final IAuthenticationService _authService;
    private final AuthenticationManager _authenticationManager;
    private final PasswordEncoder _passwordEncoder;

    public UsuarioService(
            UsuarioRepository usuarioRepo,
            AuthenticationManager authenticationManager,
            IAuthenticationService authService,
            PasswordEncoder passwordEncoder)
    {
        _usuarioRepo = usuarioRepo;
        _authenticationManager = authenticationManager;
        _authService = authService;
        _passwordEncoder = passwordEncoder;
    }

    @Override
    public LoginResponseDTO cadastrarUsuario(PostUsuarioDTO novoUsuario)
    {
        Usuario checkEmailAndCpf = _usuarioRepo.findByEmailOrCpf(novoUsuario.email(), novoUsuario.cpf());
        if(checkEmailAndCpf != null){
            throw new IllegalArgumentException("Usuario com este email ou cpf ja existe");
        }

        String encryptedPassword = _passwordEncoder.encode(novoUsuario.senha());
        Usuario usuario = new Usuario(novoUsuario, encryptedPassword);

        _usuarioRepo.save(usuario);

        String jwtToken = _authService.createToken(usuario);

        UsuarioResponseDTO usuarioResponse = new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNomeCompleto(),
                usuario.getEmail(),
                usuario.getCpf(),
                usuario.getFotoPerfil()
        );

        return new LoginResponseDTO(
                jwtToken,
                usuarioResponse
        );
    }

    @Override
    public LoginResponseDTO login(UserLoginDTO userLogin)
    {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    userLogin.email(),
                    userLogin.senha()
            );
            Authentication auth = _authenticationManager.authenticate(authenticationToken);
            Usuario usuario = (Usuario) auth.getPrincipal();

        String jwtToken = _authService.createToken(usuario);

        UsuarioResponseDTO usuarioResponse = new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNomeCompleto(),
                usuario.getEmail(),
                usuario.getCpf(),
                usuario.getFotoPerfil()
        );

        return new LoginResponseDTO(
                jwtToken,
                usuarioResponse
        );
    }

    @Override
    public List<Usuario> listarTodosUsuarios() {
        return _usuarioRepo.findAll();
    }
}
