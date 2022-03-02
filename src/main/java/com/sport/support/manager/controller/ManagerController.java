package com.sport.support.manager.controller;

import com.sport.support.manager.controller.dto.AddManagerRequest;
import com.sport.support.manager.controller.dto.ManagerDetailResponse;
import com.sport.support.manager.controller.dto.UpdateManagerRequest;
import com.sport.support.manager.entity.Manager;
import com.sport.support.manager.service.ManagerService;
import lombok.RequiredArgsConstructor;
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
@RequestMapping(value = "/managers")
public class ManagerController {

    private final ManagerService managerService;

    @GetMapping()
    @PreAuthorize("hasAuthority('MANAGER_READ')")
    public ResponseEntity<List<ManagerDetailResponse>> getAll() {
        List<ManagerDetailResponse> detailDTOList = managerService.retrieveAll().stream()
                .map(ManagerDetailResponse::new).collect(Collectors.toList());

        return ResponseEntity.ok(detailDTOList);
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('MANAGER_READ')")
    public ResponseEntity<ManagerDetailResponse> get(@PathVariable @Min(1) Long id) {
        return ResponseEntity.ok(new ManagerDetailResponse(managerService.retrieveById(id)));
    }

    @PostMapping()
    @PreAuthorize("hasAuthority('MANAGER_WRITE')")
    public ResponseEntity<String> add(@RequestBody @Valid AddManagerRequest addManagerRequest) {
        Manager manager = new Manager(addManagerRequest);
        managerService.add(manager);
        return new ResponseEntity<>("Manager with ID = " + manager.getId() + " added!", HttpStatus.CREATED);
    }

    @PutMapping()
    @PreAuthorize("hasAuthority('MANAGER_WRITE')")
    public ResponseEntity<String> update(@RequestBody @Valid UpdateManagerRequest updateManagerRequest) {
        managerService.update(new Manager(updateManagerRequest));
        return new ResponseEntity<>(String.format("Manager with ID = %d updated!", updateManagerRequest.getId()),
                HttpStatus.ACCEPTED);
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('MANAGER_WRITE')")
    public ResponseEntity<String> delete(@PathVariable @Min(1) Long id) {
        managerService.delete(id);
        return new ResponseEntity<>("Manager with ID = " + id + " deleted!", HttpStatus.ACCEPTED);
    }
}
