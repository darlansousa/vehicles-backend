package com.exercises.vehicles.entrypoint.api.controller.brands;

import com.exercises.vehicles.core.domain.vehicles.BrandDomain;


import java.util.List;

public interface BrandsControllerV1 {

    List<BrandDomain> findAll();
}
