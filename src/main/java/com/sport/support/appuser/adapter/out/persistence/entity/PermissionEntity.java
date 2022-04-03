package com.sport.support.appuser.adapter.out.persistence.entity;

import com.sport.support.shared.abstractions.entity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "permission")
public class PermissionEntity extends AbstractEntity {

    private String name;

}
