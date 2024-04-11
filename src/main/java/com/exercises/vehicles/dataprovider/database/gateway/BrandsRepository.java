package com.exercises.vehicles.dataprovider.database.gateway;

import com.exercises.vehicles.core.domain.vehicles.BrandDomain;
import com.exercises.vehicles.core.gateway.BrandsGateway;
import com.exercises.vehicles.dataprovider.database.mapper.BrandMapper;
import com.exercises.vehicles.dataprovider.database.repository.BrandJPARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class BrandsRepository implements BrandsGateway {


    private final BrandJPARepository repository;
    private final BrandMapper mapper;

    @Override
    public List<BrandDomain> findAll() {
        return StreamSupport
                .stream(repository.findAll().spliterator(), false)
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public Optional<BrandDomain> findBy(Long id) {
        return repository.findById(id).map(mapper::toDomain);
    }
}
