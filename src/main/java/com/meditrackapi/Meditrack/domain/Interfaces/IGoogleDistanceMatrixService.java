package com.meditrackapi.Meditrack.domain.Interfaces;

import com.meditrackapi.Meditrack.domain.DTOs.PostoTOs.Response.PostoCoordenadasDTO;
import com.meditrackapi.Meditrack.domain.Entities.Posto;

import java.util.List;
import java.util.Map;

public interface IGoogleDistanceMatrixService {
    public Map<String, Double> getDistances(double userLat, double userLon, List<PostoCoordenadasDTO> postos);
}
