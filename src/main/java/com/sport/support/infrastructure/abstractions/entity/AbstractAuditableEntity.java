package com.sport.support.infrastructure.abstractions.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class AbstractAuditableEntity extends AbstractEntity {

    @Column(name = "CREATION_TIMESTAMP")
    private LocalDateTime creationTimestamp;

    @Column(name = "UPDATE_TIMESTAMP")
    private LocalDateTime updateTimestamp;

    @Column(name = "VERSION")
    private int version;

    @PrePersist
    void preInsert() {
        if (this.creationTimestamp == null) {
            this.creationTimestamp = LocalDateTime.now();
            this.version = 0;
        }
        this.version = getVersion() + 1;
    }

    @PreUpdate
    void preUpdate() {
        this.updateTimestamp = LocalDateTime.now();
        this.version = getVersion() + 1;
    }
}
