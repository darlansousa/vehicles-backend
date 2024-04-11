package com.exercises.vehicles.core.usecase.vehicles.dto.input;

public record VehicleInputDto(
        Long id,
        Long brandId,
        String vehicle,
        Integer year,
        String color,
        String description,
        Boolean wasSold
) {
}
