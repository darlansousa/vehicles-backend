package com.exercises.vehicles.core.gateway;

import com.exercises.vehicles.core.domain.vehicles.VehicleDomain;

import java.util.List;
import java.util.Optional;

public interface VehiclesGateway {

    List<VehicleDomain> findAll();

    List<VehicleDomain> findBy(String brand, Long year, String color);

    Optional<VehicleDomain> findBy(Long id);

    VehicleDomain createOrUpdate(VehicleDomain vehicle);

    void deleteBy(Long id);

}
