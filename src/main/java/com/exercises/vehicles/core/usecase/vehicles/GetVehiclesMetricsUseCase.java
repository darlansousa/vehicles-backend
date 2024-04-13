package com.exercises.vehicles.core.usecase.vehicles;

import com.exercises.vehicles.core.domain.metrics.Metrics;
import com.exercises.vehicles.core.gateway.VehiclesGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetVehiclesMetricsUseCase {

    private final VehiclesGateway vehiclesGateway;

    public Metrics getMetrics() {
        return Metrics.builder()
                .totalVehicles(vehiclesGateway.contAll())
                .totalVehiclesNotSold(vehiclesGateway.countByWasSold(false))
                .vehiclesByBrand(vehiclesGateway.countTotalVehiclesByBrandName())
                .vehiclesByDecade(vehiclesGateway.countTotalVehiclesByDecade())
                .build();
    }

}
