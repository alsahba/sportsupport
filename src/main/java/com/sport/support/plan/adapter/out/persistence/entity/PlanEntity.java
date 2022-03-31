package com.sport.support.plan.adapter.out.persistence.entity;

import com.sport.support.appuser.adapter.out.persistence.entity.AppUser;
import com.sport.support.infrastructure.abstractions.entity.AbstractAuditableEntity;
import com.sport.support.plan.domain.Plan;
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
@NoArgsConstructor
public class PlanEntity extends AbstractAuditableEntity {

   @ManyToOne
   @JoinColumn(name = "USER_ID")
   private AppUser user;

   private LocalDate date;

   @OneToMany(mappedBy = "planEntity", cascade = CascadeType.ALL)
   private Set<PlanExerciseEntity> planExerciseEntities;

   public Plan toDomain() {
      // TODO: 30.03.2022 builder cause problems
      var plan = new Plan();
      plan.setId(getId());
      plan.setDate(getDate());
      plan.setPlanExercises(getPlanExerciseEntities().stream()
          .map(PlanExerciseEntity::toDomain).collect(Collectors.toSet()));
      return plan;
   }

   public PlanEntity(Plan plan) {
      setId(plan.getId());
      setDate(plan.getDate());
      setPlanExerciseEntities(plan.getPlanExercises().stream()
          .map(e -> new PlanExerciseEntity(e, this)).collect(Collectors.toSet()));
   }

   public PlanEntity(Long id) {
      super(id);
   }
}
