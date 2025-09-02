package com.exercises.vehicles.core.usecase.vehicles;

import com.exercises.vehicles.core.domain.vehicles.BrandDomain;
import com.exercises.vehicles.core.domain.vehicles.VehicleDomain;
import com.exercises.vehicles.core.domain.vehicles.VehicleStatus;
import com.exercises.vehicles.core.domain.vehicles.VehicleStatusEvent;
import com.exercises.vehicles.core.exception.BrandNotFoundException;
import com.exercises.vehicles.core.gateway.BrandsGateway;
import com.exercises.vehicles.core.gateway.VehicleStatusPublisherGateway;
import com.exercises.vehicles.core.gateway.VehiclesGateway;
import com.exercises.vehicles.core.usecase.vehicles.dto.input.VehicleInputDto;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class SaveVehiclesUseCaseTest {


    private static final EasyRandom EASY_RANDOM = new EasyRandom();

    @InjectMocks
    private SaveVehiclesUseCase subject;

    @Mock
    private VehiclesGateway vehiclesGatewayMock;
    @Mock
    private BrandsGateway brandsGatewayMock;
    @Mock
    private VehicleStatusPublisherGateway vehiclePublisherGatewayMock;

    @Test
    void shouldCreateOrUpdate() {
        final var vehicle = buildInput();
        final var domain = buildDomainFrom(vehicle);
        given(brandsGatewayMock.findBy(vehicle.brandId())).willReturn(Optional.of(domain.getBrand()));
        given(vehiclesGatewayMock.createOrUpdate(domain)).willReturn(domain);

        assertEquals(domain, subject.createOrUpdate(vehicle));

        then(brandsGatewayMock).should().findBy(vehicle.brandId());
        then(vehiclesGatewayMock).should().createOrUpdate(domain);
        then(vehiclePublisherGatewayMock).should().publish(
                VehicleStatusEvent.builder()
                        .vehicleId(vehicle.id())
                        .status(vehicle.wasSold() ? VehicleStatus.SOLD : VehicleStatus.AVAILABLE)
                        .build()
        );
    }

    @Test
    void shouldThrowWhenBrandNotFound() {
        final var vehicle = buildInput();
        given(brandsGatewayMock.findBy(vehicle.brandId())).willReturn(Optional.empty());
        assertThrows(BrandNotFoundException.class, ()-> subject.createOrUpdate(vehicle));
        then(brandsGatewayMock).should().findBy(vehicle.brandId());
        then(vehiclesGatewayMock).shouldHaveNoInteractions();
        then(vehiclePublisherGatewayMock).shouldHaveNoInteractions();
    }

    private static VehicleInputDto buildInput() {
        return new VehicleInputDto(
                EASY_RANDOM.nextLong(),
                EASY_RANDOM.nextLong(),
                EASY_RANDOM.nextObject(String.class),
                EASY_RANDOM.nextInt(),
                EASY_RANDOM.nextObject(String.class),
                EASY_RANDOM.nextObject(String.class),
                true);
    }


    private static VehicleDomain buildDomainFrom(VehicleInputDto vehicle) {
        return VehicleDomain.builder()
                .id(vehicle.id())
                .vehicle(vehicle.vehicle())
                .color(vehicle.color())
                .brand(BrandDomain.builder()
                        .id(vehicle.brandId())
                        .name("")
                        .build())
                .wasSold(vehicle.wasSold())
                .description(vehicle.description())
                .year(vehicle.year())
                .build();
    }

}