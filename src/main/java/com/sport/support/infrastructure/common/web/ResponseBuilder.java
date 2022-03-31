package com.sport.support.infrastructure.common.web;

import java.util.List;

public class ResponseBuilder {

   public static <T> Response<DataResponse<T>> build(List<T> items) {
      return new Response<>(new DataResponse<>(items));
   }

   public static <T> Response<T> build(T item) {
      return new Response<>(item);
   }

   public static <T> Response<T> build(ErrorResponse error) {
      return new Response<>(error);
   }

   public static Response build() {
      return new Response(ResponseStatus.SUCCESS);
   }

}
