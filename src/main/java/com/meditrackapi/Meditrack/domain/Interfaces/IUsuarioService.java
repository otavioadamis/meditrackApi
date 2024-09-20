package com.meditrackapi.Meditrack.domain.Interfaces;

import com.meditrackapi.Meditrack.domain.DTOs.UsuarioTOs.LoginResponseDTO;
import com.meditrackapi.Meditrack.domain.DTOs.UsuarioTOs.PostUsuarioDTO;
import com.meditrackapi.Meditrack.domain.DTOs.UsuarioTOs.UserLoginDTO;
import com.meditrackapi.Meditrack.domain.Entities.Usuario;

import java.util.List;

public interface IUsuarioService {
    LoginResponseDTO cadastrarUsuario(PostUsuarioDTO novoUsuario);
    LoginResponseDTO login(UserLoginDTO userLogin);
    List<Usuario> listarTodosUsuarios(); // Adicione esta linha
}
