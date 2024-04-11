package com.exercises.vehicles.core.common.mapper;

import java.util.List;

public interface BaseMapper<E, D> {
    D toDomain(E entity);

    E toEntity(D domain);

    List<D> toDomain(List<E> entities);

}
