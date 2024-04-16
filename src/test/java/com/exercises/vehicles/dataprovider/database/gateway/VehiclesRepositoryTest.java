package com.exercises.vehicles.dataprovider.database.gateway;

import com.exercises.vehicles.core.domain.metrics.VehicleBrandCount;
import com.exercises.vehicles.core.domain.metrics.VehicleDecadeCount;
import com.exercises.vehicles.core.domain.vehicles.BrandDomain;
import com.exercises.vehicles.core.domain.vehicles.VehicleDomain;
import com.exercises.vehicles.dataprovider.database.entity.VehicleEntity;
import com.exercises.vehicles.dataprovider.database.entity.metrics.IVehicleBrandCount;
import com.exercises.vehicles.dataprovider.database.entity.metrics.IVehicleDecadeCount;
import com.exercises.vehicles.dataprovider.database.mapper.VehicleMapper;
import com.exercises.vehicles.dataprovider.database.repository.VehiclesJPARepository;
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
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class VehiclesRepositoryTest {

    private static final EasyRandom EASY_RANDOM = new EasyRandom();

    @InjectMocks
    private VehiclesRepository subject;
    @Mock
    private VehiclesJPARepository repositoryMock;
    @Mock
    private VehicleMapper mapperMock;


    @Test
    void shouldFindAllVehicles() {
        final var vehicle = EASY_RANDOM.nextObject(VehicleEntity.class);
        final var domain = buildDomainFrom(vehicle);
        final var expected = List.of(domain);

        given(repositoryMock.findAll()).willReturn(List.of(vehicle));
        given(mapperMock.toDomain(vehicle)).willReturn(domain);

        assertEquals(expected, subject.findAll());

        then(repositoryMock).should().findAll();
        then(mapperMock).should().toDomain(vehicle);
    }

    @Test
    void shouldFindVehicleById() {
        final var vehicle = EASY_RANDOM.nextObject(VehicleEntity.class);
        final var domain = buildDomainFrom(vehicle);
        final var expected = Optional.of(domain);

        given(repositoryMock.findById(vehicle.getId())).willReturn(Optional.of(vehicle));
        given(mapperMock.toDomain(vehicle)).willReturn(domain);

        assertEquals(expected, subject.findBy(vehicle.getId()));

        then(repositoryMock).should().findById(vehicle.getId());
        then(mapperMock).should().toDomain(vehicle);
    }

    @Test
    void shouldSave() {
        final var vehicle = EASY_RANDOM.nextObject(VehicleEntity.class);
        final var domain = buildDomainFrom(vehicle);

        given(repositoryMock.save(vehicle)).willReturn(vehicle);
        given(mapperMock.toEntity(domain)).willReturn(vehicle);
        given(mapperMock.toDomain(vehicle)).willReturn(domain);

        assertEquals(domain, subject.createOrUpdate(domain));

        then(repositoryMock).should().save(vehicle);
        then(mapperMock).should().toDomain(vehicle);
        then(mapperMock).should().toEntity(domain);
    }

    @Test
    void shouldReturnsEmptyWhenVehicleNotExists() {
        final var id = EASY_RANDOM.nextLong();
        final var expected = Optional.empty();

        given(repositoryMock.findById(id)).willReturn(Optional.empty());

        assertEquals(expected, subject.findBy(id));

        then(repositoryMock).should().findById(id);
        then(mapperMock).shouldHaveNoInteractions();
    }

    @Test
    void shouldDelete() {
        final var id = EASY_RANDOM.nextLong();

        subject.deleteBy(id);
        then(repositoryMock).should().deleteById(id);
        then(mapperMock).shouldHaveNoInteractions();
    }

    @Test
    void shouldCountAll() {
        final var count = EASY_RANDOM.nextLong();
        given(repositoryMock.count()).willReturn(count);

        assertEquals(count, subject.contAll());

        then(repositoryMock).should().count();
        then(mapperMock).shouldHaveNoInteractions();
    }

    @Test
    void shouldCountByWasSoldField() {
        final var count = EASY_RANDOM.nextLong();
        final var wasSold = EASY_RANDOM.nextBoolean();
        given(repositoryMock.countByWasSold(wasSold)).willReturn(count);

        assertEquals(count, subject.countByWasSold(wasSold));

        then(repositoryMock).should().countByWasSold(wasSold);
        then(mapperMock).shouldHaveNoInteractions();
    }

    @Test
    void shouldCountByBrandName() {
        final var brandCountMock = mock(IVehicleBrandCount.class);
        final var brandName = EASY_RANDOM.nextObject(String.class);
        final var count = EASY_RANDOM.nextLong();
        final var expected  = VehicleBrandCount.builder().brand(brandName).total(count).build();

        given(brandCountMock.getBrandName()).willReturn(brandName);
        given(brandCountMock.getTotal()).willReturn(count);
        given(repositoryMock.countTotalVehiclesByBrandName()).willReturn(List.of(brandCountMock));

        assertEquals(List.of(expected), subject.countTotalVehiclesByBrandName());

        then(repositoryMock).should().countTotalVehiclesByBrandName();
        then(mapperMock).shouldHaveNoInteractions();
    }

    @Test
    void shouldCountByDecade() {
        final var brandCountMock = mock(IVehicleDecadeCount.class);
        final var decade = EASY_RANDOM.nextLong();
        final var count = EASY_RANDOM.nextLong();
        final var expected  = VehicleDecadeCount.builder().decade(decade).total(count).build();

        given(brandCountMock.getDecade()).willReturn(decade);
        given(brandCountMock.getTotal()).willReturn(count);
        given(repositoryMock.countTotalVehiclesByDecade()).willReturn(List.of(brandCountMock));

        assertEquals(List.of(expected), subject.countTotalVehiclesByDecade());

        then(repositoryMock).should().countTotalVehiclesByDecade();
        then(mapperMock).shouldHaveNoInteractions();
    }


    private static VehicleDomain buildDomainFrom(VehicleEntity vehicle) {
        return VehicleDomain.builder()
                .id(vehicle.getId())
                .vehicle(vehicle.getVehicle())
                .color(vehicle.getColor())
                .brand(BrandDomain.builder()
                        .id(vehicle.getBrand().getId())
                        .name(vehicle.getBrand().getName())
                        .build())
                .wasSold(vehicle.getWasSold())
                .year(vehicle.getYear())
                .build();
    }


}