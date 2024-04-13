package com.exercises.vehicles.entrypoint.api.controller.vehicles;

import com.exercises.vehicles.core.domain.vehicles.VehicleDomain;
import com.exercises.vehicles.entrypoint.api.dto.vehicles.input.PartialUpdateInputFormDto;
import com.exercises.vehicles.entrypoint.api.dto.vehicles.input.VehicleInputFormDto;

import java.util.List;

public interface VehiclesControllerV1 {


    List<VehicleDomain> getAll(String brand, Long year, String color);

    VehicleDomain getBy(Long id);


    VehicleDomain save(VehicleInputFormDto input);


    VehicleDomain update(Long id, VehicleInputFormDto input);


    VehicleDomain partialUpdate(Long id, PartialUpdateInputFormDto input);


    void delete(Long id);

}