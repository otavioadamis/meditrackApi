package com.meditrackapi.Meditrack.api.Controller;

import com.meditrackapi.Meditrack.domain.DTOs.PostoTOs.Response.PostoComMedicamentosResponse;
import com.meditrackapi.Meditrack.domain.DTOs.PostoTOs.Response.PostoResumoResponse;
import com.meditrackapi.Meditrack.domain.Interfaces.IPostoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posto")
public class PostoController {
    private final IPostoService _postoService;

    public PostoController(IPostoService postoService) {
        _postoService = postoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostoComMedicamentosResponse> buscarPorId(@PathVariable String id) {
        return _postoService.SearchById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/pesquisar/{nome}")
    public ResponseEntity<List<PostoResumoResponse>> buscarPorNome(@PathVariable String nome) {
        return ResponseEntity.ok(_postoService.SearchByName(nome));
    }
}