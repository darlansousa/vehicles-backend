package com.exercises.vehicles.dataprovider.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "vehicles")
@EntityListeners(AuditingEntityListener.class)
public class VehicleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne
    @JoinColumn(name="brand_id", nullable=false)
    private BrandEntity brand;
    @Column(name = "vehicle")
    private String vehicle;
    @Column(name = "`year`")
    private Integer year;
    @Column(name = "color")
    private String color;
    @Column(name = "description")
    private String description;
    @Column(name = "was_sold")
    private Boolean wasSold;
    @CreatedDate
    @Column(name = "created", nullable = false, updatable = false)
    private LocalDateTime created;
    @LastModifiedDate
    @Column(name = "updated", nullable = true)
    private LocalDateTime updated;

}

