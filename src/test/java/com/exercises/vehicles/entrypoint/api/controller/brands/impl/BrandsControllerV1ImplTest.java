package com.exercises.vehicles.entrypoint.api.controller.brands.impl;

import com.exercises.vehicles.core.domain.vehicles.BrandDomain;
import com.exercises.vehicles.core.usecase.brands.ListBrandsUseCase;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(MockitoExtension.class)
class BrandsControllerV1ImplTest {

    private static final EasyRandom EASY_RANDOM = new EasyRandom();
    private MockMvc mvc;

    @InjectMocks
    private BrandsControllerV1Impl subject;

    @Mock
    private ListBrandsUseCase listBrandsUseCaseMock;

    private JacksonTester<List<BrandDomain>> jsonTester;

    @BeforeEach
    public void setup() {
        JacksonTester.initFields(this, new ObjectMapper());
        mvc = MockMvcBuilders.standaloneSetup(subject)
                .setControllerAdvice(new ApiExceptionHandler())
                .build();
    }

    @Test
    public void shouldReturnsBrands() throws Exception {
        final var brands = EASY_RANDOM.objects(BrandDomain.class, 3).toList();

        given(listBrandsUseCaseMock.getAll()).willReturn(brands);

        final var response = mvc.perform(
                        get("/v1/brands").accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(response.getContentAsString(), jsonTester.write(brands).getJson());
        then(listBrandsUseCaseMock).should().getAll();
    }


}