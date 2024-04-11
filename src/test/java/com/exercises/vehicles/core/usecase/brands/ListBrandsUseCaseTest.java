package com.exercises.vehicles.core.usecase.brands;

import com.exercises.vehicles.core.domain.vehicles.BrandDomain;
import com.exercises.vehicles.core.gateway.BrandsGateway;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class ListBrandsUseCaseTest {

    private static final EasyRandom EASY_RANDOM = new EasyRandom();

    @InjectMocks
    private ListBrandsUseCase subject;

    @Mock
    private BrandsGateway brandsGatewayMock;

    @Test
    void shouldReturnsAllBrands() {
        final var brand = EASY_RANDOM.nextObject(BrandDomain.class);
        final var expected = List.of(brand);

        given(brandsGatewayMock.findAll()).willReturn(List.of(brand));

        assertEquals(expected, subject.getAll());

        then(brandsGatewayMock).should().findAll();
    }

}