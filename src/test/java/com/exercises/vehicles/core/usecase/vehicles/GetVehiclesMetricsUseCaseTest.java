package com.exercises.vehicles.core.usecase.vehicles;

import com.exercises.vehicles.core.domain.metrics.Metrics;
import com.exercises.vehicles.core.domain.metrics.VehicleBrandCount;
import com.exercises.vehicles.core.domain.metrics.VehicleDecadeCount;
import com.exercises.vehicles.core.gateway.VehiclesGateway;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class GetVehiclesMetricsUseCaseTest {

    private static final EasyRandom EASY_RANDOM = new EasyRandom();

    @InjectMocks
    private GetVehiclesMetricsUseCase subject;

    @Mock
    private VehiclesGateway vehiclesGatewayMock;

    @Test
    void shouldReturnsMetrics() {
        final var total = EASY_RANDOM.nextLong();
        final var totalSold = EASY_RANDOM.nextLong();
        final var vehiclesByBrand = EASY_RANDOM.objects(VehicleBrandCount.class, 3).toList();
        final var vehiclesByDecade = EASY_RANDOM.objects(VehicleDecadeCount.class, 3).toList();
        final var expected = Metrics.builder()
                .totalVehicles(total)
                .totalVehiclesNotSold(totalSold)
                .vehiclesByBrand(vehiclesByBrand)
                .vehiclesByDecade(vehiclesByDecade)
                .build();

        given(vehiclesGatewayMock.contAll()).willReturn(total);
        given(vehiclesGatewayMock.countByWasSold(false)).willReturn(totalSold);
        given(vehiclesGatewayMock.countTotalVehiclesByBrandName()).willReturn(vehiclesByBrand);
        given(vehiclesGatewayMock.countTotalVehiclesByDecade()).willReturn(vehiclesByDecade);

        assertEquals(expected, subject.getMetrics());

        then(vehiclesGatewayMock).should().contAll();
        then(vehiclesGatewayMock).should().countByWasSold(false);
        then(vehiclesGatewayMock).should().countTotalVehiclesByBrandName();
        then(vehiclesGatewayMock).should().countTotalVehiclesByDecade();

    }

}