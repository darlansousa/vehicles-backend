package com.exercises.vehicles.dataprovider.database.mapper;

import com.exercises.vehicles.core.domain.vehicles.BrandDomain;
import com.exercises.vehicles.core.domain.vehicles.BrandDomain.BrandDomainBuilder;
import com.exercises.vehicles.dataprovider.database.entity.BrandEntity;
import com.exercises.vehicles.dataprovider.database.entity.BrandEntity.BrandEntityBuilder;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-10T16:02:59-0300",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.5.jar, environment: Java 21.0.2 (Azul Systems, Inc.)"
)
@Component
public class BrandMapperImpl implements BrandMapper {

    @Override
    public BrandDomain toDomain(BrandEntity entity) {
        if ( entity == null ) {
            return null;
        }

        BrandDomainBuilder brandDomain = BrandDomain.builder();

        brandDomain.id( entity.getId() );
        brandDomain.name( entity.getName() );

        return brandDomain.build();
    }

    @Override
    public BrandEntity toEntity(BrandDomain domain) {
        if ( domain == null ) {
            return null;
        }

        BrandEntityBuilder brandEntity = BrandEntity.builder();

        brandEntity.id( domain.getId() );
        brandEntity.name( domain.getName() );

        return brandEntity.build();
    }

    @Override
    public List<BrandDomain> toDomain(List<BrandEntity> entities) {
        if ( entities == null ) {
            return null;
        }

        List<BrandDomain> list = new ArrayList<BrandDomain>( entities.size() );
        for ( BrandEntity brandEntity : entities ) {
            list.add( toDomain( brandEntity ) );
        }

        return list;
    }
}
