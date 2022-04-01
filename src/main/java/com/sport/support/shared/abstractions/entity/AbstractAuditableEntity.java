package com.sport.support.shared.abstractions.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractAuditableEntity extends AbstractEntity {

    @CreatedDate
    private LocalDateTime creationTimestamp;

    @LastModifiedDate
    private LocalDateTime updateTimestamp;

    @Version
    private int version;

    public void copyFrom(AbstractAuditableEntity entity) {
        this.creationTimestamp = entity.getCreationTimestamp();
        this.updateTimestamp = entity.getUpdateTimestamp();
        this.version = entity.getVersion();
    }

    public AbstractAuditableEntity(Long id) {
        super(id);
    }
}
