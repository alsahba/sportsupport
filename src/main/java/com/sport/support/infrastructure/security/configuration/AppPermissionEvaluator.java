package com.sport.support.infrastructure.security.configuration;

import com.sport.support.employee.adapter.in.web.payload.AddEmployeeRequest;
import com.sport.support.employee.domain.enumeration.EmployeeType;
import com.sport.support.infrastructure.security.enumeration.PermissionEnum;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

import java.io.Serializable;

@Configuration
public class AppPermissionEvaluator implements PermissionEvaluator {

   @Override
   public boolean hasPermission(Authentication authentication, Object o, Object o1) {
      var authorities = authentication.getAuthorities();
      if (o instanceof AddEmployeeRequest request) {
         if (EmployeeType.TRAINER.equals(request.getType())) {
            return authorities.contains(PermissionEnum.TRAINER_WRITE.toGrantedAuthority());
         } else {
            return authorities.contains(PermissionEnum.MANAGER_WRITE.toGrantedAuthority());
         }
      }
      return false;
   }

   @Override
   public boolean hasPermission(Authentication authentication, Serializable serializable, String s, Object o) {
      return false;
   }
}
