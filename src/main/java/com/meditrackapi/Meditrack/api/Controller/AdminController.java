package com.meditrackapi.Meditrack.api.Controller;

import com.meditrackapi.Meditrack.domain.DTOs.PostoTOs.Response.PostoDetalhadoResponse;
import com.meditrackapi.Meditrack.domain.DTOs.UsuarioTOs.UsuarioResponseDTO;
import com.meditrackapi.Meditrack.domain.Interfaces.IMedicamentoService;
import com.meditrackapi.Meditrack.domain.Interfaces.IPostoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOError;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final IMedicamentoService _medicamentoService;
    private final IPostoService _postoService;
    public AdminController(IMedicamentoService medicamentoService, IPostoService postoService){
        _medicamentoService = medicamentoService;
        _postoService = postoService;
    }

    @PostMapping(value = "/upload-meds", consumes = {"multipart/form-data"})
    public ResponseEntity<Integer> uploadMedicamentos(@RequestPart("file") MultipartFile file) throws IOException {
        return ResponseEntity.ok(_medicamentoService.InserirCargaMedicamentos(file));
    }
}
