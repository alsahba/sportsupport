package com.sport.support.manager.controller.dto;

import com.sport.support.branch.controller.dto.BranchDetailResponse;
import com.sport.support.manager.entity.Manager;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ManagerDetailResponse {

    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private BranchDetailResponse branchDetail;

    public ManagerDetailResponse(Manager manager) {
        this.name = manager.getAppUser().getName();
        this.surname = manager.getAppUser().getSurname();
        this.email = manager.getAppUser().getEmail();
        this.phoneNumber = manager.getAppUser().getPhoneNumber();
        this.branchDetail = new BranchDetailResponse(manager.getBranch());
    }
}
