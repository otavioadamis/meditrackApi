package com.meditrackapi.Meditrack.service;

import com.meditrackapi.Meditrack.dao.Repositories.MedicamentoRepository;
import com.meditrackapi.Meditrack.dao.Repositories.PostoRepository;
import com.meditrackapi.Meditrack.domain.DTOs.MedicamentoTOs.Response.MedicamentoCard;
import com.meditrackapi.Meditrack.domain.DTOs.PostoTOs.Response.PostoComMedicamentosResponse;
import com.meditrackapi.Meditrack.domain.DTOs.PostoTOs.Response.PostoDetalhadoResponse;
import com.meditrackapi.Meditrack.domain.DTOs.PostoTOs.Response.PostoResumoResponse;
import com.meditrackapi.Meditrack.domain.Interfaces.IPostoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostoService implements IPostoService {
    private final PostoRepository _postoRepo;
    private final MedicamentoRepository _medicamentoRepo;

    public PostoService(PostoRepository postoRepository,
                       MedicamentoRepository medicamentoRepository) {
        _postoRepo = postoRepository;
        _medicamentoRepo = medicamentoRepository;
    }

    @Override
    public List<PostoDetalhadoResponse> findAllPostos(){
        return _postoRepo.findAllPostos();
    }

    @Override
    public Optional<PostoComMedicamentosResponse> SearchById(String id) {
        Optional<PostoComMedicamentosResponse> postoOpt = _postoRepo.findComMedicamentosById(id);
        
        if (postoOpt.isPresent()) {
            PostoComMedicamentosResponse posto = postoOpt.get();
            List<MedicamentoCard> medicamentos = _medicamentoRepo.findAllByPostoId(posto.getId());
            posto.setMedicamentos(medicamentos);
            return Optional.of(posto);
        }
        
        return Optional.empty();
    }

    @Override
    public List<PostoResumoResponse> SearchByName(String nome) {
        return _postoRepo.findByNomeContainingIgnoreCase(nome);
    }
}