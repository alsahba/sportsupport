package com.sport.support.infrastructure.entity;

import com.sport.support.infrastructure.abstractions.entity.AbstractEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class District extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "CITY_ID", nullable = false, insertable = false, updatable = false)
    private City city;

    @Column(name = "NAME")
    private String name;

    public District(Long id) {
        super.setId(id);
    }
}
