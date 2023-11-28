package com.eclesiastelucien.com.lucienstore.modules.user.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.eclesiastelucien.com.lucienstore.commons.models.AbstractEntity;
import com.eclesiastelucien.com.lucienstore.commons.models.Token;
import com.eclesiastelucien.com.lucienstore.modules.order.models.Order;
import com.eclesiastelucien.com.lucienstore.modules.user.enums.UserRoleEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Inheritance;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class User extends AbstractEntity implements UserDetails {

    @NotNull
    @Column(nullable = false)
    private String name;

    @NotNull
    @Email
    @Column(unique = true, nullable = false)
    private String email;

    private String phoneNumber;

    @JsonIgnore
    @OneToMany(mappedBy = "buyer", cascade = CascadeType.REMOVE)
    private List<Order> orders = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // In this example, we'll assign a simple role to all users. You can adjust this
        // as per your role system.
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @JsonIgnore
    @Column(nullable = false)
    private String password;

    @JsonIgnore
    private boolean isActive;

    @JsonIgnore
    private boolean isBlocked;

    @JsonIgnore
    private String socialProviderName;

    @JsonIgnore
    private Token token;

    @JsonIgnore
    private List<String> addresses = new ArrayList<>();

    @Column(columnDefinition = "BOOLEAN DEFAULT false")
    private Boolean termAndConditionAccepted = false;

    @Override
    public String getPassword() {
        return this.password;
    }

    @JsonIgnore
    @Override
    public String getUsername() {
        return this.email;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }

    public UserRoleEnum getRole() {
        if (this instanceof Administrator) {
            return UserRoleEnum.ADMINISTRATOR;
        }
        return UserRoleEnum.BUYER;
    }

}
