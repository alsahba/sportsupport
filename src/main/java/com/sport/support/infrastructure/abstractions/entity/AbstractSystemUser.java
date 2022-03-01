package com.sport.support.infrastructure.abstractions.entity;

import com.sport.support.appuser.AppUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class AbstractSystemUser extends AbstractAuditableEntity {

    @OneToOne
    @JoinColumn(name = "APP_USER_ID")
    private AppUser appUser;

}
