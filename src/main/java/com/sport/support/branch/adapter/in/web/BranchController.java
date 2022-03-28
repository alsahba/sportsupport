package com.sport.support.branch.adapter.in.web;

import com.sport.support.branch.adapter.in.web.payload.AddBranchRequest;
import com.sport.support.branch.adapter.in.web.payload.BranchDetailResponse;
import com.sport.support.branch.adapter.in.web.payload.UpdateBranchRequest;
import com.sport.support.branch.application.port.in.command.AddBranchCommand;
import com.sport.support.branch.application.port.in.command.FindBranchQuery;
import com.sport.support.branch.application.port.in.command.UpdateBranchCommand;
import com.sport.support.branch.application.port.in.usecase.AddBranchUC;
import com.sport.support.branch.application.port.in.usecase.DeleteBranchUC;
import com.sport.support.branch.application.port.in.usecase.FindBranchUC;
import com.sport.support.branch.application.port.in.usecase.UpdateBranchUC;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/branches")
public class BranchController {

   private final AddBranchUC addBranchUC;
   private final FindBranchUC findBranchUC;
   private final UpdateBranchUC updateBranchUC;
   private final DeleteBranchUC deleteBranchUC;

   // TODO: 28.03.2022 managers can update only their branches but owner can update all branches

   @GetMapping
   @PreAuthorize("hasAuthority('BRANCH_READ')")
   public ResponseEntity<List<BranchDetailResponse>> getAll(
       @Valid @RequestParam(defaultValue = "5") @Min(1) int limit,
       @Valid @RequestParam(defaultValue = "0") @Min(0) int pageNumber) {

      PageRequest pageRequest = PageRequest.of(pageNumber, limit);
      List<BranchDetailResponse> detailDTOList = findBranchUC.findAll(new FindBranchQuery(pageRequest)).stream()
          .map(BranchDetailResponse::new).collect(Collectors.toList());
      return ResponseEntity.ok(detailDTOList);
   }

   @GetMapping(value = "/{id}")
   @PreAuthorize("hasAuthority('BRANCH_READ')")
   public ResponseEntity<BranchDetailResponse> get(@PathVariable @Min(1) Long id) {
      return ResponseEntity.ok(new BranchDetailResponse(findBranchUC.findById(new FindBranchQuery(id))));
   }

   @PostMapping
   @PreAuthorize("hasAuthority('BRANCH_CREATE')")
   public ResponseEntity<String> add(@RequestBody @Valid AddBranchRequest addBranchRequest) {
      addBranchUC.add(new AddBranchCommand(addBranchRequest));
      return new ResponseEntity<>("Branch added!", HttpStatus.CREATED);
   }

   @PutMapping
   @PreAuthorize("hasAuthority('BRANCH_UPDATE')")
   public ResponseEntity<String> update(@RequestBody @Valid UpdateBranchRequest updateBranchRequest) {
      updateBranchUC.update(new UpdateBranchCommand(updateBranchRequest));
      return new ResponseEntity<>(String.format("Branch with ID = %d updated!", updateBranchRequest.getId()),
          HttpStatus.ACCEPTED);
   }

   @DeleteMapping(value = "/{id}")
   @PreAuthorize("hasAuthority('BRANCH_UPDATE')")
   public ResponseEntity<String> delete(@PathVariable @Min(1) Long id) {
      deleteBranchUC.delete(id);
      return new ResponseEntity<>(String.format("Branch with ID = %d deleted!", id), HttpStatus.ACCEPTED);
   }
}
