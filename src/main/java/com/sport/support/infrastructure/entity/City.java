package com.sport.support.infrastructure.entity;

import com.sport.support.infrastructure.abstractions.entity.AbstractEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class City extends AbstractEntity {

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "AREA_CODE", nullable = false)
    private int areaCode;

    public City(Long id) {
        super.setId(id);
    }
}
