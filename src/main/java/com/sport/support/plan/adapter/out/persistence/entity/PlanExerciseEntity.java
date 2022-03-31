package com.sport.support.plan.adapter.out.persistence.entity;

import com.sport.support.exercise.adapter.out.persistence.ExerciseEntity;
import com.sport.support.infrastructure.abstractions.entity.AbstractEntity;
import com.sport.support.plan.domain.PlanExercise;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "PLAN_EXERCISE")
@NoArgsConstructor
public class PlanExerciseEntity extends AbstractEntity {

   @ManyToOne
   @JoinColumn(name = "PLAN_ID", referencedColumnName = "ID")
   private PlanEntity planEntity;

   @ManyToOne
   @JoinColumn(name = "EXERCISE_ID", referencedColumnName = "ID")
   private ExerciseEntity exerciseEntity;

   private int sets;

   private boolean completed;

   public PlanExerciseEntity(PlanExercise planExercise, PlanEntity planEntity) {
      this(planExercise);
      setPlanEntity(planEntity);
   }

   public PlanExerciseEntity(PlanExercise planExercise) {
      setId(planExercise.getId());
      setPlanEntity(new PlanEntity(planExercise.getPlanId()));
      setPlanEntity(planEntity);
      setExerciseEntity(new ExerciseEntity(planExercise.getExerciseId()));
      setSets(planExercise.getSets());
      setCompleted(planExercise.isCompleted());
   }

   public PlanExercise toDomain() {
      return PlanExercise.builder()
            .id(getId())
            .planId(getPlanEntity().getId())
            .exerciseId(getExerciseEntity().getId())
            .sets(getSets())
            .completed(isCompleted())
            .build();
   }
}
