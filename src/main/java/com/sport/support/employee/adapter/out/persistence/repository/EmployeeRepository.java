package com.sport.support.employee.adapter.out.persistence.repository;

import com.sport.support.employee.adapter.out.persistence.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
