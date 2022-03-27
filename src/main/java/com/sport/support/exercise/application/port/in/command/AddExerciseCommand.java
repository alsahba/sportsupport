package com.sport.support.exercise.application.port.in.command;

import com.sport.support.exercise.adapter.in.web.payload.AddExerciseRequest;
import lombok.Getter;

@Getter
public class AddExerciseCommand {

   private final String name;
   private final String description;
   private final String videoUrl;

   public AddExerciseCommand(AddExerciseRequest request) {
      this.name = request.getName();
      this.description = request.getDescription();
      this.videoUrl = request.getVideoUrl();
   }
}
