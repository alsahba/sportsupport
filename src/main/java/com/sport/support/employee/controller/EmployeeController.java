package com.sport.support.employee.controller;

import com.sport.support.employee.controller.dto.AddEmployeeRequest;
import com.sport.support.employee.entity.Employee;
import com.sport.support.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
   // TODO: 21.03.2022 authorisation
   public ResponseEntity makeManager(@Valid @RequestBody AddEmployeeRequest request) {
      employeeService.makeEmployee(new Employee(request));
      return ResponseEntity.ok().build();
   }
}