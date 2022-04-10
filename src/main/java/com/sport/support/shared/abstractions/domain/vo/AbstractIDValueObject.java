package com.sport.support.shared.abstractions.domain.vo;

import org.apache.commons.lang3.builder.EqualsBuilder;

import java.util.Objects;

public abstract class AbstractIDValueObject {

   protected final Long id;

   protected AbstractIDValueObject(Long id) {
      this.id = id;
   }

   public Long getId() {
      return id;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      AbstractIDValueObject that = (AbstractIDValueObject) o;
      return new EqualsBuilder().append(getId(), that.getId()).isEquals();
   }

   @Override
   public int hashCode() {
      return Objects.hash(id);
   }
}
