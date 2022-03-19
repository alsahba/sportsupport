package com.sport.support.appuser.service;

import com.sport.support.appuser.entity.Otp;
import com.sport.support.appuser.repository.OtpRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class OtpService {

   private final OtpRepository otpRepository;

   public String save(String username) {
      String code = String.valueOf(new Random().nextInt(9999) + 1000);
      otpRepository.save(new Otp(code, username));
      return code;
   }

   public boolean validateOtp(String code, String username) {
      Otp otp = otpRepository.findByUsernameAndExpiryDateAfter(username, LocalDateTime.now())
          .orElseThrow(() -> new RuntimeException("OTP not found"));
      return otp.getCode().equals(code);
   }
}
