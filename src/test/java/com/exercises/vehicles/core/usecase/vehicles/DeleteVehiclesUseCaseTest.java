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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class DeleteVehiclesUseCaseTest {

    private static final EasyRandom EASY_RANDOM = new EasyRandom();
    @InjectMocks
    private DeleteVehiclesUseCase subject;
    @Mock
    private VehiclesGateway vehiclesGatewayMock;

    @Test
    void shouldDeleteWhenVehicleExists() {
        final var id = EASY_RANDOM.nextLong();
        final var vehicle = EASY_RANDOM.nextObject(VehicleDomain.class);

        given(vehiclesGatewayMock.findBy(id)).willReturn(Optional.of(vehicle));

        subject.deleteBy(id);

        then(vehiclesGatewayMock).should().deleteBy(vehicle.getId());
    }

    @Test
    void shouldThrowWhenVehicleNotExists() {
        final var id = EASY_RANDOM.nextLong();

        given(vehiclesGatewayMock.findBy(id)).willReturn(Optional.empty());

        assertThrows(VehicleNotFoundException.class, () -> subject.deleteBy(id));

        then(vehiclesGatewayMock).should(times(0)).deleteBy(anyLong());
    }

}