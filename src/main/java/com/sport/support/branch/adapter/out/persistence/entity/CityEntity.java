package com.sport.support.branch.adapter.out.persistence.entity;

import com.sport.support.infrastructure.abstractions.entity.AbstractEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "CITY")
@Getter
@Setter
@NoArgsConstructor
public class CityEntity extends AbstractEntity {

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "AREA_CODE", nullable = false)
    private int areaCode;

    public CityEntity(Long id) {
        super.setId(id);
    }
}
