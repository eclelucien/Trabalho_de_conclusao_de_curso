package com.eclesiastelucien.com.lucienstore.modules.address.modules;

import com.eclesiastelucien.com.lucienstore.commons.models.AbstractEntity;
import com.eclesiastelucien.com.lucienstore.modules.address.enums.AddressTypeEnum;
import com.eclesiastelucien.com.lucienstore.modules.user.models.User;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "addresses")
public class Address extends AbstractEntity {

    @NonNull
    private String countryCode;
    @NonNull
    private String countryName;
    @NonNull
    private String stateOrDepartment;
    @NonNull
    private String city;
    @NonNull
    private String neighborhood;
    @NonNull
    private String street;
    @NonNull
    private int number;
    private boolean isDefault;

    @NonNull
    @Enumerated(EnumType.STRING)
    private AddressTypeEnum type;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
