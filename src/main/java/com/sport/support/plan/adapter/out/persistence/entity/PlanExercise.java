package com.sport.support.plan.adapter.out.persistence.entity;

import com.sport.support.exercise.adapter.out.persistence.Exercise;
import com.sport.support.infrastructure.abstractions.entity.AbstractEntity;
import com.sport.support.plan.application.port.in.command.DailyPlanExerciseCommand;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class PlanExercise extends AbstractEntity {

   @ManyToOne
   @JoinColumn(name = "PLAN_ID", referencedColumnName = "ID")
   private Plan plan;

   @ManyToOne
   @JoinColumn(name = "EXERCISE_ID", referencedColumnName = "ID")
   private Exercise exercise;

   private int sets;

   public PlanExercise(DailyPlanExerciseCommand command, Plan plan) {
      setSets(command.getSets());
      setPlan(plan);
      setExercise(new Exercise(command.getId()));
   }
}
