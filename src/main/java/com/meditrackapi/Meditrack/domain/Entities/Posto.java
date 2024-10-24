package com.meditrackapi.Meditrack.domain.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "posto")
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Posto {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String nome;
    private String bairro;
    private String rua;
    private int numero;
    private String linhasOnibus;
    private String telefone;
}
