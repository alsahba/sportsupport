package com.sport.support.appuser.entity;

import com.sport.support.infrastructure.abstractions.entity.AbstractAuditableEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Otp extends AbstractAuditableEntity {

    @Column(name = "CODE")
    private String code;

    @Column(name = "username")
    private String username;

    @Column(name = "EXPIRY_DATE")
    private LocalDateTime expiryDate;

    public Otp (String code, String username) {
        this.code = code;
        this.username = username;
        this.expiryDate = LocalDateTime.now().plusMinutes(5);
    }
}
