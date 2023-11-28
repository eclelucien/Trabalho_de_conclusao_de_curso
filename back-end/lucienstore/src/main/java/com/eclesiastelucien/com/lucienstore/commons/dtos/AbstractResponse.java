package com.eclesiastelucien.com.lucienstore.commons.dtos;

import java.io.Serializable;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public abstract class AbstractResponse implements Serializable {
    private @NotNull Long id;

    // protected LocalDateTime createdAt;

    // protected LocalDateTime updatedAt;
}
