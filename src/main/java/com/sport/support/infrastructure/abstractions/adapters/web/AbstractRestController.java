package com.sport.support.infrastructure.abstractions.adapters.web;

import com.sport.support.infrastructure.common.web.Response;

public abstract class AbstractRestController {

   public <T> Response<T> respond(T data) {
      return new Response<>(data);
   }
}
