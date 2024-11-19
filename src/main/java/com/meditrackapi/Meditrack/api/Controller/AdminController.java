package com.meditrackapi.Meditrack.api.Controller;

import com.meditrackapi.Meditrack.domain.DTOs.UsuarioTOs.UsuarioResponseDTO;
import com.meditrackapi.Meditrack.domain.Interfaces.IMedicamentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOError;
import java.io.IOException;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final IMedicamentoService _medicamentoService;
    public AdminController(IMedicamentoService medicamentoService){
        _medicamentoService = medicamentoService;
    }

    @PostMapping(value = "/upload-meds", consumes = {"multipart/form-data"})
    public ResponseEntity<Integer> uploadMedicamentos(@RequestPart("file") MultipartFile file) throws IOException {
        return ResponseEntity.ok(_medicamentoService.InserirCargaMedicamentos(file));
    }

}
