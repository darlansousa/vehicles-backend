package com.exercises.vehicles.core.domain.vehicles;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BrandDomain {
    private Long id;
    private String name;

}
