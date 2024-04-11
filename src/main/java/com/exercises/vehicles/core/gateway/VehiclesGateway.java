package com.exercises.vehicles.core.gateway;

import com.exercises.vehicles.core.domain.vehicles.VehicleDomain;

import java.util.List;
import java.util.Optional;

public interface VehiclesGateway {

    List<VehicleDomain> findAll();

    Optional<VehicleDomain> findBy(Long id);

    VehicleDomain createOrUpdate(VehicleDomain vehicle);

    void deleteBy(Long id);

}
