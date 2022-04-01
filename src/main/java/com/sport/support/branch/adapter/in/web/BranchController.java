package com.sport.support.branch.adapter.in.web;

import com.sport.support.branch.adapter.in.web.payload.AddBranchRequest;
import com.sport.support.branch.adapter.in.web.payload.BranchResponse;
import com.sport.support.branch.adapter.in.web.payload.UpdateBranchRequest;
import com.sport.support.branch.application.port.in.command.AddBranchCommand;
import com.sport.support.branch.application.port.in.command.FindBranchQuery;
import com.sport.support.branch.application.port.in.command.UpdateBranchCommand;
import com.sport.support.branch.application.port.in.usecase.AddBranchUC;
import com.sport.support.branch.application.port.in.usecase.DeleteBranchUC;
import com.sport.support.branch.application.port.in.usecase.FindBranchUC;
import com.sport.support.branch.application.port.in.usecase.UpdateBranchUC;
import com.sport.support.shared.abstractions.adapters.web.AbstractController;
import com.sport.support.shared.common.web.DataResponse;
import com.sport.support.shared.common.web.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/branches")
public class BranchController extends AbstractController {

   private final AddBranchUC addBranchUC;
   private final FindBranchUC findBranchUC;
   private final UpdateBranchUC updateBranchUC;
   private final DeleteBranchUC deleteBranchUC;

   // TODO: 28.03.2022 managers can update only their branches but owner can update all branches

   @GetMapping
   @PreAuthorize("hasAuthority('BRANCH_READ')")
   @ResponseStatus(HttpStatus.OK)
   public Response<DataResponse<BranchResponse>> getAll(
       @Valid @RequestParam(defaultValue = "5") @Positive int limit,
       @Valid @RequestParam(defaultValue = "0") @Min(0) int pageNumber) {

      PageRequest pageRequest = PageRequest.of(pageNumber, limit);
      var responseList = findBranchUC.findAll(new FindBranchQuery(pageRequest)).stream()
          .map(BranchResponse::new).collect(Collectors.toList());
      return respond(responseList, 0, limit, responseList.size());
   }

   @GetMapping(value = "/{id}")
   @PreAuthorize("hasAuthority('BRANCH_READ')")
   @ResponseStatus(HttpStatus.OK)
   public Response<BranchResponse> get(@PathVariable @Positive Long id) {
      return respond(new BranchResponse(findBranchUC.findById(new FindBranchQuery(id))));
   }

   @PostMapping
   @PreAuthorize("hasAuthority('BRANCH_CREATE')")
   @ResponseStatus(HttpStatus.CREATED)
   public Response<BranchResponse> add(@RequestBody @Valid AddBranchRequest addBranchRequest) {
      var branch = addBranchUC.add(new AddBranchCommand(addBranchRequest));
      return respond(new BranchResponse(branch));
   }

   @PutMapping
   @PreAuthorize("hasAuthority('BRANCH_UPDATE')")
   @ResponseStatus(HttpStatus.OK)
   public Response<Long> update(@RequestBody @Valid UpdateBranchRequest updateBranchRequest) {
      updateBranchUC.update(new UpdateBranchCommand(updateBranchRequest));
      return respond(updateBranchRequest.getId());
   }

   @DeleteMapping(value = "/{id}")
   @PreAuthorize("hasAuthority('BRANCH_UPDATE')")
   @ResponseStatus(HttpStatus.OK)
   public Response<Long> delete(@PathVariable @Positive Long id) {
      deleteBranchUC.delete(id);
      return respond(id);
   }
}
