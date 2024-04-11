package com.exercises.vehicles.core.usecase.vehicles;

import com.exercises.vehicles.core.domain.vehicles.VehicleDomain;
import com.exercises.vehicles.core.gateway.VehiclesGateway;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

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



}