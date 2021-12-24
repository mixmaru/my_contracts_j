package com.mixmaru.my_contracts_j.infra.user.mapper;

import com.mixmaru.my_contracts_j.domain.entity.IndividualUserEntity;
import lombok.Data;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Data
@Entity
@PrimaryKeyJoinColumn(name = "userId")
@Table(name = "users_individual")
public class IndividualUserMapper extends UserMapper {

    private static ModelMapper modelMapper = new ModelMapper();

    public static IndividualUserMapper of(IndividualUserEntity entity) {
        return modelMapper.map(entity, IndividualUserMapper.class);
    }

    private String name;

    public IndividualUserMapper() {}

    /**
     * mapperからentityを生成する
     * @return entity
     */
    public IndividualUserEntity generateEntity() {
        return new IndividualUserEntity(this.getId(), this.name, this.getCreatedAt(), this.getUpdatedAt());
    }
}
