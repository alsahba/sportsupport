package com.sport.support.appuser.adapter.out.persistence.entity;

import com.sport.support.appuser.adapter.in.web.payload.AddUserRequest;
import com.sport.support.branch.adapter.out.persistence.Branch;
import com.sport.support.infrastructure.abstractions.entity.AbstractAuditableEntity;
import com.sport.support.wallet.adapter.out.persistence.entity.Wallet;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

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

   @ManyToMany(fetch = FetchType.EAGER)
   @JoinTable(
       name = "PERMISSION_APP_USER",
       joinColumns = @JoinColumn(name = "APP_USER_ID"),
       inverseJoinColumns = @JoinColumn(name = "PERMISSION_ID")
   )
   private Set<Permission> permissions = new HashSet<>();

   @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
   private Wallet wallet;

   @ManyToOne
   @JoinColumn(name = "BRANCH_ID", referencedColumnName = "ID")
   private Branch branch;

   public AppUser(AddUserRequest addUserRequest) {
      setName(addUserRequest.getName());
      setSurname(addUserRequest.getSurname());
      setEmail(addUserRequest.getEmail());
      setUsername(addUserRequest.getUsername());
      setPassword(addUserRequest.getPassword());
      setPhoneNumber(addUserRequest.getPhoneNumber());
   }

   public AppUser(Long id) {
      setId(id);
   }

   public void update(String name, String surname) {
      setName(name);
      setSurname(surname);
   }

   public Set<GrantedAuthority> getGrantedAuthorities() {
      return getPermissions()
          .stream()
          .map(p -> new SimpleGrantedAuthority(p.getName()))
          .collect(Collectors.toSet());
   }

}