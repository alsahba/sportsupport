package com.sport.support.employee.adapter.out.persistence.entity;

import com.sport.support.appuser.adapter.out.persistence.entity.AppUser;
import com.sport.support.branch.adapter.out.persistence.entity.BranchEntity;
import com.sport.support.employee.domain.enumeration.EmployeeType;
import com.sport.support.employee.domain.Employee;
import com.sport.support.infrastructure.abstractions.entity.AbstractAuditableEntity;
import com.sport.support.infrastructure.common.money.Money;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "EMPLOYEE")
@Getter
@Setter
@NoArgsConstructor
public class EmployeeEntity extends AbstractAuditableEntity {

   @OneToOne
   @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
   private AppUser user;

   @ManyToOne
   @JoinColumn(name = "BRANCH_ID", referencedColumnName = "ID")
   private BranchEntity branchEntity;

   @Enumerated(EnumType.STRING)
   private EmployeeType type;

   private LocalDate startDate;

   @Embedded
   @AttributeOverrides(value = {
       @AttributeOverride(name = "amount", column = @Column(name = "BASE_SALARY_AMOUNT")),
   })
   private Money baseSalary;

   @Embedded
   @AttributeOverrides(value = {
       @AttributeOverride(name = "amount", column = @Column(name = "BONUS_AMOUNT")),
   })
   private Money bonus;

   public EmployeeEntity(Employee employee) {
      setUser(new AppUser(employee.getUserId()));
      setBranchEntity(new BranchEntity(employee.getBranchId()));
      setType(employee.getType());
      setStartDate(employee.getStartDate());
      setBaseSalary(employee.getBaseSalary());
      setBonus(employee.getBonus());
   }

   public Employee toDomain() {
      return new Employee(
          getId(),
          getUser().getId(),
          this.getBranchEntity().getId(),
          getType(),
          getBaseSalary(),
          getBonus(),
          getStartDate()
      );
   }
}
