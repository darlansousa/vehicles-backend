package com.exercises.vehicles.dataprovider.database.mapper;

import com.exercises.vehicles.core.common.mapper.BaseMapper;
import com.exercises.vehicles.core.domain.vehicles.BrandDomain;
import com.exercises.vehicles.dataprovider.database.entity.BrandEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface BrandMapper extends BaseMapper<BrandEntity, BrandDomain> {
}
