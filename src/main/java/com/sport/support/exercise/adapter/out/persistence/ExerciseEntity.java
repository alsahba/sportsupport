package com.sport.support.exercise.adapter.out.persistence;

import com.sport.support.exercise.domain.Exercise;
import com.sport.support.infrastructure.abstractions.entity.AbstractEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "EXERCISE")
@NoArgsConstructor
public class ExerciseEntity extends AbstractEntity {

   private String name;

   private String description;

   private String videoUrl;

   public ExerciseEntity(Long id) {
      super(id);
   }

   public ExerciseEntity(Exercise exercise) {
      setName(exercise.getName());
      setDescription(exercise.getDescription());
      setVideoUrl(exercise.getVideoUrl());
   }

   public Exercise toDomain() {
      return Exercise.builder()
          .id(getId())
          .name(getName())
          .description(getDescription())
          .videoUrl(getVideoUrl())
          .build();
   }
}
