package com.sport.support.manager.controller.dto;

import com.sport.support.branch.controller.dto.BranchDetailDTO;
import com.sport.support.manager.entity.Manager;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ManagerDetailDTO {

    private String name;
    private String surname;
    private String eMail;
    private String phoneNumber;
    private BranchDetailDTO branchDetail;

    public ManagerDetailDTO(Manager manager) {
        this.name = manager.getName();
        this.surname = manager.getSurname();
        this.eMail = manager.getEMail();
        this.phoneNumber = manager.getPhoneNumber();
        this.branchDetail = new BranchDetailDTO(manager.getBranch());
    }
}
