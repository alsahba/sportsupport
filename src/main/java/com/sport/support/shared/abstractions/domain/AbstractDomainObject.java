package com.sport.support.shared.abstractions.domain;

import com.sport.support.shared.abstractions.domain.vo.AbstractIDValueObject;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractDomainObject<T extends AbstractIDValueObject> {

   protected T idVO;

   public Long getId() {
      if (idVO == null) {
         return null;
      }
      return idVO.getId();
   }

   public T getIdVO() {
      return idVO;
   }

}
