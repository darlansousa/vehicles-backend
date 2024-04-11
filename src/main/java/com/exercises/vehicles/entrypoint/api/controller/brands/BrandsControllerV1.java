package com.exercises.vehicles.entrypoint.api.controller.brands;

import com.exercises.vehicles.core.domain.vehicles.BrandDomain;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;


import java.util.List;

public interface BrandsControllerV1 {

    List<BrandDomain> findAll();
}
