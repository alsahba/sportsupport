package com.sport.support.branch.adapter.out.persistence.entity;

import com.sport.support.shared.abstractions.entity.AbstractEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "DISTRICT")
@NoArgsConstructor
public class DistrictEntity extends AbstractEntity {

    @ManyToOne
    @JoinColumn(nullable = false, insertable = false, updatable = false)
    private CityEntity city;

    @Column(nullable = false)
    private String name;

    public DistrictEntity(Long id) {
        super.setId(id);
    }
}
