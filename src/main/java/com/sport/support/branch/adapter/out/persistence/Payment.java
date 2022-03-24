package com.sport.support.branch.adapter.out.persistence;

import com.sport.support.branch.adapter.in.web.payload.AddUpdatePaymentRequest;
import com.sport.support.infrastructure.abstractions.entity.AbstractAuditableEntity;
import com.sport.support.infrastructure.common.Money;
import com.sport.support.membership.adapter.out.persistence.enumeration.Duration;
import com.sport.support.membership.adapter.out.persistence.enumeration.Type;

import javax.persistence.*;

@Entity
public class Payment extends AbstractAuditableEntity {

   @ManyToOne
   @JoinColumn(name = "BRANCH_ID", referencedColumnName = "ID")
   private Branch branch;

   @Embedded
   @AttributeOverrides(value = {
       @AttributeOverride( name = "amount", column = @Column(name = "COST_AMOUNT")),
   })
   private Money cost;

   @Enumerated(EnumType.STRING)
   private Duration duration;

   @Enumerated(EnumType.STRING)
   private Type type;

   public Payment(AddUpdatePaymentRequest addUpdatePaymentRequest, Branch branch) {
      setBranch(branch);
      setType(addUpdatePaymentRequest.getType());
      setDuration(addUpdatePaymentRequest.getDuration());
      setCost(Money.of(addUpdatePaymentRequest.getCost()));
   }

   public Payment() {}

   public Branch getBranch() {
      return branch;
   }

   public void setBranch(Branch branch) {
      this.branch = branch;
   }

   public Money getCost() {
      return cost;
   }

   public void setCost(Money cost) {
      this.cost = cost;
   }

   public Duration getDuration() {
      return duration;
   }

   public void setDuration(Duration duration) {
      this.duration = duration;
   }

   public Type getType() {
      return type;
   }

   public void setType(Type type) {
      this.type = type;
   }
}
