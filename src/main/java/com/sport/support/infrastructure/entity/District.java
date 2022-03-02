package com.sport.support.infrastructure.entity;

import com.sport.support.infrastructure.abstractions.entity.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "DISTRICT")
@Data
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
