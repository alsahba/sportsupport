package com.sport.support.branch.entity;

import com.sport.support.branch.controller.dto.AddUpdatePaymentRequest;
import com.sport.support.infrastructure.abstractions.entity.AbstractAuditableEntity;
import com.sport.support.infrastructure.common.Money;
import com.sport.support.membership.entity.enumeration.Type;

import javax.persistence.*;

@Entity
@Table
public class Payment extends AbstractAuditableEntity {

   @ManyToOne
   @JoinColumn(name = "BRANCH_ID", referencedColumnName = "ID")
   private Branch branch;

   @Embedded
   @AttributeOverrides(value = {
       @AttributeOverride( name = "amount", column = @Column(name = "ONE_TIME_COST")),
   })
   private Money oneTime;

   @Embedded
   @AttributeOverrides(value = {
       @AttributeOverride( name = "amount", column = @Column(name = "MONTHLY_COST")),
   })
   private Money monthly;

   @Embedded
   @AttributeOverrides(value = {
       @AttributeOverride( name = "amount", column = @Column(name = "ANNUAL_COST")),
   })
   private Money annual;

   @Enumerated(EnumType.STRING)
   private Type type;

   public Payment(AddUpdatePaymentRequest addUpdatePaymentRequest, Branch branch) {
      setBranch(branch);
      setType(addUpdatePaymentRequest.getType());
      setOneTime(new Money(addUpdatePaymentRequest.getOneTimeAmount()));
      setMonthly(new Money(addUpdatePaymentRequest.getMonthlyAmount()));
      setAnnual(new Money(addUpdatePaymentRequest.getAnnualAmount()));
   }

   public Payment() {}

   public Branch getBranch() {
      return branch;
   }

   public void setBranch(Branch branch) {
      this.branch = branch;
   }

   public Money getOneTime() {
      return oneTime;
   }

   public void setOneTime(Money oneTime) {
      this.oneTime = oneTime;
   }

   public Money getMonthly() {
      return monthly;
   }

   public void setMonthly(Money monthly) {
      this.monthly = monthly;
   }

   public Money getAnnual() {
      return annual;
   }

   public void setAnnual(Money annual) {
      this.annual = annual;
   }

   public Type getType() {
      return type;
   }

   public void setType(Type type) {
      this.type = type;
   }
}
