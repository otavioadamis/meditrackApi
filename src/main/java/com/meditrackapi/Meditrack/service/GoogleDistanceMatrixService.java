package com.meditrackapi.Meditrack.service;

import com.meditrackapi.Meditrack.domain.DTOs.PostoTOs.Response.PostoCoordenadasDTO;
import com.meditrackapi.Meditrack.domain.Entities.Posto;
import com.meditrackapi.Meditrack.domain.Interfaces.IGoogleDistanceMatrixService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
class DistanceMatrixResponse {
    private List<String> destination_addresses;
    private List<String> origin_addresses;
    private List<DistanceMatrixRow> rows;
    private String status;
}

@Data
class DistanceMatrixRow {
    private List<DistanceMatrixElement> elements;
}

@Data
class DistanceMatrixElement {
    private DistanceValue distance;
    private DurationValue duration;
    private String status;
}

@Data
class DistanceValue {
    private String text;
    private int value; // in meters
}

@Data
class DurationValue {
    private String text;
    private int value; // in seconds
}

@Service
@RequiredArgsConstructor
public class GoogleDistanceMatrixService implements IGoogleDistanceMatrixService {

    @Value("${google.maps.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public Map<String, Double> getDistances(double userLat, double userLon, List<PostoCoordenadasDTO> postos) {
        final int BATCH_SIZE = 25;
        String origin = userLat + "," + userLon;
        Map<String, Double> distanceMap = new HashMap<>();

        for (int i = 0; i < postos.size(); i += BATCH_SIZE) {
            List<PostoCoordenadasDTO> batch = postos.subList(i, Math.min(i + BATCH_SIZE, postos.size()));

            String destinations = batch.stream()
                    .map(p -> p.getLatitude() + "," + p.getLongitude())
                    .collect(Collectors.joining("|"));

            String url = UriComponentsBuilder.fromHttpUrl("https://maps.googleapis.com/maps/api/distancematrix/json")
                    .queryParam("origins", origin)
                    .queryParam("destinations", destinations)
                    .queryParam("key", apiKey)
                    .queryParam("mode", "driving")
                    .build()
                    .toUriString();

            try {
                ResponseEntity<DistanceMatrixResponse> response = restTemplate.exchange(
                        url,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<>() {}
                );

                DistanceMatrixResponse matrix = response.getBody();

                if (matrix != null && matrix.getRows() != null && !matrix.getRows().isEmpty()) {
                    List<DistanceMatrixElement> elements = matrix.getRows().get(0).getElements();

                    for (int j = 0; j < elements.size(); j++) {
                        DistanceMatrixElement element = elements.get(j);
                        if ("OK".equals(element.getStatus())) {
                            double distanceKm = element.getDistance().getValue() / 1000.0;
                            distanceMap.put(batch.get(j).getId(), distanceKm);
                        } else {
                            distanceMap.put(batch.get(j).getId(), Double.MAX_VALUE); // fallback distance
                        }
                    }
                }
            } catch (Exception e) {
                System.err.println("Erro ao consultar a Distance Matrix API: " + e.getMessage());
                // Optionally: add fallback value
                for (PostoCoordenadasDTO p : batch) {
                    distanceMap.put(p.getId(), Double.MAX_VALUE);
                }
            }
        }

        return distanceMap;
    }
}
