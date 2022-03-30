package com.sport.support.appuser.adapter.out.persistence.entity;

import com.sport.support.appuser.application.port.in.command.RegisterUserCommand;
import com.sport.support.infrastructure.abstractions.entity.AbstractAuditableEntity;
import com.sport.support.wallet.adapter.out.persistence.entity.WalletEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppUser extends AbstractAuditableEntity {

   private String name;

   private String surname;

   @Column(unique = true)
   private String username;

   private String password;

   @Column(unique = true)
   private String phoneNumber;

   @Column(unique = true)
   private String email;

   @ManyToOne
   private Role role;

   @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
   private WalletEntity walletEntity;

   public AppUser(Long id) {
      setId(id);
   }

   public AppUser(RegisterUserCommand command) {
      setName(command.getName());
      setSurname(command.getSurname());
      setEmail(command.getEmail());
      setUsername(command.getUsername());
      setPassword(command.getPassword());
      setPhoneNumber(command.getPhoneNumber());
   }

   public void update(String name, String surname) {
      setName(name);
      setSurname(surname);
   }
}
