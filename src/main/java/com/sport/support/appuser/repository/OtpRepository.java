package com.sport.support.appuser.repository;

import com.sport.support.appuser.entity.Otp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface OtpRepository extends JpaRepository<Otp, Long> {

    Optional<Otp> findByUsernameAndExpiryDateAfter(String username, LocalDateTime expiryDate);

}
