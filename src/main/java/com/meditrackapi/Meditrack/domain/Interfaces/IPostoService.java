package com.meditrackapi.Meditrack.domain.Interfaces;

import com.meditrackapi.Meditrack.domain.DTOs.PostoTOs.Response.*;

import java.util.List;
import java.util.Optional;

public interface IPostoService {
    List<HistoricoEstoqueResponse> getHistoricoEstoque();
    List<PostoDetalhadoResponse> findAllPostos();
    Optional<PostoComMedicamentosResponse> SearchById(String id);
    List<PostoResumoResponse> SearchByName(String nome);
    List<PostoDistanciaResponse> SearchPostosProximos(double lat, double lon);
}