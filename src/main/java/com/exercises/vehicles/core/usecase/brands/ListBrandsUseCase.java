package com.exercises.vehicles.core.usecase.brands;


import com.exercises.vehicles.core.domain.vehicles.BrandDomain;
import com.exercises.vehicles.core.gateway.BrandsGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ListBrandsUseCase {

    private final BrandsGateway brandsGateway;

    public List<BrandDomain> getAll() {
        return brandsGateway.findAll();
    }

}
