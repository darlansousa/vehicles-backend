package com.exercises.vehicles.core.gateway;

import com.exercises.vehicles.core.domain.metrics.VehicleBrandCount;
import com.exercises.vehicles.core.domain.metrics.VehicleDecadeCount;
import com.exercises.vehicles.core.domain.vehicles.VehicleDomain;

import java.util.List;
import java.util.Optional;

public interface VehiclesGateway {

    List<VehicleDomain> findAll();

    List<VehicleDomain> findBy(String brand, Long year, String color);

    Optional<VehicleDomain> findBy(Long id);

    VehicleDomain createOrUpdate(VehicleDomain vehicle);

    List<VehicleBrandCount> countTotalVehiclesByBrandName();

    List<VehicleDecadeCount> countTotalVehiclesByDecade();

    Long contAll();

    Long countByWasSold(Boolean wasSold);

    void deleteBy(Long id);

}
