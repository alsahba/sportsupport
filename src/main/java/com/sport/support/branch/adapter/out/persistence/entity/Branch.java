package com.sport.support.branch.adapter.out.persistence.entity;

import com.sport.support.branch.application.port.in.command.AddBranchCommand;
import com.sport.support.branch.application.port.in.command.UpdateBranchCommand;
import com.sport.support.branch.domain.BranchErrorMessages;
import com.sport.support.infrastructure.abstractions.entity.AbstractAuditableEntity;
import com.sport.support.infrastructure.common.money.Money;
import com.sport.support.infrastructure.exception.BusinessRuleException;
import com.sport.support.membership.adapter.out.persistence.enumeration.Duration;
import com.sport.support.membership.adapter.out.persistence.enumeration.Type;

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

   public Branch(AddBranchCommand command) {
      setName(command.getName());
      setQuota(command.getQuota());
      setAddress(command.getAddress());
      setCity(new City(command.getCityId()));
      setDistrict(new District(command.getDistrictId()));
      setPhoneNumber(command.getPhoneNumber());
      setPayments(command.getPayments().stream().map(p -> new Payment(p, this)).collect(Collectors.toSet()));
   }

   public Branch(Long id) {
      super(id);
   }

   public void update(UpdateBranchCommand command) {
      setName(command.getName());
      setQuota(command.getQuota());
      setAddress(command.getAddress());
      setPhoneNumber(command.getPhoneNumber());
      setPayments(command.getPayments().stream().map(p -> new Payment(p, this)).collect(Collectors.toSet()));
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
      if (quota < 0) {
         throw new BusinessRuleException(BranchErrorMessages.ERROR_BRANCH_QUOTA_IS_EMPTY);
      }
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
