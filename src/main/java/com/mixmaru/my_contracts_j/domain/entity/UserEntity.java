package com.mixmaru.my_contracts_j.domain.entity;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class UserEntity {
    private Long id;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

    public UserEntity() {}

    public UserEntity(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
        this.updatedAt = createdAt;
    }
}
