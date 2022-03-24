package com.sport.support.employee.adapter.in.web;

import com.sport.support.employee.adapter.in.web.payload.AddEmployeeRequest;
import com.sport.support.employee.adapter.out.persistence.Employee;
import com.sport.support.employee.application.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {

   private final EmployeeService employeeService;

   @PostMapping
   @PreAuthorize("hasPermission(#request, 'WRITE')")
   public ResponseEntity addEmployee(@Valid @RequestBody AddEmployeeRequest request) {
      employeeService.add(new Employee(request));
      return ResponseEntity.ok().build();
   }
}