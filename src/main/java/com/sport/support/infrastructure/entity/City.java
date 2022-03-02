package com.sport.support.infrastructure.entity;

import com.sport.support.infrastructure.abstractions.entity.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "CITY")
@Data
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
