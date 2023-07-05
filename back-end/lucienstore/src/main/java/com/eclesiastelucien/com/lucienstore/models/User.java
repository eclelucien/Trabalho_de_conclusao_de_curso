package com.eclesiastelucien.com.lucienstore.models;

import jakarta.persistence.Column;
import java.util.ArrayList;
import org.springframework.security.core.userdetails.UserDetails;
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
public abstract class User extends AbstractEntity implements UserDetails {

    @NotNull
    @Column(nullable = false)
    private String name;

    @NotNull
    @Email
    @Column(unique = true, nullable = false)
    private String email;

    private String phoneNumber;

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
    @OneToMany(mappedBy = "user")
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
}
