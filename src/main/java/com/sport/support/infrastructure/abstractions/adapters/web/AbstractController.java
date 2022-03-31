package com.sport.support.infrastructure.abstractions.adapters.web;

import com.sport.support.infrastructure.common.web.DataResponse;
import com.sport.support.infrastructure.common.web.ErrorResponse;
import com.sport.support.infrastructure.common.web.Response;
import com.sport.support.infrastructure.common.web.ResponseBuilder;

import java.util.List;

public abstract class AbstractController {

   protected Response respond() {
      return ResponseBuilder.build();
   }

   protected <T> Response<T> respond(T data) {
      return ResponseBuilder.build(data);
   }

   protected <T> Response<DataResponse<T>> respond(List<T> items) {
      return ResponseBuilder.build(items);
   }

   protected Response<ErrorResponse> respond(ErrorResponse error) {
      return ResponseBuilder.build(error);
   }

}
