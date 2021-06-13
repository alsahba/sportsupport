package com.sport.support.manager.controller;

import com.sport.support.manager.controller.dto.AddManagerDTO;
import com.sport.support.manager.controller.dto.ManagerDetailDTO;
import com.sport.support.manager.controller.dto.UpdateManagerDTO;
import com.sport.support.manager.entity.Manager;
import com.sport.support.manager.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/managers")
public class ManagerController {

    private final ManagerService managerService;

    @GetMapping()
    public ResponseEntity<List<ManagerDetailDTO>> getAll() {
        List<ManagerDetailDTO> detailDTOList = managerService.retrieveAll().stream()
                .map(ManagerDetailDTO::new).collect(Collectors.toList());

        return ResponseEntity.ok(detailDTOList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ManagerDetailDTO> get(@PathVariable @Min(1) Long id) {
        return ResponseEntity.ok(new ManagerDetailDTO(managerService.retrieveById(id)));
    }

    @PostMapping()
    public ResponseEntity<String> add(@RequestBody @Valid AddManagerDTO addManagerDTO) {
        Long id = managerService.add(new Manager(addManagerDTO));
        return new ResponseEntity<>("Manager with ID = " + id + " added!",
                HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<String> update(@RequestBody @Valid UpdateManagerDTO updateManagerDTO) {
        managerService.update(new Manager(updateManagerDTO));
        return new ResponseEntity<>("Manager with ID = " + updateManagerDTO.getId() + " updated!",
                HttpStatus.ACCEPTED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable @Min(1) Long id) {
        managerService.delete(id);
        return new ResponseEntity<>("Manager with ID = " + id + " deleted!", HttpStatus.ACCEPTED);
    }
}
