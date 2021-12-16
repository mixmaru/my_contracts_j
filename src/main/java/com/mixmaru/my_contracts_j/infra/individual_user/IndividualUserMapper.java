package com.mixmaru.my_contracts_j.infra.individual_user;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "users_individual")
public class IndividualUserMapper {
    @Id
    private Long userId;
    private String name;

    public IndividualUserMapper() {}

    public IndividualUserMapper(Long userId, String name) {
        this.userId = userId;
        this.name = name;
    }
}
