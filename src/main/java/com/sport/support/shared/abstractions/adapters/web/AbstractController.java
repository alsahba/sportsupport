package com.sport.support.shared.abstractions.adapters.web;

import com.sport.support.shared.common.web.DataResponse;
import com.sport.support.shared.common.web.ErrorResponse;
import com.sport.support.shared.common.web.Response;
import com.sport.support.shared.common.web.ResponseBuilder;

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

   protected <T> Response<DataResponse<T>> respond(List<T> items, int page, int pageSize, int totalPages) {
      return ResponseBuilder.build(items, page, pageSize, totalPages);
   }

   protected Response<ErrorResponse> respond(ErrorResponse error) {
      return ResponseBuilder.build(error);
   }

}
