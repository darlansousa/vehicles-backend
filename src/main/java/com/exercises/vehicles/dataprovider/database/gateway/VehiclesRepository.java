package com.exercises.vehicles.dataprovider.database.gateway;

import com.exercises.vehicles.core.domain.metrics.VehicleBrandCount;
import com.exercises.vehicles.core.domain.metrics.VehicleDecadeCount;
import com.exercises.vehicles.core.domain.vehicles.VehicleDomain;
import com.exercises.vehicles.core.gateway.VehiclesGateway;
import com.exercises.vehicles.dataprovider.database.mapper.VehicleMapper;
import com.exercises.vehicles.dataprovider.database.repository.VehiclesJPARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class VehiclesRepository implements VehiclesGateway {

    private final VehiclesJPARepository repository;
    private final VehicleMapper mapper;

    @Override
    public List<VehicleDomain> findAll() {
        return StreamSupport
                .stream(repository.findAll().spliterator(), false)
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<VehicleDomain> findBy(String brand, Long year, String color) {
        return repository.findByBrandNameAndYearAndColor(brand, year, color).stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public Optional<VehicleDomain> findBy(Long id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public VehicleDomain createOrUpdate(VehicleDomain vehicle) {
        return mapper.toDomain(repository.save(mapper.toEntity(vehicle)));
    }

    @Override
    public List<VehicleBrandCount> countTotalVehiclesByBrandName() {
        return repository.countTotalVehiclesByBrandName().stream()
                .map(it ->  VehicleBrandCount.builder()
                        .brand(it.getBrandName())
                        .total(it.getTotal())
                        .build())
                .toList();
    }

    @Override
    public List<VehicleDecadeCount> countTotalVehiclesByDecade() {
        return repository.countTotalVehiclesByDecade().stream()
                .map(it -> VehicleDecadeCount.builder()
                        .decade(it.getDecade())
                        .total(it.getTotal())
                        .build())
                .toList();
    }

    @Override
    public Long contAll() {
        return repository.count();
    }

    @Override
    public Long countByWasSold(Boolean wasSold) {
        return repository.countByWasSold(wasSold);
    }

    @Override
    public void deleteBy(Long id) {
        repository.deleteById(id);
    }
}