package com.sport.support.branch.controller;

import com.sport.support.branch.controller.dto.AddBranchDTO;
import com.sport.support.branch.controller.dto.BranchDetailDTO;
import com.sport.support.branch.controller.dto.UpdateBranchDTO;
import com.sport.support.branch.entity.Branch;
import com.sport.support.branch.service.BranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.stream.Collectors;

// TODO: 5/15/2021 pagination

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/branches")
public class BranchController {

    private final BranchService branchService;

    @GetMapping()
    public ResponseEntity<List<BranchDetailDTO>> getAll() {
        List<BranchDetailDTO> detailDTOList = branchService.retrieveAll().stream()
                .map(BranchDetailDTO::new).collect(Collectors.toList());

        return ResponseEntity.ok(detailDTOList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<BranchDetailDTO> get(@PathVariable @Min(1) Long id) {
        return ResponseEntity.ok(new BranchDetailDTO(branchService.retrieveById(id)));
    }

    @PostMapping()
    public ResponseEntity<String> add(@RequestBody @Valid AddBranchDTO addBranchDTO) {
        Long id = branchService.add(new Branch(addBranchDTO));
        return new ResponseEntity<>("Branch with ID = " + id + " added!",
                HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<String> update(@RequestBody @Valid UpdateBranchDTO updateBranchDTO) {
        branchService.update(new Branch(updateBranchDTO));
        return new ResponseEntity<>("Branch with ID = " + updateBranchDTO.getId() + " updated!",
                HttpStatus.ACCEPTED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable @Min(1) Long id) {
        branchService.delete(id);
        return new ResponseEntity<>("Branch with ID = " + id + " deleted!", HttpStatus.ACCEPTED);
    }
}
