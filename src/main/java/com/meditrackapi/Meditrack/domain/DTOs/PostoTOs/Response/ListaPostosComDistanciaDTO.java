package com.meditrackapi.Meditrack.domain.DTOs.PostoTOs.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListaPostosComDistanciaDTO {
    private String postoId;
    private String nomePosto;
    private String bairroPosto;
    private String ruaPosto;
    private String numeroPosto;
    private String linhasOnibus;
    private String telefone;
    private Integer quantidadeEstoque;
    private Double latitude;
    private Double longitude;
    private Double distanciaKm;

    public ListaPostosComDistanciaDTO(ListaPostosResponse base, String postoId, Double distanciaKm) {
        this.postoId = postoId;
        this.nomePosto = base.getNomePosto();
        this.bairroPosto = base.getBairroPosto();
        this.ruaPosto = base.getRuaPosto();
        this.numeroPosto = base.getNumeroPosto();
        this.linhasOnibus = base.getLinhasOnibus();
        this.telefone = base.getTelefone();
        this.quantidadeEstoque = base.getQuantidadeEstoque();
        this.latitude = base.getLatitude();
        this.longitude = base.getLongitude();
        this.distanciaKm = distanciaKm;
    }
}
