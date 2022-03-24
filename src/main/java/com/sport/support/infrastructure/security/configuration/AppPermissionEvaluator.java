package com.sport.support.infrastructure.security.configuration;

import com.sport.support.employee.adapter.in.web.payload.AddEmployeeRequest;
import com.sport.support.employee.adapter.out.persistence.EmployeeType;
import com.sport.support.infrastructure.security.enumeration.PermissionEnum;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

@Configuration
public class AppPermissionEvaluator implements PermissionEvaluator {

   @Override
   public boolean hasPermission(Authentication authentication, Object o, Object o1) {
      Set<PermissionEnum> permissions = authentication.getAuthorities().stream()
          .map(authority -> PermissionEnum.valueOf(authority.getAuthority()))
          .collect(Collectors.toSet());

      if (o instanceof AddEmployeeRequest) {
         AddEmployeeRequest request = (AddEmployeeRequest) o;
         if (EmployeeType.TRAINER.equals(request.getType())) {
            return permissions.contains(PermissionEnum.TRAINER_WRITE);
         } else {
            return permissions.contains(PermissionEnum.MANAGER_WRITE);
         }
      }
      return false;
   }

   @Override
   public boolean hasPermission(Authentication authentication, Serializable serializable, String s, Object o) {
      return false;
   }
}
