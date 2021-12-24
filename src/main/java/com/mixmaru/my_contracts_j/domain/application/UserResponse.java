package com.mixmaru.my_contracts_j.domain.application;

import com.mixmaru.my_contracts_j.domain.entity.CorporationUserEntity;
import com.mixmaru.my_contracts_j.domain.entity.IndividualUserEntity;

import java.util.Optional;

public class UserResponse {

    private final IndividualUserEntity individualUserEntity;
    private final CorporationUserEntity corporationUserEntity;

    public UserResponse() {
        this.individualUserEntity = null;
        this.corporationUserEntity = null;
    }

    public UserResponse(IndividualUserEntity individualUserEntity) {
        this.individualUserEntity = individualUserEntity;
        this.corporationUserEntity = null;
    }

    public UserResponse(CorporationUserEntity corporationUserEntity) {
        this.corporationUserEntity = corporationUserEntity;
        this.individualUserEntity = null;
    }

    public UserResponse(
            IndividualUserEntity individualUserEntity,
            CorporationUserEntity corporationUserEntity) {
        this.individualUserEntity = individualUserEntity;
        this.corporationUserEntity = corporationUserEntity;
    }

    public IndividualUserEntity getIndividualUserEntity() {
        return individualUserEntity;
    }

    public CorporationUserEntity getCorporationUserEntity() {
        return corporationUserEntity;
    }
}
