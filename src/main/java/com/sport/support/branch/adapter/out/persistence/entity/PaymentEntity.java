package com.sport.support.branch.adapter.out.persistence.entity;

import com.sport.support.branch.domain.Payment;
import com.sport.support.branch.domain.vo.BranchId;
import com.sport.support.branch.domain.vo.PaymentId;
import com.sport.support.shared.abstractions.entity.AbstractAuditableEntity;
import com.sport.support.shared.common.money.Money;
import com.sport.support.membership.domain.enumeration.Duration;
import com.sport.support.membership.domain.enumeration.Type;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.hibernate.Hibernate;

import javax.persistence.*;

@Entity
@Table(name = "PAYMENT")
@NoArgsConstructor
public class PaymentEntity extends AbstractAuditableEntity {

   @ManyToOne
   private BranchEntity branch;

   @Embedded
   @AttributeOverrides(value = {
       @AttributeOverride( name = "amount", column = @Column(name = "COST_AMOUNT")),
   })
   private Money cost;

   @Enumerated(EnumType.STRING)
   private Duration duration;

   @Enumerated(EnumType.STRING)
   private Type type;

   public PaymentEntity(Payment payment, BranchEntity branchEntity) {
      setId(payment.getId());
      setBranch(branchEntity);
      setCost(payment.getCost());
      setDuration(payment.getDuration());
      setType(payment.getType());
   }

   public Payment toDomain() {
      return Payment.builder()
          .idVO(new PaymentId(getId()))
          .branchId(new BranchId(getBranch().getId()))
          .cost(getCost())
          .duration(getDuration())
          .type(getType())
          .build();
   }

   public void update(PaymentEntity paymentEntity) {
      setId(paymentEntity.getId());
      copyFrom(paymentEntity);
   }

   public BranchEntity getBranch() {
      return branch;
   }

   public void setBranch(BranchEntity branchEntity) {
      this.branch = branchEntity;
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

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
      PaymentEntity that = (PaymentEntity) o;
      return new EqualsBuilder()
          .append(getType(), that.getType())
          .append(getDuration(), that.getDuration())
          .append(branch.getId(), that.branch.getId())
          .isEquals();
   }

   @Override
   public int hashCode() {
      return getClass().hashCode();
   }
}
