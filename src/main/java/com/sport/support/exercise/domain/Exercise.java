package com.sport.support.exercise.domain;

import com.sport.support.exercise.application.port.in.command.AddExerciseCommand;
import com.sport.support.exercise.domain.vo.ExerciseId;
import com.sport.support.shared.abstractions.domain.AbstractDomainObject;
import lombok.*;

@Getter
@Setter
public class Exercise extends AbstractDomainObject<ExerciseId> {

   private String name;
   private String description;
   private String videoUrl;

   public Exercise(AddExerciseCommand command) {
      setName(command.getName());
      setDescription(command.getDescription());
      setVideoUrl(command.getVideoUrl());
   }

   @Builder
   public Exercise(ExerciseId idVO, String name, String description, String videoUrl) {
      super(idVO);
      this.name = name;
      this.description = description;
      this.videoUrl = videoUrl;
   }
}
