package com.sport.support.infrastructure.entity;

import com.sport.support.infrastructure.abstractions.entity.AbstractAuditableEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "CITY")
@Data
@NoArgsConstructor
public class City extends AbstractAuditableEntity {

    @OneToMany(mappedBy = "city")
    private Set<District> districtList;

    @Column(name = "NAME")
    private String name;

    @Column(name = "AREA_CODE")
    private int areaCode;

    public City(Long id) {
        super.setId(id);
    }
}
