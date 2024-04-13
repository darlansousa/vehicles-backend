package com.exercises.vehicles.core.usecase.vehicles;

import com.exercises.vehicles.core.domain.vehicles.VehicleDomain;
import com.exercises.vehicles.core.exception.VehicleNotFoundException;
import com.exercises.vehicles.core.gateway.VehiclesGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ListVehiclesUseCase {
    
    private final VehiclesGateway vehiclesGateway;

    public List<VehicleDomain> findAll() {
        return vehiclesGateway.findAll();
    }

    public VehicleDomain findBy(Long id) {
        return vehiclesGateway.findBy(id).orElseThrow(VehicleNotFoundException::new);
    }

    public List<VehicleDomain> findBy(String brand, Long year, String color) {
        return vehiclesGateway.findBy(brand, year, color);
    }

}
