package com.eclesiastelucien.com.lucienstore.commons.models;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.eclesiastelucien.com.lucienstore.modules.user.models.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class AbstractEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @JsonIgnore
    @Column(nullable = false)
    protected LocalDateTime createdAt;

    @JsonIgnore
    @Column(nullable = false)
    protected LocalDateTime updatedAt;

    @JsonIgnore
    protected Long createdByUserId;

    @JsonIgnore
    protected Long updatedByUserId;

    @PrePersist
    protected void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.setCreatedByUserId();
    }

    @PreUpdate
    protected void preUpdate() {
        this.updatedAt = LocalDateTime.now();
        this.setUpdatedByUserId();
    }

    private void setCreatedByUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getPrincipal() == "anonymousUser") {
            this.createdByUserId = null;
        } else {
            User user = (User) authentication.getPrincipal();
            this.createdByUserId = user.getId();
        }
    }

    private void setUpdatedByUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getPrincipal() == "anonymousUser") {
            this.updatedByUserId = null;
        } else {
            User user = (User) authentication.getPrincipal();
            this.updatedByUserId = user.getId();
        }
    }
}
