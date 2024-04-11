package com.exercises.vehicles.entrypoint.api.dto.vehicles.input;

public record VehicleInputFormDto(
        Long brandId,
        String vehicle,
        Integer year,
        String color,
        String description,
        Boolean wasSold) {
}
