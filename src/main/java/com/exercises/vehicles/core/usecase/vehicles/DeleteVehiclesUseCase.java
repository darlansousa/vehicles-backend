package com.exercises.vehicles.core.usecase.vehicles;

import com.exercises.vehicles.core.exception.VehicleNotFoundException;
import com.exercises.vehicles.core.gateway.VehiclesGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteVehiclesUseCase {

    private final VehiclesGateway vehiclesGateway;

    public void deleteBy(Long id) {
        final var vehicle = vehiclesGateway.findBy(id).orElseThrow(VehicleNotFoundException::new);
        vehiclesGateway.deleteBy(vehicle.getId());
    }
}
