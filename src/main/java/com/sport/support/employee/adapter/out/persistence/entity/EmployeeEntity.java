package com.sport.support.employee.adapter.out.persistence.entity;

import com.sport.support.appuser.adapter.out.persistence.entity.AppUserEntity;
import com.sport.support.appuser.domain.vo.UserId;
import com.sport.support.branch.adapter.out.persistence.entity.BranchEntity;
import com.sport.support.branch.domain.vo.BranchId;
import com.sport.support.employee.domain.enumeration.EmployeeType;
import com.sport.support.employee.domain.Employee;
import com.sport.support.employee.domain.vo.EmployeeId;
import com.sport.support.shared.abstractions.entity.AbstractAuditableEntity;
import com.sport.support.shared.common.money.Money;
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
   private AppUserEntity user;

   @ManyToOne
   private BranchEntity branch;

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
      setUser(new AppUserEntity(employee.getUserId().getId()));
      setBranch(new BranchEntity(employee.getBranchId().getId()));
      setType(employee.getType());
      setStartDate(employee.getStartDate());
      setBaseSalary(employee.getBaseSalary());
      setBonus(employee.getBonus());
   }

   public Employee toDomain() {
      return new Employee(
          new EmployeeId(getId()),
          new UserId(getUser().getId()),
          new BranchId(getBranch().getId()),
          getType(),
          getBaseSalary(),
          getBonus(),
          getStartDate()
      );
   }
}
