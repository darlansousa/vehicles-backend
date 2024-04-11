package com.exercises.vehicles.entrypoint.api.dto.vehicles.input;

public record PartialUpdateInputFormDto(
        String color,
        String description,
        Boolean wasSold) {
}
