package com.exercises.vehicles.core.domain.metrics;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VehicleDecadeCount {
    private Long decade;
    private Long total;

}
