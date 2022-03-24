package com.sport.support.employee.adapter.out.persistence;

import com.sport.support.appuser.adapter.out.persistence.entity.AppUser;
import com.sport.support.employee.adapter.in.web.payload.AddEmployeeRequest;
import com.sport.support.infrastructure.abstractions.entity.AbstractAuditableEntity;
import com.sport.support.infrastructure.common.Money;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Employee extends AbstractAuditableEntity {

   @OneToOne
   @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
   private AppUser user;

   @Enumerated(EnumType.STRING)
   private EmployeeType type;

   private LocalDateTime startDate;

   @Embedded
   @AttributeOverrides(value = {
       @AttributeOverride( name = "amount", column = @Column(name = "BASE_SALARY_AMOUNT")),
   })
   private Money baseSalary;

   @Embedded
   @AttributeOverrides(value = {
       @AttributeOverride( name = "amount", column = @Column(name = "BONUS_AMOUNT")),
   })
   private Money bonus;

   public Employee(AddEmployeeRequest request) {
      setUser(AppUser.builder().username(request.getUsername()).build());
      setType(request.getType());
      setStartDate(LocalDateTime.now());
      setBaseSalary(new Money(request.getBaseSalary()));
      setStartDate(request.getStartDate());
      setBonus(new Money(request.getBonus()));
   }
}
