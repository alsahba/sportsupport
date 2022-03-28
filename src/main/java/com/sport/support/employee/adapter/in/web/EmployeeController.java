package com.sport.support.employee.adapter.in.web;

import com.sport.support.employee.adapter.in.web.payload.AddEmployeeRequest;
import com.sport.support.employee.application.port.in.command.AddEmployeeCommand;
import com.sport.support.employee.application.port.in.usecase.AddEmployeeUC;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

   private final AddEmployeeUC addEmployeeUC;

   @PostMapping
   @PreAuthorize("hasPermission(#request, 'WRITE')")
   public ResponseEntity addEmployee(@Valid @RequestBody AddEmployeeRequest request) {
      addEmployeeUC.add(new AddEmployeeCommand(request));
      return ResponseEntity.ok().build();
   }
}