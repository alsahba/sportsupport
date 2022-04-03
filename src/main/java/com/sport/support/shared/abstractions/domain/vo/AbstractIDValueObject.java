package com.sport.support.shared.abstractions.domain.vo;

public abstract class AbstractIDValueObject {

   protected final Long id;

   protected AbstractIDValueObject(Long id) {
      this.id = id;
   }

   public Long getId() {
      return id;
   }
}
