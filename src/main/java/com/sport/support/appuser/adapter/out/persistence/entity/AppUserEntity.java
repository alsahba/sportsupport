package com.sport.support.appuser.adapter.out.persistence.entity;

import com.sport.support.appuser.domain.AppUser;
import com.sport.support.appuser.domain.vo.UserId;
import com.sport.support.shared.abstractions.entity.AbstractAuditableEntity;
import com.sport.support.wallet.adapter.out.persistence.entity.WalletEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "app_user")
@NoArgsConstructor
public class AppUserEntity extends AbstractAuditableEntity {

   private String name;

   private String surname;

   @Column(unique = true)
   private String username;

   private String password;

   @Column(unique = true)
   private String phone;

   @Column(unique = true)
   private String email;

   @ManyToOne
   private RoleEntity role;

   @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
   private WalletEntity wallet;

   public AppUserEntity(Long id) {
      setId(id);
   }

   public AppUserEntity(AppUser user) {
      setName(user.getName());
      setSurname(user.getSurname());
      setEmail(user.getEmail());
      setUsername(user.getUsername());
      setPassword(user.getPassword());
      setPhone(user.getPhone());
      setRole(new RoleEntity(user.getRole().getId()));
   }

   public void update(String name, String surname) {
      setName(name);
      setSurname(surname);
   }

   public AppUser toDomain() {
      return AppUser.builder()
          .idVO(new UserId(getId()))
          .name(getName())
          .surname(getSurname())
          .username(getUsername())
          .password(getPassword())
          .phone(getPhone())
          .email(getEmail())
          .role(getRole().toDomain())
          .build();
   }
}
