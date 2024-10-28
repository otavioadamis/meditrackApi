package com.meditrackapi.Meditrack.api.Controller;

import com.meditrackapi.Meditrack.domain.DTOs.MedicamentoTOs.Response.ListaMedsResponse;
import com.meditrackapi.Meditrack.domain.DTOs.MedicamentoTOs.Response.MedicamentoResponse;
import com.meditrackapi.Meditrack.domain.Interfaces.IMedicamentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/medicamento")
public class MedicamentoController {

    private final IMedicamentoService _medicamentoService;
    public MedicamentoController(IMedicamentoService medicamentoService){
        _medicamentoService = medicamentoService;
    }

    public ResponseEntity buscarMedicamentosPorNome(String nome){
        ListaMedsResponse response = _medicamentoService.SearchByName(nome);
        return ResponseEntity.ok(response);
    }

    public ResponseEntity buscarMedicamento(String medicamentoId){
        MedicamentoResponse response = _medicamentoService.SearchById(medicamentoId);
        return ResponseEntity.ok(response);
    }
}
