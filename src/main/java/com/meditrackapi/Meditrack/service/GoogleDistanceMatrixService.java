package com.meditrackapi.Meditrack.service;

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

    public Map<String, Double> getDistances(double userLat, double userLon, List<Posto> postos) {
        String origins = userLat + "," + userLon;

        for (Posto posto : postos) {
            System.out.println("lat " + posto.getLatitude());
            System.out.println("long " + posto.getLongitude());
        }

        String destinations = postos.stream()
                .map(p -> p.getLatitude() + "," + p.getLongitude())
                .collect(Collectors.joining("|"));

        System.out.println("Número de destinos: " + postos.size());
        System.out.println("Destinations string: " + destinations);
        System.out.println("Origins string: " + origins);

        String url = UriComponentsBuilder.fromHttpUrl("https://maps.googleapis.com/maps/api/distancematrix/json")
                .queryParam("origins", origins)
                .queryParam("destinations", destinations)
                .queryParam("key", apiKey)
                .queryParam("mode", "driving")
                .build()
                .toUriString();

        System.out.println("URL de chamada: " + url);

        ResponseEntity<DistanceMatrixResponse> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );
        DistanceMatrixResponse matrix = response.getBody();

        System.out.println("Matrix: " + matrix);
        if (matrix == null || matrix.getRows() == null || matrix.getRows().isEmpty()) {
            System.out.println("Resposta vazia ou com erro da API.");
            return new HashMap<>();
        }

        List<DistanceMatrixElement> elements = matrix.getRows().get(0).getElements();
        if (elements == null || elements.size() != postos.size()) {
            System.out.println("Número de elementos da resposta não é compatível com o número de postos.");
            return new HashMap<>();
        }

        Map<String, Double> distanceMap = new HashMap<>();

        for (int i = 0; i < postos.size(); i++) {
            Posto posto = postos.get(i);
            DistanceMatrixElement element = elements.get(i);
            if (element.getStatus().equals("OK")) {
                double distanceKm = element.getDistance().getValue() / 1000.0;
                distanceMap.put(posto.getId(), distanceKm);
            }
        }

        return distanceMap;
    }
}
