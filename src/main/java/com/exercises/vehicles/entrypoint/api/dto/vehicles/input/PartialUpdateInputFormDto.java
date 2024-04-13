package com.exercises.vehicles.entrypoint.api.dto.vehicles.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PartialUpdateInputFormDto(
        @NotBlank
        String color,
        @NotBlank
        String description,
        @NotNull
        Boolean wasSold) {
}
