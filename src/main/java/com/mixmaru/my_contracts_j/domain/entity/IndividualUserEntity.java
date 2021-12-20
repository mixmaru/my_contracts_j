package com.mixmaru.my_contracts_j.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.ZonedDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
public class IndividualUserEntity extends UserEntity {
    private String name;

    public IndividualUserEntity() {
        super();
    }

    public IndividualUserEntity(String name, ZonedDateTime createdAt) {
        super(createdAt);
        this.name = name;
    }

    public IndividualUserEntity(Long id, String name, ZonedDateTime createdAt, ZonedDateTime updatedAt) {
        super(id, createdAt, updatedAt);
        this.name = name;
    }
}
