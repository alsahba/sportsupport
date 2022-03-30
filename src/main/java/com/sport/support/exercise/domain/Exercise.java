package com.sport.support.exercise.domain;

import com.sport.support.exercise.application.port.in.command.AddExerciseCommand;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class Exercise {

   private Long id;
   private String name;
   private String description;
   private String videoUrl;

   public Exercise(AddExerciseCommand command) {
      setName(command.getName());
      setDescription(command.getDescription());
      setVideoUrl(command.getVideoUrl());
   }
}
