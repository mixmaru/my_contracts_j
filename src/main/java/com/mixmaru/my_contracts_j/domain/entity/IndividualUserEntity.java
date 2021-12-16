package com.mixmaru.my_contracts_j.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.ZonedDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
public class IndividualUserEntity extends UserEntity {
    private String name;

    public IndividualUserEntity(String name, ZonedDateTime createdAt) {
        super(createdAt);
        this.name = name;
    }
}
