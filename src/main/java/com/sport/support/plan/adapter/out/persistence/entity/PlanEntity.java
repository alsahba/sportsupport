package com.sport.support.plan.adapter.out.persistence.entity;

import com.sport.support.appuser.adapter.out.persistence.entity.AppUserEntity;
import com.sport.support.appuser.domain.vo.UserId;
import com.sport.support.plan.domain.Plan;
import com.sport.support.plan.domain.vo.PlanId;
import com.sport.support.shared.abstractions.entity.AbstractAuditableEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@Table(name = "PLAN")
@NoArgsConstructor
public class PlanEntity extends AbstractAuditableEntity {

   @ManyToOne
   private AppUserEntity user;

   private LocalDate date;

   @OneToMany(mappedBy = "plan", cascade = CascadeType.ALL)
   private Set<PlanExerciseEntity> planExerciseEntities;

   public Plan toDomain() {
      return Plan.builder()
          .idVO(new PlanId(getId()))
          .userId(new UserId(getUser().getId()))
          .date(getDate())
          .planExercises(getPlanExerciseEntities().stream()
              .map(PlanExerciseEntity::toDomain).collect(Collectors.toSet()))
          .build();
   }

   public PlanEntity(Plan plan) {
      setId(plan.getId());
      setUser(new AppUserEntity(plan.getUserId().getId()));
      setDate(plan.getDate());
      setPlanExerciseEntities(plan.getPlanExercises().stream()
          .map(e -> new PlanExerciseEntity(e, this)).collect(Collectors.toSet()));
   }
}
