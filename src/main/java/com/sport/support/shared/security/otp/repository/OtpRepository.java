package com.sport.support.shared.security.otp.repository;

import com.sport.support.shared.security.otp.entity.Otp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface OtpRepository extends JpaRepository<Otp, Long> {

   Optional<Otp> findByUsernameAndExpiryDateAfter(String username, LocalDateTime expiryDate);

}
