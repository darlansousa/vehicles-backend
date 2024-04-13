package com.exercises.vehicles.core.usecase.vehicles;

import com.exercises.vehicles.core.domain.vehicles.VehicleDomain;
import com.exercises.vehicles.core.exception.VehicleNotFoundException;
import com.exercises.vehicles.core.gateway.VehiclesGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PartialUpdateVehiclesUseCase {

    private final VehiclesGateway vehiclesGateway;

    public VehicleDomain partialUpdate(Long id, String color, String description, Boolean wasSold) {
        final var vehicle = vehiclesGateway.findBy(id).orElseThrow(VehicleNotFoundException::new);
        return vehiclesGateway.createOrUpdate(VehicleDomain.builder()
                .id(id)
                .brand(vehicle.getBrand())
                .vehicle(vehicle.getVehicle())
                .year(vehicle.getYear())
                .color(color)
                .description(description)
                .wasSold(wasSold)
                .build());
    }
}
