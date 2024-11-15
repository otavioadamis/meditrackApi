package com.meditrackapi.Meditrack.api.Controller;

import com.meditrackapi.Meditrack.domain.Interfaces.IMedicamentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/funcionario")
public class FuncionarioController {
    private final IMedicamentoService _medicamentoService;
    public FuncionarioController(IMedicamentoService medicamentoService){
        _medicamentoService = medicamentoService;
    }

    @PostMapping(value = "/atualizar-estoque", consumes = {"multipart/form-data"})
    public ResponseEntity<Integer> atualizarEstoque(@RequestPart("file") MultipartFile file) throws IOException {
        return ResponseEntity.ok(_medicamentoService.AtualizarEstoque(file, "teste"));
    }
}
