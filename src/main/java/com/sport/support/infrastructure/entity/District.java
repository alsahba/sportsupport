package com.sport.support.infrastructure.entity;

import com.sport.support.infrastructure.abstractions.entity.AbstractAuditableEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "DISTRICT")
@Data
@NoArgsConstructor
public class District extends AbstractAuditableEntity {

    @ManyToOne
    @JoinColumn(name = "CITY_ID", nullable = false, insertable = false, updatable = false)
    private City city;

    @Column(name = "NAME")
    private String name;

    public District(Long id) {
        super.setId(id);
    }
}
