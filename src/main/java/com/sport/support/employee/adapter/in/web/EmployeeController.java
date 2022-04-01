package com.sport.support.employee.adapter.in.web;

import com.sport.support.employee.adapter.in.web.payload.AddEmployeeRequest;
import com.sport.support.employee.adapter.in.web.payload.EmployeeResponse;
import com.sport.support.employee.application.port.in.command.AddEmployeeCommand;
import com.sport.support.employee.application.port.in.usecase.AddEmployeeUC;
import com.sport.support.shared.abstractions.adapters.web.AbstractController;
import com.sport.support.shared.common.web.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController extends AbstractController {

   private final AddEmployeeUC addEmployeeUC;

   @PostMapping
   @PreAuthorize("hasPermission(#request, 'WRITE')")
   @ResponseStatus(value = HttpStatus.CREATED)
   public Response<EmployeeResponse> add(@Valid @RequestBody AddEmployeeRequest request) {
      var employee = addEmployeeUC.add(new AddEmployeeCommand(request));
      return respond(new EmployeeResponse(employee));
   }

   // TODO: 29.03.2022 - update salary info by manager uc
}