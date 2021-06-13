package com.sport.support.manager.repository;

import com.sport.support.manager.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ManagerRepository extends JpaRepository<Manager, Long> {

    List<Manager> findAllByBranchId(Long branchId);
}
