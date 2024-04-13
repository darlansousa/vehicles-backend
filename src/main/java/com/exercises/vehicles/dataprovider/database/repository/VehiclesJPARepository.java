package com.exercises.vehicles.dataprovider.database.repository;

import com.exercises.vehicles.dataprovider.database.entity.VehicleEntity;
import com.exercises.vehicles.dataprovider.database.entity.metrics.IVehicleBrandCount;
import com.exercises.vehicles.dataprovider.database.entity.metrics.IVehicleDecadeCount;
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

    @Query("""
            SELECT vehicle.brand.name brandName, COUNT(vehicle.brand.name) total
             FROM VehicleEntity vehicle GROUP BY vehicle.brand.name ORDER BY vehicle.brand.name DESC""")
    List<IVehicleBrandCount> countTotalVehiclesByBrandName();

    @Query("""
            SELECT
                (vehicle.year - (vehicle.year % 10)) decade,
                COUNT(vehicle.year) total
             FROM VehicleEntity vehicle GROUP BY (vehicle.year - (vehicle.year % 10))
             ORDER BY (vehicle.year - (vehicle.year % 10)) DESC""")
    List<IVehicleDecadeCount> countTotalVehiclesByDecade();

    Long countByWasSold(Boolean wasSold);

}