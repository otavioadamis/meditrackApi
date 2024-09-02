package com.meditrackapi.Meditrack.domain.Entities;

import com.meditrackapi.Meditrack.domain.DTOs.UsuarioTOs.PostUsuarioDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.UUID;

@Table(name = "usuario")
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String nomeCompleto;
    private String cpf;
    private String email;
    private String senha;
    private String fotoPerfil;
    private Date dataNascimento;
    @CreationTimestamp
    private Date criadoEm;

    public Usuario(PostUsuarioDTO novoUsuario, String encryptedPassword){
        this.nomeCompleto = novoUsuario.nomeCompleto();
        this.cpf = novoUsuario.cpf();
        this.email = novoUsuario.email();
        this.senha = encryptedPassword;
        this.fotoPerfil = "dev-teste";
        this.dataNascimento = novoUsuario.dataNascimento();
    }

}
