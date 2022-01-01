package com.mixmaru.my_contracts_j.domain.entity;

import com.fasterxml.jackson.annotation.JsonGetter;
import lombok.ToString;

import java.time.ZonedDateTime;

@ToString(callSuper = true)
public class IndividualUserEntity extends UserEntity {

    public static IndividualUserEntity createNew(String name, ZonedDateTime createdAt) {
        var entity = new IndividualUserEntity();
        entity.setName(name);
        entity.setCreatedAt(createdAt);
        entity.setUpdatedAt(createdAt);
        return entity;
    }

    public static IndividualUserEntity crateFromData(long id, String name, ZonedDateTime createdAt, ZonedDateTime updatedAt) {
        var entity = new IndividualUserEntity();
        entity.setId(id);
        entity.setName(name);
        entity.setCreatedAt(createdAt);
        entity.setUpdatedAt(updatedAt);
        return entity;
    }

    ///// private field /////
    private String name;

    ///// constructor /////
    private IndividualUserEntity() {}

    ///// public method /////
    @JsonGetter("name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
