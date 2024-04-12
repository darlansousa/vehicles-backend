package com.exercises.vehicles.entrypoint.api.controller.vehicles.impl;

import com.exercises.vehicles.core.domain.vehicles.VehicleDomain;
import com.exercises.vehicles.core.usecase.vehicles.DeleteVehiclesUseCase;
import com.exercises.vehicles.core.usecase.vehicles.ListVehiclesUseCase;
import com.exercises.vehicles.core.usecase.vehicles.SaveVehiclesUseCase;
import com.exercises.vehicles.core.usecase.vehicles.dto.input.VehicleInputDto;
import com.exercises.vehicles.entrypoint.api.controller.vehicles.VehiclesControllerV1;
import com.exercises.vehicles.entrypoint.api.dto.vehicles.input.PartialUpdateInputFormDto;
import com.exercises.vehicles.entrypoint.api.dto.vehicles.input.VehicleInputFormDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/vehicles")
@Tag(name = "Veículos", description = "Através deste recurso é possível fazer a gestão de veículos")
public class VehiclesControllerV1Impl implements VehiclesControllerV1 {

    private final ListVehiclesUseCase listVehiclesUseCase;
    private final SaveVehiclesUseCase saveVehiclesUseCase;
    private final DeleteVehiclesUseCase deleteVehiclesUseCase;

    @GetMapping
    @Operation(
            summary = "Recuperar veículos",
            description = "Retorna lista de veículos cadastrados",
            responses = {
                    @ApiResponse(responseCode = "200", content = {
                            @Content(schema = @Schema(implementation = VehicleDomain.class))
                    })
            }
    )
    @Override
    public List<VehicleDomain> getAll() {
        return listVehiclesUseCase.findAll();
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Recuperar veículo por ID",
            description = "Retorna um veículo por ID",
            responses = {
                    @ApiResponse(responseCode = "200", content = {
                            @Content(schema = @Schema(implementation = VehicleDomain.class))
                    }),
                    @ApiResponse(responseCode = "424", description = "Violação de regras de negócio")
            }
    )
    @Override
    public VehicleDomain getBy(@PathVariable Long id) {
        return listVehiclesUseCase.findBy(id);
    }

    @PostMapping
    @Operation(
            summary = "Criar veículo",
            description = "Cadastrar um veículo",
            responses = {
                    @ApiResponse(responseCode = "200", content = {
                            @Content(schema = @Schema(implementation = VehicleDomain.class))
                    }),
                    @ApiResponse(responseCode = "424", description = "Violação de regras de negócio")
            }
    )
    @Override
    public VehicleDomain save(@RequestBody @Valid VehicleInputFormDto input) {
        return saveVehiclesUseCase.createOrUpdate(new VehicleInputDto(
                null,
                input.brandId(),
                input.vehicle(),
                input.year(),
                input.color(),
                input.description(),
                input.wasSold()
        ));
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Atualizar veículo",
            description = "Atualiza dados de um veículo completamente",
            responses = {
                    @ApiResponse(responseCode = "200", content = {
                            @Content(schema = @Schema(implementation = VehicleDomain.class))
                    }),
                    @ApiResponse(responseCode = "424", description = "Violação de regras de negócio")
            }
    )
    @Override
    public VehicleDomain update(@PathVariable("id") Long id, @RequestBody @Valid VehicleInputFormDto input) {
        return saveVehiclesUseCase.createOrUpdate(new VehicleInputDto(
                id,
                input.brandId(),
                input.vehicle(),
                input.year(),
                input.color(),
                input.description(),
                input.wasSold()
        ));
    }

    @PatchMapping("/{id}")
    @Operation(
            summary = "Atualizar veículo",
            description = "Atualiza dados de um veículo parcialmente",
            responses = {
                    @ApiResponse(responseCode = "200", content = {
                            @Content(schema = @Schema(implementation = VehicleDomain.class))
                    }),
                    @ApiResponse(responseCode = "424", description = "Violação de regras de negócio")
            }
    )
    @Override
    public VehicleDomain partialUpdate(@PathVariable("id") Long id, @RequestBody PartialUpdateInputFormDto input) {
        return null;
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Remover veículo",
            description = "Remover um veículo por ID",
            responses = {
                    @ApiResponse(responseCode = "200", content = {
                            @Content(schema = @Schema(implementation = VehicleDomain.class))
                    }),
                    @ApiResponse(responseCode = "424", description = "Violação de regras de negócio")
            }
    )
    @Override
    public void delete(@PathVariable Long id) {
        deleteVehiclesUseCase.deleteBy(id);
    }

}