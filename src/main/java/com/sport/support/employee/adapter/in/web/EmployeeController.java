package com.sport.support.employee.adapter.in.web;

import com.sport.support.employee.adapter.in.web.payload.AddEmployeeRequest;
import com.sport.support.employee.adapter.in.web.payload.ChangeSalaryRequest;
import com.sport.support.employee.adapter.in.web.payload.EmployeeResponse;
import com.sport.support.employee.application.port.in.command.AddEmployeeCommand;
import com.sport.support.employee.application.port.in.command.ChangeSalaryCommand;
import com.sport.support.employee.application.port.in.usecase.AddEmployeeUC;
import com.sport.support.employee.application.port.in.usecase.ChangeSalaryUC;
import com.sport.support.shared.abstractions.adapters.web.AbstractController;
import com.sport.support.shared.common.web.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.security.Principal;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController extends AbstractController {

   private final AddEmployeeUC addEmployeeUC;
   private final ChangeSalaryUC changeSalaryUC;

   @PostMapping
   @PreAuthorize("hasPermission(#request, 'WRITE')")
   @ResponseStatus(value = HttpStatus.CREATED)
   public Response<EmployeeResponse> add(@Valid @RequestBody AddEmployeeRequest request) {
      var employee = addEmployeeUC.add(new AddEmployeeCommand(request));
      return respond(new EmployeeResponse(employee));
   }

   @PostMapping(value = "/{id}/salary")
   @PreAuthorize("hasRole('ROLE_MANAGER')")
   @ResponseStatus(value = HttpStatus.CREATED)
   public Response<Long> changeSalary(@Valid @PathVariable @Positive Long id,
                                      @Valid @RequestBody ChangeSalaryRequest request,
                                      Principal principal) {
      changeSalaryUC.change(new ChangeSalaryCommand(id, getUserIdFromAuth(principal), request));
      return respond(id);
   }
}