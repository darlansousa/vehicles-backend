package com.exercises.vehicles.dataprovider.database.mapper;

import com.exercises.vehicles.core.domain.vehicles.VehicleDomain;
import com.exercises.vehicles.core.domain.vehicles.VehicleDomain.VehicleDomainBuilder;
import com.exercises.vehicles.dataprovider.database.entity.VehicleEntity;
import com.exercises.vehicles.dataprovider.database.entity.VehicleEntity.VehicleEntityBuilder;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-11T08:30:31-0300",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.7.jar, environment: Java 21.0.2 (Azul Systems, Inc.)"
)
@Component
public class VehicleMapperImpl implements VehicleMapper {

    @Autowired
    private BrandMapper brandMapper;

    @Override
    public VehicleDomain toDomain(VehicleEntity entity) {
        if ( entity == null ) {
            return null;
        }

        VehicleDomainBuilder vehicleDomain = VehicleDomain.builder();

        vehicleDomain.id( entity.getId() );
        vehicleDomain.brand( brandMapper.toDomain( entity.getBrand() ) );
        vehicleDomain.vehicle( entity.getVehicle() );
        vehicleDomain.year( entity.getYear() );
        vehicleDomain.color( entity.getColor() );
        vehicleDomain.description( entity.getDescription() );
        vehicleDomain.wasSold( entity.getWasSold() );

        return vehicleDomain.build();
    }

    @Override
    public VehicleEntity toEntity(VehicleDomain domain) {
        if ( domain == null ) {
            return null;
        }

        VehicleEntityBuilder vehicleEntity = VehicleEntity.builder();

        vehicleEntity.id( domain.getId() );
        vehicleEntity.brand( brandMapper.toEntity( domain.getBrand() ) );
        vehicleEntity.vehicle( domain.getVehicle() );
        vehicleEntity.year( domain.getYear() );
        vehicleEntity.color( domain.getColor() );
        vehicleEntity.description( domain.getDescription() );
        vehicleEntity.wasSold( domain.getWasSold() );

        return vehicleEntity.build();
    }

    @Override
    public List<VehicleDomain> toDomain(List<VehicleEntity> entities) {
        if ( entities == null ) {
            return null;
        }

        List<VehicleDomain> list = new ArrayList<VehicleDomain>( entities.size() );
        for ( VehicleEntity vehicleEntity : entities ) {
            list.add( toDomain( vehicleEntity ) );
        }

        return list;
    }
}
