package com.sport.support.exercise.adapter.in.web.payload;

import com.sport.support.exercise.domain.Exercise;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExerciseResponse {

   private Long id;
   private String name;
   private String description;
   private String videoUrl;

   public static ExerciseResponse success(Exercise exercise) {
      return ExerciseResponse.builder()
         .id(exercise.getId())
         .name(exercise.getName())
         .description(exercise.getDescription())
         .videoUrl(exercise.getVideoUrl())
         .build();
   }
}
