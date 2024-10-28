package com.meditrackapi.Meditrack.domain.DTOs.MedicamentoTOs.Response;

public record ListaMedsResponse(
        String medicamentoId,
        String nomeMedicamento,
        boolean necessitaReceita
) {
}
