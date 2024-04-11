package com.exercises.vehicles.core.usecase.vehicles;

import com.exercises.vehicles.core.domain.vehicles.VehicleDomain;
import com.exercises.vehicles.core.exception.BrandNotFoundException;
import com.exercises.vehicles.core.gateway.BrandsGateway;
import com.exercises.vehicles.core.gateway.VehiclesGateway;
import com.exercises.vehicles.core.usecase.vehicles.dto.input.VehicleInputDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SaveVehiclesUseCase {

    private final VehiclesGateway vehiclesGateway;
    private final BrandsGateway brandsGateway;

    public VehicleDomain createOrUpdate(VehicleInputDto input) {
        final var brand = brandsGateway.findBy(input.brandId()).orElseThrow(BrandNotFoundException::new);
        return vehiclesGateway.createOrUpdate(VehicleDomain.builder()
                        .id(input.id())
                        .brand(brand)
                        .vehicle(input.vehicle())
                        .color(input.color())
                        .description(input.description())
                        .year(input.year())
                        .wasSold(input.wasSold())
                .build());
    }
}
