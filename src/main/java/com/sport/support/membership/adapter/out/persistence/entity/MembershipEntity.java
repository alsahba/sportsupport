package com.sport.support.membership.adapter.out.persistence.entity;


import com.sport.support.appuser.adapter.out.persistence.entity.AppUserEntity;
import com.sport.support.branch.adapter.out.persistence.entity.BranchEntity;
import com.sport.support.shared.abstractions.entity.AbstractAuditableEntity;
import com.sport.support.membership.domain.enumeration.Duration;
import com.sport.support.membership.domain.enumeration.Status;
import com.sport.support.membership.domain.enumeration.Type;
import com.sport.support.membership.domain.Membership;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "MEMBERSHIP")
@Getter
@Setter
@NoArgsConstructor
public class MembershipEntity extends AbstractAuditableEntity {

   @OneToOne
   @JoinColumn(name = "USER_ID", nullable = false)
   private AppUserEntity user;

   @OneToOne
   @JoinColumn(name = "TRAINER_ID", nullable = false)
   private AppUserEntity trainer;

   @ManyToOne
   @JoinColumn(name = "BRANCH_ID", nullable = false)
   private BranchEntity branchEntity;

   @Enumerated(EnumType.STRING)
   private Duration duration;

   @Enumerated(EnumType.STRING)
   private Type type;

   @Enumerated(EnumType.STRING)
   private Status status;

   private int loginAttempt;

   private LocalDateTime endDate;

   public MembershipEntity(Membership membership) {
      this.user = new AppUserEntity(membership.getUserId());
      this.trainer = new AppUserEntity(membership.getTrainerId());
      this.branchEntity = new BranchEntity(membership.getBranchId());
      this.duration = membership.getDuration();
      this.type = membership.getType();
      this.status = membership.getStatus();
      this.loginAttempt = membership.getLoginAttempt();
      this.endDate = membership.getEndDate();
   }

   public Membership toDomain() {
      return new Membership(
          getId(),
          user.getId(),
          branchEntity.getId(),
          trainer.getId(),
          status,
          type,
          duration,
          loginAttempt,
          endDate
      );
   }

   public void update(MembershipEntity entity) {
      copyFrom(entity);
   }
}
