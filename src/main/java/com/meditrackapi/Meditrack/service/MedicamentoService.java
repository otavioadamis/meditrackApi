package com.meditrackapi.Meditrack.service;

import com.meditrackapi.Meditrack.domain.DTOs.MedicamentoTOs.Response.ListaMedsResponse;
import com.meditrackapi.Meditrack.domain.DTOs.MedicamentoTOs.Response.MedicamentoResponse;
import com.meditrackapi.Meditrack.domain.Interfaces.IMedicamentoService;
import org.springframework.stereotype.Service;

@Service
public class MedicamentoService implements IMedicamentoService {

    public MedicamentoService(){

    }

    @Override
    public ListaMedsResponse SearchByName(String nome){
        return null;
    }

    @Override
    public MedicamentoResponse SearchById(String medicamentoId){
        return null;
    }
}
