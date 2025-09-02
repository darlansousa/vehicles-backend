package com.exercises.vehicles.core.usecase.vehicles;

import static java.lang.Boolean.FALSE;

import org.springframework.stereotype.Component;
import com.exercises.vehicles.core.domain.vehicles.VehicleDomain;
import com.exercises.vehicles.core.domain.vehicles.VehicleStatus;
import com.exercises.vehicles.core.domain.vehicles.VehicleStatusEvent;
import com.exercises.vehicles.core.exception.BrandNotFoundException;
import com.exercises.vehicles.core.gateway.BrandsGateway;
import com.exercises.vehicles.core.gateway.VehicleStatusPublisherGateway;
import com.exercises.vehicles.core.gateway.VehiclesGateway;
import com.exercises.vehicles.core.usecase.vehicles.dto.input.VehicleInputDto;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SaveVehiclesUseCase {

    private final VehiclesGateway vehiclesGateway;
    private final BrandsGateway brandsGateway;
    private final VehicleStatusPublisherGateway vehiclePublisherGateway;

    public VehicleDomain createOrUpdate(VehicleInputDto input) {
        final var brand = brandsGateway.findBy(input.brandId()).orElseThrow(BrandNotFoundException::new);

        final var saved = vehiclesGateway.createOrUpdate(
                VehicleDomain.builder().id(input.id()).brand(brand).vehicle(input.vehicle()).color(input.color())
                        .description(input.description()).year(input.year()).wasSold(input.wasSold()).build());

        final var builder = VehicleStatusEvent.builder().vehicleId(saved.getId());

        if (FALSE.equals(saved.getWasSold())) {
            vehiclePublisherGateway.publish(builder.status(VehicleStatus.AVAILABLE).build());
            return saved;
        }

        vehiclePublisherGateway.publish(builder.status(VehicleStatus.SOLD).build());
        return saved;
    }
}
