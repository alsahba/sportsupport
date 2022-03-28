package com.sport.support.employee.application.port.in.command;

import com.sport.support.employee.adapter.out.persistence.enumeration.EmployeeType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FindEmployeeQuery {

   private Long userId;
   private EmployeeType type;

}
