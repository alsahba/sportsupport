package com.sport.support.membership.adapter.out.persistence.entity;


import com.sport.support.appuser.adapter.out.persistence.entity.AppUser;
import com.sport.support.branch.adapter.out.persistence.entity.Branch;
import com.sport.support.infrastructure.abstractions.entity.AbstractAuditableEntity;
import com.sport.support.membership.adapter.out.persistence.enumeration.Duration;
import com.sport.support.membership.adapter.out.persistence.enumeration.Status;
import com.sport.support.membership.adapter.out.persistence.enumeration.Type;
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
   private AppUser user;

   @OneToOne
   @JoinColumn(name = "TRAINER_ID", nullable = false)
   private AppUser trainer;

   @ManyToOne
   @JoinColumn(name = "BRANCH_ID", nullable = false)
   private Branch branch;

   @Enumerated(EnumType.STRING)
   private Duration duration;

   @Enumerated(EnumType.STRING)
   private Type type;

   @Enumerated(EnumType.STRING)
   private Status status;

   private int loginAttempt;

   private LocalDateTime endDate;

   public MembershipEntity(Membership membership) {
      this.user = new AppUser(membership.getUserId());
      this.trainer = new AppUser(membership.getTrainerId());
      this.branch = new Branch(membership.getBranchId());
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
          branch.getId(),
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
