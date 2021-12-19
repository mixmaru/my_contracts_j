package com.mixmaru.my_contracts_j.infra.user.mapper;

import com.mixmaru.my_contracts_j.domain.entity.IndividualUserEntity;
import lombok.Data;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Data
@Entity
@PrimaryKeyJoinColumn(name = "userId")
@Table(name = "users_individual")
public class IndividualUserMapper extends UserMapper {
    private String name;

    public IndividualUserMapper() {}

    public IndividualUserMapper(String name, ZonedDateTime createdAt, ZonedDateTime updatedAt) {
        super(createdAt, updatedAt);
        this.name = name;
    }

    /**
     * mapperからentityを生成する
     * @return entity
     */
    public IndividualUserEntity generateEntity() {
        return new IndividualUserEntity(this.getId(), this.name, this.getCreatedAt(), this.getUpdatedAt());
    }
}
