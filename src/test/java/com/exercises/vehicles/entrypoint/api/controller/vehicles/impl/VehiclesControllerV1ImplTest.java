package com.exercises.vehicles.entrypoint.api.controller.vehicles.impl;

import com.exercises.vehicles.core.domain.metrics.Metrics;
import com.exercises.vehicles.core.domain.vehicles.VehicleDomain;
import com.exercises.vehicles.core.usecase.vehicles.*;
import com.exercises.vehicles.entrypoint.api.config.ApiExceptionHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(MockitoExtension.class)
class VehiclesControllerV1ImplTest {

    private static final EasyRandom EASY_RANDOM = new EasyRandom();

    private JacksonTester<Metrics> metricsJsonTester;
    private JacksonTester<VehicleDomain> vehicleJsonTester;
    private JacksonTester<List<VehicleDomain>> vehiclesJsonTester;
    private MockMvc mvc;
    @InjectMocks
    private VehiclesControllerV1Impl subject;

    @Mock
    private ListVehiclesUseCase listVehiclesUseCaseMock;
    @Mock
    private SaveVehiclesUseCase saveVehiclesUseCaseMock;
    @Mock
    private DeleteVehiclesUseCase deleteVehiclesUseCaseMock;
    @Mock
    private PartialUpdateVehiclesUseCase partialUpdateVehiclesUseCaseMock;
    @Mock
    private GetVehiclesMetricsUseCase getVehiclesMetricsUseCaseMock;


    @BeforeEach
    public void setup() {
        JacksonTester.initFields(this, new ObjectMapper());
        mvc = MockMvcBuilders.standaloneSetup(subject)
                .setControllerAdvice(new ApiExceptionHandler())
                .build();
    }

    @Test
    public void shouldFindAll() throws Exception {
        final var vehicles = EASY_RANDOM.objects(VehicleDomain.class, 3).toList();
        final var expected = vehiclesJsonTester.write(vehicles).getJson();

        given(listVehiclesUseCaseMock.findBy(null, null, null)).willReturn(vehicles);

        final var response = mvc.perform(
                        get("/v1/vehicles").accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(expected, response.getContentAsString());

        then(listVehiclesUseCaseMock).should().findBy(null, null, null);
        then(partialUpdateVehiclesUseCaseMock).shouldHaveNoInteractions();
        then(deleteVehiclesUseCaseMock).shouldHaveNoInteractions();
        then(saveVehiclesUseCaseMock).shouldHaveNoInteractions();
        then(getVehiclesMetricsUseCaseMock).shouldHaveNoInteractions();
    }

    @Test
    public void shouldFindById() throws Exception {
        final var id = EASY_RANDOM.nextLong();
        final var vehicle = EASY_RANDOM.nextObject(VehicleDomain.class);
        final var expected = vehicleJsonTester.write(vehicle).getJson();

        given(listVehiclesUseCaseMock.findBy(id)).willReturn(vehicle);

        final var response = mvc.perform(
                        get("/v1/vehicles/%d".formatted(id)).accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(expected, response.getContentAsString());

        then(listVehiclesUseCaseMock).should().findBy(id);
        then(partialUpdateVehiclesUseCaseMock).shouldHaveNoInteractions();
        then(deleteVehiclesUseCaseMock).shouldHaveNoInteractions();
        then(saveVehiclesUseCaseMock).shouldHaveNoInteractions();
        then(getVehiclesMetricsUseCaseMock).shouldHaveNoInteractions();
    }

    @Test
    public void shouldReturnsMetrics() throws Exception {
        final var metrics = EASY_RANDOM.nextObject(Metrics.class);
        final var expected = metricsJsonTester.write(metrics).getJson();

        given(getVehiclesMetricsUseCaseMock.getMetrics()).willReturn(metrics);

        final var response = mvc.perform(
                        get("/v1/vehicles/metrics").accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(expected, response.getContentAsString());

        then(getVehiclesMetricsUseCaseMock).should().getMetrics();
        then(partialUpdateVehiclesUseCaseMock).shouldHaveNoInteractions();
        then(listVehiclesUseCaseMock).shouldHaveNoInteractions();
        then(saveVehiclesUseCaseMock).shouldHaveNoInteractions();
        then(deleteVehiclesUseCaseMock).shouldHaveNoInteractions();
    }

    @Test
    public void shouldDelete() throws Exception {
        final var id = EASY_RANDOM.nextLong();

        final var response = mvc.perform(
                        delete("/v1/vehicles/%d".formatted(id)).accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());

        then(deleteVehiclesUseCaseMock).should().deleteBy(id);
        then(partialUpdateVehiclesUseCaseMock).shouldHaveNoInteractions();
        then(listVehiclesUseCaseMock).shouldHaveNoInteractions();
        then(saveVehiclesUseCaseMock).shouldHaveNoInteractions();
        then(getVehiclesMetricsUseCaseMock).shouldHaveNoInteractions();
    }


}