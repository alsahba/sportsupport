package com.sport.support.infrastructure.entity;

import com.sport.support.infrastructure.abstractions.entity.AbstractEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class City extends AbstractEntity {

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
