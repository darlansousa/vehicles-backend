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
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class PartialUpdateVehiclesUseCaseTest {

    private static final EasyRandom EASY_RANDOM = new EasyRandom();

    @InjectMocks
    private PartialUpdateVehiclesUseCase subject;

    @Mock
    private VehiclesGateway vehiclesGatewayMock;

    @Test
    void shouldUpdatePartialProps() {
        final var vehicle = EASY_RANDOM.nextObject(VehicleDomain.class);

        given(vehiclesGatewayMock.findBy(vehicle.getId())).willReturn(Optional.of(vehicle));
        given(vehiclesGatewayMock.createOrUpdate(vehicle)).willReturn(vehicle);

        assertEquals(vehicle, subject.partialUpdate(
                vehicle.getId(),
                vehicle.getColor(),
                vehicle.getDescription(),
                true));

        then(vehiclesGatewayMock).should().findBy(vehicle.getId());
        then(vehiclesGatewayMock).should().createOrUpdate(vehicle);

    }

    @Test
    void shouldThrowWhenVehicleDoNotExists() {

        final var id = EASY_RANDOM.nextLong();
        final var color = EASY_RANDOM.nextObject(String.class);
        final var description = EASY_RANDOM.nextObject(String.class);
        final var wasSold = EASY_RANDOM.nextBoolean();

        given(vehiclesGatewayMock.findBy(id)).willReturn(Optional.empty());

        assertThrows(VehicleNotFoundException.class, () -> subject.partialUpdate(id, color, description, wasSold));

        then(vehiclesGatewayMock).should().findBy(id);
        then(vehiclesGatewayMock).shouldHaveNoMoreInteractions();

    }

}