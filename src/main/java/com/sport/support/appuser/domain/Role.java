package com.sport.support.appuser.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@Getter
@Builder
public class Role {

   private Long id;
   private String name;
   private Set<String> permissions;
}
