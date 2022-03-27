package com.sport.support.exercise.adapter.in.web.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class AddExerciseRequest {

   @NotBlank
   @Size(max = 128)
   private String name;

   @NotBlank
   @Size(max = 256)
   private String description;

   @NotBlank
   @Size(max = 128)
   private String videoUrl;

}
