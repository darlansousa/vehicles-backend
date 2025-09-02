package com.exercises.vehicles.core.gateway;

import com.exercises.vehicles.core.domain.vehicles.BrandDomain;

import java.util.List;
import java.util.Optional;

public interface BrandsGateway {

    List<BrandDomain> findAll();

    Optional<BrandDomain> findBy(Long id);

}
