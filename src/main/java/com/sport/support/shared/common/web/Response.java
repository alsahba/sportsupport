package com.sport.support.shared.common.web;

import lombok.Getter;

@Getter
public class Response<T> {

   private final ResponseStatus status;
   private final String message;
   private T data;
   private ErrorResponse error;

   public Response(ResponseStatus status) {
      this.status = status;
      this.message = status.getMessage();
   }

   public Response(T data) {
      this(ResponseStatus.SUCCESS);
      this.data = data;
   }

   public Response(ErrorResponse error) {
      this(ResponseStatus.FAILURE);
      this.error = error;
   }

}
