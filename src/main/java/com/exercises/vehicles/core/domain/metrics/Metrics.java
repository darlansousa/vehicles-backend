package com.exercises.vehicles.core.domain.metrics;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Metrics {
    private Long totalVehicles;
    private Long totalVehiclesNotSold;
    private List<VehicleBrandCount> vehiclesByBrand;
    private List<VehicleDecadeCount> vehiclesByDecade;

}
