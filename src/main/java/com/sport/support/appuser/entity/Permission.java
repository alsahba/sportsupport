package com.sport.support.appuser.entity;

import com.sport.support.infrastructure.abstractions.entity.AbstractEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class Permission extends AbstractEntity {

    @Column(name = "NAME")
    private String name;

}
