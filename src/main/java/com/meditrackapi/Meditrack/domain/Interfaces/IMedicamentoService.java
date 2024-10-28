package com.meditrackapi.Meditrack.domain.Interfaces;

import com.meditrackapi.Meditrack.domain.DTOs.MedicamentoTOs.Response.ListaMedsResponse;
import com.meditrackapi.Meditrack.domain.DTOs.MedicamentoTOs.Response.MedicamentoResponse;
import com.meditrackapi.Meditrack.domain.Entities.Medicamento;

import java.util.List;

public interface IMedicamentoService {
    List<ListaMedsResponse> SearchByName(String nome);
    MedicamentoResponse SearchById(String medicamentoId);
}
