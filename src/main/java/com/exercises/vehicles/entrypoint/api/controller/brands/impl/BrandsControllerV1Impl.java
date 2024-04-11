package com.exercises.vehicles.entrypoint.api.controller.brands.impl;

import com.exercises.vehicles.core.domain.vehicles.BrandDomain;
import com.exercises.vehicles.core.usecase.brands.ListBrandsUseCase;
import com.exercises.vehicles.entrypoint.api.controller.brands.BrandsControllerV1;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/brands")
@Tag(name = "Marcas V1", description = "Através deste recurso é possível obter todas as marcas cadastradas")
public class BrandsControllerV1Impl implements BrandsControllerV1 {

    private final ListBrandsUseCase listBrandsUseCase;

    @GetMapping
    @Operation(
            summary = "Recuperar marcas",
            description = "Retorna lista de marcas cadastradas",
            responses = {
                    @ApiResponse(responseCode = "200", content = {
                            @Content(schema = @Schema(implementation = BrandDomain.class))
                    })
            }
    )
    @Override
    public List<BrandDomain> findAll() {
        return listBrandsUseCase.getAll();
    }
}
