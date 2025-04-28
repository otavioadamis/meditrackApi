package com.meditrackapi.Meditrack.domain.DTOs.PostoTOs.Response;

import lombok.Getter;

@Getter
public class PostoDistanciaResponse {
    private String id;
    private String nome;
    private String bairro;
    private String rua;
    private String numero;
    private String linhasOnibus;
    private String telefone;
    private double distanciaKm;

    public PostoDistanciaResponse(
            String id,
            String nome,
            String bairro,
            String rua,
            String numero,
            String linhasOnibus,
            String telefone,
            double distanciaKm
    ) {
        this.id = id;
        this.nome = nome;
        this.bairro = bairro;
        this.rua = rua;
        this.numero = numero;
        this.linhasOnibus = linhasOnibus;
        this.telefone = telefone;
        this.distanciaKm = distanciaKm;
    }

}