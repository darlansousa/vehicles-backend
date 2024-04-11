package com.exercises.vehicles.dataprovider.database.mapper;


import com.exercises.vehicles.core.common.mapper.BaseMapper;
import com.exercises.vehicles.core.domain.vehicles.VehicleDomain;
import com.exercises.vehicles.dataprovider.database.entity.VehicleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE, uses = {BrandMapper.class})
public interface VehicleMapper extends BaseMapper<VehicleEntity, VehicleDomain> {

}
