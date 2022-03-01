package com.sport.support.manager.controller.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class UpdateManagerRequest {

    @NotNull
    private Long id;

    @NotNull
    private Long branchId;

}
