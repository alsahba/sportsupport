package com.sport.support.infrastructure.exception;

public class DatabaseException extends RuntimeException {

   public DatabaseException(Exception e) {
      super(e.getMessage(), e.getCause());
   }

}
