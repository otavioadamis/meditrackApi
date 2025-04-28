package com.meditrackapi.Meditrack.service;

import com.meditrackapi.Meditrack.dao.Repositories.HistoricoEstoqueRepository;
import com.meditrackapi.Meditrack.dao.Repositories.MedicamentoRepository;
import com.meditrackapi.Meditrack.dao.Repositories.PostoRepository;
import com.meditrackapi.Meditrack.dao.Repositories.UsuarioRepository;
import com.meditrackapi.Meditrack.domain.DTOs.MedicamentoTOs.Response.MedicamentoCard;
import com.meditrackapi.Meditrack.domain.DTOs.PostoTOs.Response.*;
import com.meditrackapi.Meditrack.domain.Entities.Posto;
import com.meditrackapi.Meditrack.domain.Entities.Usuario;
import com.meditrackapi.Meditrack.domain.Interfaces.IGoogleDistanceMatrixService;
import com.meditrackapi.Meditrack.domain.Interfaces.IPostoService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PostoService implements IPostoService {
    private final PostoRepository _postoRepo;
    private final MedicamentoRepository _medicamentoRepo;
    private final HistoricoEstoqueRepository _historicoEstoqueRepo;
    private final UsuarioRepository _userRepo;
    private final IGoogleDistanceMatrixService _distanceService;

    public PostoService(
            PostoRepository postoRepository,
            MedicamentoRepository medicamentoRepository,
            HistoricoEstoqueRepository historicoEstoqueRepository,
            UsuarioRepository usuarioRepository,
            IGoogleDistanceMatrixService distanceService) {
        _postoRepo = postoRepository;
        _medicamentoRepo = medicamentoRepository;
        _historicoEstoqueRepo = historicoEstoqueRepository;
        _userRepo = usuarioRepository;
        _distanceService = distanceService;
    }

    @Override
    public List<HistoricoEstoqueResponse> getHistoricoEstoque(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUserEmail = authentication.getName();
        Usuario usuarioLogado = (Usuario) _userRepo.findByEmail(loggedInUserEmail);

        String postoId = usuarioLogado.getPosto().getId();

        return _historicoEstoqueRepo.findHistoricoEstoqueByPostoId(postoId);
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

    public List<PostoDistanciaResponse> SearchPostosProximos(double lat, double lon){
        List<Posto> limitedPostos = _postoRepo.findLimitedPostos();

        Map<String, Double> distanceMap = _distanceService.getDistances(lat, lon, limitedPostos);
        return limitedPostos.stream()
                .map(p -> new PostoDistanciaResponse(
                        p.getId(),
                        p.getNome(),
                        p.getBairro(),
                        p.getRua(),
                        p.getNumero(),
                        p.getLinhasOnibus(),
                        p.getTelefone(),
                        distanceMap.getOrDefault(p.getId(), 0.0)
                ))
                .sorted(Comparator.comparingDouble(PostoDistanciaResponse::getDistanciaKm))
                .toList();
    }
}