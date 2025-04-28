package com.meditrackapi.Meditrack.domain.Interfaces;

import com.meditrackapi.Meditrack.domain.Entities.Posto;

import java.util.List;
import java.util.Map;

public interface IGoogleDistanceMatrixService {
    Map<String, Double> getDistances(double userLat, double userLon, List<Posto> postos);
}
