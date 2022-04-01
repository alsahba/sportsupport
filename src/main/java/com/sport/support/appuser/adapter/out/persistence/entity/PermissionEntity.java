package com.sport.support.appuser.adapter.out.persistence.entity;

import com.sport.support.shared.abstractions.entity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "permission")
@Getter
@Setter
public class PermissionEntity extends AbstractEntity {

    private String name;

}
