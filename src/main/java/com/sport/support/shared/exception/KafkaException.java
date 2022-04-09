package com.sport.support.shared.exception;

import lombok.Getter;

@Getter
public class KafkaException extends RuntimeException {

   public KafkaException(String message) {
      super(message);
   }

}
