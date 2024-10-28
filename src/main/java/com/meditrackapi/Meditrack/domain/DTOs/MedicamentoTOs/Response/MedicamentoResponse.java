package com.meditrackapi.Meditrack.domain.DTOs.MedicamentoTOs.Response;

public record MedicamentoResponse(
        String localizacao,
        int estoque,
        String contatoPosto,
        String nomePosto,
        String nomeMedicamento,
        boolean necessitaReceita
) {
}
