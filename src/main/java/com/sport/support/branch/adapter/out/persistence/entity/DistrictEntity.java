package com.sport.support.branch.adapter.out.persistence.entity;

import com.sport.support.infrastructure.abstractions.entity.AbstractEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "DISTRICT")
@Getter
@Setter
@NoArgsConstructor
public class DistrictEntity extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "CITY_ID", referencedColumnName = "ID", nullable = false, insertable = false, updatable = false)
    private CityEntity city;

    @Column(nullable = false)
    private String name;

    public DistrictEntity(Long id) {
        super.setId(id);
    }
}
