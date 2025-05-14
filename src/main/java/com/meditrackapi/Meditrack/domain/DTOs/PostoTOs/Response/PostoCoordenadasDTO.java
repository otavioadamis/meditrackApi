package com.meditrackapi.Meditrack.domain.DTOs.PostoTOs.Response;

import lombok.Getter;

@Getter
public class PostoCoordenadasDTO {
    private final String id;
    private final double latitude;
    private final double longitude;

    public PostoCoordenadasDTO(String id, double latitude, double longitude) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
    }

}

