package com.sport.support.appuser.adapter.out.persistence.entity;

import com.sport.support.infrastructure.abstractions.entity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class Permission extends AbstractEntity {

    private String name;

}
