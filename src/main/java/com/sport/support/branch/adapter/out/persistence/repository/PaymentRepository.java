package com.sport.support.branch.adapter.out.persistence.repository;

import com.sport.support.branch.adapter.out.persistence.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {
}
