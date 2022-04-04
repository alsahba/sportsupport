package com.sport.support.exercise.domain;

import com.sport.support.exercise.application.port.in.command.AddExerciseCommand;
import com.sport.support.exercise.domain.vo.ExerciseId;
import com.sport.support.shared.abstractions.domain.AbstractDomainObject;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class Exercise extends AbstractDomainObject<ExerciseId> {

   private String name;
   private String description;
   private String videoUrl;

   public Exercise(AddExerciseCommand command) {
      this.name = command.getName();
      this.description = command.getDescription();
      this.videoUrl = command.getVideoUrl();
   }
}
