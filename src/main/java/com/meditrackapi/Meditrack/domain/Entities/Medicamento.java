package com.meditrackapi.Meditrack.domain.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.util.Date;
import java.util.Set;

@Table(name = "medicamento")
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Medicamento {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private int codigo;
    private String produto;
    private String tipo;
    private Date vencimento;
    @ManyToMany
    @JoinTable(name = "medicamento_posto",
            joinColumns = @JoinColumn(name = "medicamento_id"),
            inverseJoinColumns = @JoinColumn(name = "posto_id"))
    private Set<Posto> postos;
}
