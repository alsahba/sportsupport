package com.sport.support.membership.adapter.out.persistence.entity;


import com.sport.support.appuser.adapter.out.persistence.entity.AppUserEntity;
import com.sport.support.appuser.domain.vo.UserId;
import com.sport.support.branch.adapter.out.persistence.entity.BranchEntity;
import com.sport.support.branch.domain.vo.BranchId;
import com.sport.support.employee.adapter.out.persistence.entity.EmployeeEntity;
import com.sport.support.employee.domain.vo.EmployeeId;
import com.sport.support.membership.domain.Membership;
import com.sport.support.membership.domain.enumeration.Duration;
import com.sport.support.membership.domain.enumeration.Status;
import com.sport.support.membership.domain.enumeration.Type;
import com.sport.support.membership.domain.vo.MembershipId;
import com.sport.support.shared.abstractions.entity.AbstractAuditableEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "MEMBERSHIP")
@NoArgsConstructor
public class MembershipEntity extends AbstractAuditableEntity {

   @OneToOne
   @JoinColumn(nullable = false)
   private AppUserEntity user;

   @OneToOne
   @JoinColumn(nullable = false)
   private EmployeeEntity trainer;

   @ManyToOne
   @JoinColumn(nullable = false)
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
      this.user = new AppUserEntity(membership.getUserId().getId());
      this.trainer = new EmployeeEntity(membership.getTrainerId().getId());
      this.branchEntity = new BranchEntity(membership.getBranchId().getId());
      this.duration = membership.getDuration();
      this.type = membership.getType();
      this.status = membership.getStatus();
      this.loginAttempt = membership.getLoginAttempt();
      this.endDate = membership.getEndDate();
   }

   public Membership toDomain() {
      return Membership.builder()
          .idVO(new MembershipId(getId()))
          .userId(new UserId(user.getId()))
          .trainerId(new EmployeeId(trainer.getId()))
          .branchId(new BranchId(branchEntity.getId()))
          .duration(duration)
          .type(type)
          .status(status)
          .loginAttempt(loginAttempt)
          .endDate(endDate)
          .build();
   }

   public void update(MembershipEntity entity) {
      copyFrom(entity);
   }
}
