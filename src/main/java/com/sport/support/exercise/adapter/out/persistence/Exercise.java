package com.sport.support.exercise.adapter.out.persistence;

import com.sport.support.exercise.application.port.in.command.AddExerciseCommand;
import com.sport.support.infrastructure.abstractions.entity.AbstractEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Exercise extends AbstractEntity {

   private String name;

   private String description;

   private String videoUrl;

   public Exercise(AddExerciseCommand command) {
      setName(command.getName());
      setDescription(command.getDescription());
      setVideoUrl(command.getVideoUrl());
   }

   public Exercise(Long id) {
      super(id);
   }
}
