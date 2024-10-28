package com.meditrackapi.Meditrack.service;

import com.meditrackapi.Meditrack.dao.Repositories.MedicamentoRepository;
import com.meditrackapi.Meditrack.dao.Repositories.PostoRepository;
import com.meditrackapi.Meditrack.domain.DTOs.MedicamentoTOs.Response.ListaMedsResponse;
import com.meditrackapi.Meditrack.domain.DTOs.MedicamentoTOs.Response.MedicamentoResponse;
import com.meditrackapi.Meditrack.domain.DTOs.PostoTOs.Response.ListaPostosResponse;
import com.meditrackapi.Meditrack.domain.Entities.Medicamento;
import com.meditrackapi.Meditrack.domain.Interfaces.IMedicamentoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicamentoService implements IMedicamentoService {

    private final MedicamentoRepository _medicamentoRepo;
    private final PostoRepository _postoRepo;
    public MedicamentoService(MedicamentoRepository medicamentoRepository, PostoRepository postoRepository){
        _medicamentoRepo = medicamentoRepository;
        _postoRepo = postoRepository;
    }

    @Override
    public List<ListaMedsResponse> SearchByName(String nome){
        return _medicamentoRepo.findByName(nome);
    }

    @Override
    public MedicamentoResponse SearchById(String medicamentoId){
        Medicamento medicamento = _medicamentoRepo.findById(medicamentoId)
                .orElseThrow(() -> new IllegalArgumentException("Medicamento não encontrado."));

        List<ListaPostosResponse> postos = _postoRepo.findPostosByMedicamentoId(medicamentoId);

        return new MedicamentoResponse(
                medicamento.getCodigo(),
                medicamento.getLote(),
                medicamento.getProduto(),
                medicamento.getTipo(),
                medicamento.getVencimento(),
                medicamento.isNecessitaReceita(),
                postos
            );
    }
}
