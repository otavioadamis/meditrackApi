package com.meditrackapi.Meditrack.domain.DTOs.PostoTOs.Response;

public interface ListaPostosResponse {
    String getPostoId();
    String getNomePosto();
    String getBairroPosto();
    String getRuaPosto();
    String getNumeroPosto();
    String getLinhasOnibus();
    String getTelefone();
    Integer getQuantidadeEstoque();
    Double getLatitude();
    Double getLongitude();
}
