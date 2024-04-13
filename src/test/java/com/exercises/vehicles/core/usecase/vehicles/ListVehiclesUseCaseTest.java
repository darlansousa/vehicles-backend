package com.exercises.vehicles.core.usecase.vehicles;

import com.exercises.vehicles.core.domain.vehicles.VehicleDomain;
import com.exercises.vehicles.core.exception.VehicleNotFoundException;
import com.exercises.vehicles.core.gateway.VehiclesGateway;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class ListVehiclesUseCaseTest {

    private static final EasyRandom EASY_RANDOM = new EasyRandom();
    @InjectMocks
    private ListVehiclesUseCase subject;

    @Mock
    private VehiclesGateway vehiclesGatewayMock;

    @Test
    void shouldReturnsAllVehicles() {
        final var vehicle = EASY_RANDOM.nextObject(VehicleDomain.class);
        final var expected = List.of(vehicle);
        given(vehiclesGatewayMock.findAll()).willReturn(List.of(vehicle));

        assertEquals(expected, subject.findAll());

        then(vehiclesGatewayMock).should().findAll();
    }

    @Test
    void shouldFindByID() {
        final var vehicle = EASY_RANDOM.nextObject(VehicleDomain.class);

        given(vehiclesGatewayMock.findBy(vehicle.getId())).willReturn(Optional.of(vehicle));

        assertEquals(vehicle, subject.findBy(vehicle.getId()));

        then(vehiclesGatewayMock).should().findBy(vehicle.getId());
    }

    @Test
    void shouldThrowWhenVehicleDoNotExists() {
        final var id = EASY_RANDOM.nextLong();

        given(vehiclesGatewayMock.findBy(id)).willReturn(Optional.empty());

        assertThrows(VehicleNotFoundException.class, () -> subject.findBy(id));

        then(vehiclesGatewayMock).should().findBy(id);
    }

    @Test
    void shouldFindByBrandYearAndColor() {
        final var vehicle = EASY_RANDOM.nextObject(VehicleDomain.class);
        final var brand = EASY_RANDOM.nextObject(String.class);
        final var color = EASY_RANDOM.nextObject(String.class);
        final var year = EASY_RANDOM.nextLong();
        final var expected = List.of(vehicle);

        given(vehiclesGatewayMock.findBy(brand, year, color)).willReturn(List.of(vehicle));

        assertEquals(expected, subject.findBy(brand, year, color));

        then(vehiclesGatewayMock).should().findBy(brand, year, color);
    }

}