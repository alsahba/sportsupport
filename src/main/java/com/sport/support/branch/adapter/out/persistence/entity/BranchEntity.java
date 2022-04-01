package com.sport.support.branch.adapter.out.persistence.entity;

import com.sport.support.branch.domain.Branch;
import com.sport.support.branch.domain.BranchErrorMessages;
import com.sport.support.shared.abstractions.entity.AbstractAuditableEntity;
import com.sport.support.shared.exception.BusinessRuleException;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "BRANCH")
public class BranchEntity extends AbstractAuditableEntity {

   private String name;

   private int quota;

   @OneToOne
   @JoinColumn(name = "CITY_ID", referencedColumnName = "ID")
   private CityEntity city;

   @OneToOne
   @JoinColumn(name = "DISTRICT_ID", referencedColumnName = "ID")
   private DistrictEntity district;

   private String address;

   private String phoneNumber;

   @OneToMany(mappedBy = "branch", cascade = CascadeType.ALL)
   private Set<PaymentEntity> payments;

   public BranchEntity(Long id) {
      super(id);
   }

   public BranchEntity(Branch branch) {
      setId(branch.getId());
      setName(branch.getName());
      setQuota(branch.getQuota());
      setCity(new CityEntity(branch.getCityId()));
      setDistrict(new DistrictEntity(branch.getDistrictId()));
      setAddress(branch.getAddress());
      setPhoneNumber(branch.getPhoneNumber());
      setPayments(branch.getPayments().stream()
          .map(payment -> new PaymentEntity(payment, this))
          .collect(Collectors.toSet()));
   }

   public Branch toDomain() {
      return Branch.builder()
          .id(getId())
          .name(getName())
          .quota(getQuota())
          .cityId(getCity().getId())
          .districtId(getDistrict().getId())
          .address(getAddress())
          .phoneNumber(getPhoneNumber())
          .payments(getPayments().stream().map(PaymentEntity::toDomain).collect(Collectors.toSet()))
          .build();
   }

   public void update(BranchEntity branchEntity) {
      copyFrom(branchEntity);
      setCity(branchEntity.getCity());
      setDistrict(branchEntity.getDistrict());
      getPayments().forEach(payment -> branchEntity.getPayments().stream()
          .filter(p -> p.equals(payment)).findFirst().ifPresent(p -> payment.setId(p.getId())));
   }

   public BranchEntity() {
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public int getQuota() {
      return quota;
   }

   public void setQuota(int quota) {
      this.quota = quota;
      if (quota < 0) {
         throw new BusinessRuleException(BranchErrorMessages.ERROR_BRANCH_QUOTA_IS_EMPTY);
      }
   }

   public CityEntity getCity() {
      return city;
   }

   public void setCity(CityEntity city) {
      this.city = city;
   }

   public DistrictEntity getDistrict() {
      return district;
   }

   public void setDistrict(DistrictEntity district) {
      this.district = district;
   }

   public String getAddress() {
      return address;
   }

   public void setAddress(String address) {
      this.address = address;
   }

   public String getPhoneNumber() {
      return phoneNumber;
   }

   public void setPhoneNumber(String phoneNumber) {
      this.phoneNumber = phoneNumber;
   }

   public Set<PaymentEntity> getPayments() {
      return payments;
   }

   public void setPayments(Set<PaymentEntity> payments) {
      this.payments = payments;
   }
}
