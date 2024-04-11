package com.exercises.vehicles.dataprovider.database.repository;

import com.exercises.vehicles.dataprovider.database.entity.VehicleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehiclesJPARepository extends CrudRepository<VehicleEntity, Long> {

}