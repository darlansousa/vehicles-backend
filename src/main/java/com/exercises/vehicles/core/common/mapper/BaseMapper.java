package com.exercises.vehicles.core.common.mapper;

public interface BaseMapper<E, D> {
    D toDomain(E entity);

    E toEntity(D domain);

}
