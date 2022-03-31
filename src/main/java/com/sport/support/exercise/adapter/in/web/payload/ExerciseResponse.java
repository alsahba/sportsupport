package com.sport.support.exercise.adapter.in.web.payload;

import com.sport.support.exercise.domain.Exercise;
import lombok.Getter;

@Getter
public class ExerciseResponse {

   private final Long id;
   private final String name;
   private final String description;
   private final String videoUrl;

   public ExerciseResponse(Exercise exercise) {
      this.id = exercise.getId();
      this.name = exercise.getName();
      this.description = exercise.getDescription();
      this.videoUrl = exercise.getVideoUrl();
   }
}
