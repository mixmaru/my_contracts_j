package com.mixmaru.my_contracts_j.infra.user.mapper;

import lombok.Data;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Data
@Entity
@PrimaryKeyJoinColumn(name = "userId")
@Table(name = "users_individual")
public class IndividualUserMapper extends UserMapper {
//    @Id
//    private Long userId;
    private String name;

    public IndividualUserMapper() {}

    public IndividualUserMapper(String name, ZonedDateTime createdAt, ZonedDateTime updatedAt) {
        super(createdAt, updatedAt);
        this.name = name;
    }
}
