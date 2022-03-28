package com.sport.support.employee.adapter.out.persistence.repository;

import com.sport.support.employee.adapter.out.persistence.entity.Employee;
import com.sport.support.employee.adapter.out.persistence.enumeration.EmployeeType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

   Optional<Employee> findByUserIdAndType(Long userId, EmployeeType type);

}
