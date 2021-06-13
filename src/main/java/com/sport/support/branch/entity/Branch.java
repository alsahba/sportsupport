package com.sport.support.branch.entity;

import com.sport.support.branch.controller.dto.AddBranchDTO;
import com.sport.support.branch.controller.dto.UpdateBranchDTO;
import com.sport.support.infrastructure.entity.City;
import com.sport.support.infrastructure.entity.District;
import com.sport.support.infrastructure.abstractions.entity.AbstractAuditableEntity;
import com.sport.support.manager.entity.Manager;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

// TODO: 5/16/2021 fetch types are going to be lazy
// TODO: 5/16/2021 annotation for phone number is necessary

@Entity
@Table(name = "BRANCH")
@Data
@NoArgsConstructor
public class Branch extends AbstractAuditableEntity {

    @Column(name = "NAME")
    private String name;

    @Column(name = "QUOTA")
    private int quota;

    @OneToOne()
    @JoinColumn(name = "CITY_ID", referencedColumnName = "ID")
    private City city;

    @OneToOne()
    @JoinColumn(name = "DISTRICT_ID", referencedColumnName = "ID")
    private District district;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @OneToMany(mappedBy = "branch")
    private Set<Manager> managerList;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PAYMENT_ID", referencedColumnName = "ID")
    private Payment payment;

    public void update(Branch branch) {
        this.name = branch.getName();
        this.address = branch.getAddress();
        this.payment = branch.getPayment();
    }

    public Branch (AddBranchDTO addBranchDTO) {
        this.name = addBranchDTO.getName();
        this.quota = addBranchDTO.getQuota();
        this.address = addBranchDTO.getAddress();
        this.city = new City(addBranchDTO.getCityId());
        this.district = new District(addBranchDTO.getDistrictId());
        this.payment = new Payment(addBranchDTO.getPayment());
    }

    public Branch (UpdateBranchDTO updateBranchDTO) {
        super.setId(updateBranchDTO.getId());
        this.name = updateBranchDTO.getName();
        this.quota = updateBranchDTO.getQuota();
        this.address = updateBranchDTO.getAddress();
        this.payment = new Payment(updateBranchDTO.getPayment());
    }

    public Branch(Long id) {
        super.setId(id);
    }
}
