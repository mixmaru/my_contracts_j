package com.mixmaru.my_contracts_j.domain.entity;

import com.fasterxml.jackson.annotation.JsonGetter;
import lombok.ToString;

import java.time.ZonedDateTime;

@ToString(callSuper = true)
public class CorporationUserEntity extends UserEntity {

    public static CorporationUserEntity createNew(
            String corporationName,
            String presidentName,
            String contactPersonName,
            ZonedDateTime createdAt
    ){
        var entity = new CorporationUserEntity();
        entity.setCorporationName(corporationName);
        entity.setPresidentName(presidentName);
        entity.setContactPersonName(contactPersonName);
        entity.setCreatedAt(createdAt);
        entity.setUpdatedAt(createdAt);
        return entity;
    }

    public static CorporationUserEntity createByData(
            long id,
            String corporationName,
            String presidentName,
            String contactPersonName,
            ZonedDateTime createdAt
    ) {
        var entity = new CorporationUserEntity();
        entity.setId(id);
        entity.setCorporationName(corporationName);
        entity.setPresidentName(presidentName);
        entity.setContactPersonName(contactPersonName);
        entity.setCreatedAt(createdAt);
        entity.setUpdatedAt(createdAt);
        return entity;
    }

    private String contactPersonName;
    private String presidentName;
    private String corporationName;

    private CorporationUserEntity() {}

    @JsonGetter("contact_person_name")
    public String getContactPersonName() {
        return contactPersonName;
    }

    public void setContactPersonName(String contactPersonName) {
        this.contactPersonName = contactPersonName;
    }

    @JsonGetter("president_name")
    public String getPresidentName() {
        return presidentName;
    }

    public void setPresidentName(String presidentName) {
        this.presidentName = presidentName;
    }

    @JsonGetter("corporation_name")
    public String getCorporationName() {
        return corporationName;
    }

    public void setCorporationName(String corporationName) {
        this.corporationName = corporationName;
    }
}
