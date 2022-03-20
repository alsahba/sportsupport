package com.sport.support.infrastructure.security.otp.entity;

import com.sport.support.infrastructure.abstractions.entity.AbstractAuditableEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
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
