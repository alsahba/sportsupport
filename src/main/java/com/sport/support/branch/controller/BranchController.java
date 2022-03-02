package com.sport.support.branch.controller;

import com.sport.support.branch.controller.dto.AddBranchRequest;
import com.sport.support.branch.controller.dto.BranchDetailResponse;
import com.sport.support.branch.controller.dto.UpdateBranchRequest;
import com.sport.support.branch.entity.Branch;
import com.sport.support.branch.service.BranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.stream.Collectors;

// TODO: 5/15/2021 pagination

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/branches")
public class BranchController {

    private final BranchService branchService;

    @GetMapping
    @PreAuthorize("hasAuthority('BRANCH_READ')")
    public ResponseEntity<List<BranchDetailResponse>> getAll() {
        List<BranchDetailResponse> detailDTOList = branchService.retrieveAll().stream()
                .map(BranchDetailResponse::new).collect(Collectors.toList());

        return ResponseEntity.ok(detailDTOList);
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('BRANCH_READ')")
    public ResponseEntity<BranchDetailResponse> get(@PathVariable @Min(1) Long id) {
        return ResponseEntity.ok(new BranchDetailResponse(branchService.retrieveById(id)));
    }

    @PostMapping()
    @PreAuthorize("hasAuthority('BRANCH_WRITE')")
    public ResponseEntity<String> add(@RequestBody @Valid AddBranchRequest addBranchRequest) {
        Long id = branchService.add(new Branch(addBranchRequest));
        return new ResponseEntity<>(String.format("Branch with ID = %d added!", id), HttpStatus.CREATED);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('BRANCH_WRITE')")
    public ResponseEntity<String> update(@RequestBody @Valid UpdateBranchRequest updateBranchRequest) {
        branchService.update(new Branch(updateBranchRequest));
        return new ResponseEntity<>(String.format("Branch with ID = %d added!", updateBranchRequest.getId()),
                HttpStatus.ACCEPTED);
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('BRANCH_WRITE')")
    public ResponseEntity<String> delete(@PathVariable @Min(1) Long id) {
        branchService.delete(id);
        return new ResponseEntity<>(String.format("Branch with ID = %d deleted!", id), HttpStatus.ACCEPTED);
    }
}
