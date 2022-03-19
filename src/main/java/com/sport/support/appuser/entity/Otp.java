package com.sport.support.appuser.entity;

import com.sport.support.infrastructure.abstractions.entity.AbstractAuditableEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Otp extends AbstractAuditableEntity {

    private String code;

    private String username;

    private LocalDateTime expiryDate;

    public Otp (String code, String username) {
        this.code = code;
        this.username = username;
        this.expiryDate = LocalDateTime.now().plusMinutes(5);
    }
}
