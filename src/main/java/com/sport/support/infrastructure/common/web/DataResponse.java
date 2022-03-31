package com.sport.support.infrastructure.common.web;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class DataResponse<T> {

   private List<T> items;
   private Integer page;
   private Integer pageSize;
   private Integer totalPages;

   DataResponse (List<T> items, Integer page, Integer pageSize, Integer totalPages) {
      this.items = items;
      this.page = page;
      this.pageSize = pageSize;
      this.totalPages = totalPages;
   }

   DataResponse(List<T> items) {
      this.items = items;
   }
}
