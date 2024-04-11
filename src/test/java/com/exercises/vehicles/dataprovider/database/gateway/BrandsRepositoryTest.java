package com.exercises.vehicles.dataprovider.database.gateway;

import com.exercises.vehicles.core.domain.vehicles.BrandDomain;
import com.exercises.vehicles.dataprovider.database.entity.BrandEntity;
import com.exercises.vehicles.dataprovider.database.mapper.BrandMapper;
import com.exercises.vehicles.dataprovider.database.repository.BrandJPARepository;
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
class BrandsRepositoryTest {

    private static final EasyRandom EASY_RANDOM = new EasyRandom();
    @InjectMocks
    private BrandsRepository subject;

    @Mock
    private BrandJPARepository repositoryMock;
    @Mock
    private BrandMapper mapperMock;

    @Test
    void shouldFindAllBrands() {
        final var brand = EASY_RANDOM.nextObject(BrandEntity.class);
        final var domain = BrandDomain.builder().id(brand.getId()).name(brand.getName()).build();
        final var expected = List.of(domain);

        given(repositoryMock.findAll()).willReturn(List.of(brand));
        given(mapperMock.toDomain(brand)).willReturn(domain);

        assertEquals(expected, subject.findAll());

        then(repositoryMock).should().findAll();
        then(mapperMock).should().toDomain(brand);
    }


    @Test
    void shouldFindBrandById() {
        final var brand = EASY_RANDOM.nextObject(BrandEntity.class);
        final var domain = BrandDomain.builder().id(brand.getId()).name(brand.getName()).build();
        final var expected = Optional.of(domain);

        given(repositoryMock.findById(brand.getId())).willReturn(Optional.of(brand));
        given(mapperMock.toDomain(brand)).willReturn(domain);

        assertEquals(expected, subject.findBy(brand.getId()));

        then(repositoryMock).should().findById(brand.getId());
        then(mapperMock).should().toDomain(brand);
    }

    @Test
    void shouldReturnsEmptyWhenBrandNotExists() {
        final var id = EASY_RANDOM.nextLong();
        final var expected = Optional.empty();

        given(repositoryMock.findById(id)).willReturn(Optional.empty());

        assertEquals(expected, subject.findBy(id));

        then(repositoryMock).should().findById(id);
        then(mapperMock).shouldHaveNoInteractions();
    }

}