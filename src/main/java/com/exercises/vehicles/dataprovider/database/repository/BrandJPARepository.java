package com.exercises.vehicles.dataprovider.database.repository;

import com.exercises.vehicles.dataprovider.database.entity.BrandEntity;
import com.exercises.vehicles.dataprovider.database.entity.VehicleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandJPARepository extends CrudRepository<BrandEntity, Long> {

}