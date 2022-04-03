package com.sport.support.plan.adapter.out.persistence.entity;

import com.sport.support.exercise.adapter.out.persistence.ExerciseEntity;
import com.sport.support.plan.domain.PlanExercise;
import com.sport.support.shared.abstractions.entity.AbstractEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "PLAN_EXERCISE")
@NoArgsConstructor
public class PlanExerciseEntity extends AbstractEntity {

   @ManyToOne
   private PlanEntity plan;

   @ManyToOne
   private ExerciseEntity exercise;

   private int sets;

   private boolean completed;

   public PlanExerciseEntity(PlanExercise planExercise, PlanEntity plan) {
      this(planExercise);
      setPlan(plan);
   }

   public PlanExerciseEntity(PlanExercise planExercise) {
      setId(planExercise.getId());
      setPlan(new PlanEntity(planExercise.getPlanId()));
      setExercise(new ExerciseEntity(planExercise.getExerciseId()));
      setSets(planExercise.getSets());
      setCompleted(planExercise.isCompleted());
   }

   public PlanExercise toDomain() {
      return PlanExercise.builder()
            .id(getId())
            .planId(this.getPlan().getId())
            .exerciseId(this.getExercise().getId())
            .sets(getSets())
            .completed(isCompleted())
            .build();
   }
}
