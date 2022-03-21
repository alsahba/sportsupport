package com.sport.support.branch.entity;

import com.sport.support.branch.controller.dto.AddBranchRequest;
import com.sport.support.branch.controller.dto.UpdateBranchRequest;
import com.sport.support.branch.messages.BranchErrorMessages;
import com.sport.support.infrastructure.abstractions.entity.AbstractAuditableEntity;
import com.sport.support.infrastructure.common.Money;
import com.sport.support.infrastructure.entity.City;
import com.sport.support.infrastructure.entity.District;
import com.sport.support.infrastructure.exception.BusinessRuleException;
import com.sport.support.membership.entity.enumeration.Duration;
import com.sport.support.membership.entity.enumeration.Type;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Branch extends AbstractAuditableEntity {

   private String name;

   private int quota;

   @OneToOne
   @JoinColumn(name = "CITY_ID", referencedColumnName = "ID")
   private City city;

   @OneToOne
   @JoinColumn(name = "DISTRICT_ID", referencedColumnName = "ID")
   private District district;

   private String address;

   private String phoneNumber;

   @OneToMany(mappedBy = "branch", cascade = CascadeType.ALL)
   private Set<Payment> payments;

   public Branch(AddBranchRequest addBranchRequest) {
      setName(addBranchRequest.getName());
      setQuota(addBranchRequest.getQuota());
      setAddress(addBranchRequest.getAddress());
      setCity(new City(addBranchRequest.getCityId()));
      setDistrict(new District(addBranchRequest.getDistrictId()));
      setPhoneNumber(addBranchRequest.getPhoneNumber());
      setPayments(addBranchRequest.getPayment().stream()
          .map(p -> new Payment(p, this))
          .collect(Collectors.toSet()));
   }

   public Branch(UpdateBranchRequest updateBranchRequest) {
      super.setId(updateBranchRequest.getId());
      setName(updateBranchRequest.getName());
      setQuota(updateBranchRequest.getQuota());
      setAddress(updateBranchRequest.getAddress());
      setPhoneNumber(updateBranchRequest.getPhoneNumber());
      setPayments(updateBranchRequest.getPayment().stream()
          .map(p -> new Payment(p, this))
          .collect(Collectors.toSet()));
   }

   public Branch(Long id) {
      super.setId(id);
   }

   public void update(Branch branch) {
      setName(branch.getName());
      setAddress(branch.getAddress());
      setPayments(branch.getPayments());
   }

   public Money getCost(Type type, Duration duration) {
      Payment payment = getPayments().stream()
          .filter(p -> p.getType().equals(type) && p.getDuration().equals(duration))
          .findFirst()
          .orElseThrow(() -> new BusinessRuleException(BranchErrorMessages.ERROR_BRANCH_PAYMENT_INFO_IS_NOT_FOUND));
      return payment.getCost();
   }

   public Branch() {
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
   }

   public City getCity() {
      return city;
   }

   public void setCity(City city) {
      this.city = city;
   }

   public District getDistrict() {
      return district;
   }

   public void setDistrict(District district) {
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

   public Set<Payment> getPayments() {
      return payments;
   }

   public void setPayments(Set<Payment> payments) {
      this.payments = payments;
   }
}
