package com.exercises.vehicles.dataprovider.database.repository;

import com.exercises.vehicles.dataprovider.database.entity.VehicleEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehiclesJPARepository extends CrudRepository<VehicleEntity, Long> {

    @Query("""
             SELECT vehicle FROM VehicleEntity vehicle WHERE
              (:brandName IS NULL OR vehicle.brand.name = :brandName)
              AND (:year IS NULL OR vehicle.year = :year)
              AND (:color IS NULL OR vehicle.color = :color)""")
    List<VehicleEntity> findByBrandNameAndYearAndColor(
            @Param("brandName") String brandName,
            @Param("year") Long year,
            @Param("color") String color);

}