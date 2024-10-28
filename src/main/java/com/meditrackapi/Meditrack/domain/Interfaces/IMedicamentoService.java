package com.meditrackapi.Meditrack.domain.Interfaces;

import com.meditrackapi.Meditrack.domain.DTOs.MedicamentoTOs.Response.ListaMedsResponse;
import com.meditrackapi.Meditrack.domain.DTOs.MedicamentoTOs.Response.MedicamentoResponse;

public interface IMedicamentoService {
    ListaMedsResponse SearchByName(String nome);
    MedicamentoResponse SearchById(String medicamentoId);
}
