package com.sport.support.plan.adapter.out.persistence.entity;

import com.sport.support.appuser.adapter.out.persistence.entity.AppUser;
import com.sport.support.infrastructure.abstractions.entity.AbstractAuditableEntity;
import com.sport.support.plan.application.port.in.command.DailyPlanCommand;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Plan extends AbstractAuditableEntity {

   @ManyToOne
   @JoinColumn(name = "USER_ID")
   private AppUser user;

   private LocalDate date;

   @OneToMany(mappedBy = "plan")
   private Set<PlanExercise> planExercises;

   public Plan(DailyPlanCommand command, AppUser user) {
      setUser(user);
      setDate(command.getDate());
      setPlanExercises(command.getExercises().stream()
          .map(e -> new PlanExercise(e, this)).collect(Collectors.toSet()));
   }
}
