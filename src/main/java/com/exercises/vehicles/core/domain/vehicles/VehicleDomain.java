package com.exercises.vehicles.core.domain.vehicles;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VehicleDomain {
    private Long id;
    private BrandDomain brand;
    private String vehicle;
    private Integer year;
    private String color;
    private String description;
    private Boolean wasSold;

}
