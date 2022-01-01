package com.mixmaru.my_contracts_j.infra.user.mapper;

import com.mixmaru.my_contracts_j.domain.entity.IndividualUserEntity;
import lombok.Data;
import org.modelmapper.ModelMapper;

import javax.persistence.*;

@Data
@Entity
@PrimaryKeyJoinColumn(name = "userId")
@Table(name = "users_individual")
public class IndividualUserMapper extends UserMapper {

    private String name;

    private static final ModelMapper modelMapper = new ModelMapper();

    public static IndividualUserMapper from(IndividualUserEntity entity) {
        return modelMapper.map(entity, IndividualUserMapper.class);
    }

    public IndividualUserMapper() {}

    /**
     * mapperからentityを生成する
     * @return entity
     */
    public IndividualUserEntity newIndividualUserEntity() {
        return IndividualUserEntity.crateFromData(this.getId(), this.name, this.getCreatedAt(), this.getUpdatedAt());
    }
}
