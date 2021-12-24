package com.mixmaru.my_contracts_j.infra.user.mapper;

import com.mixmaru.my_contracts_j.domain.entity.CorporationUserEntity;
import org.modelmapper.ModelMapper;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@PrimaryKeyJoinColumn(name = "userId")
@Table(name = "users_corporation")
public class CorporationUserMapper extends UserMapper {

    public static CorporationUserMapper of(CorporationUserEntity entity) {
        return new ModelMapper().map(entity, CorporationUserMapper.class);
    }

    private String contactPersonName;
    public String getContactPersonName() {
        return contactPersonName;
    }
    public void setContactPersonName(String contactPersonName) {
        this.contactPersonName = contactPersonName;
    }

    private String presidentName;
    public String getPresidentName() {
        return presidentName;
    }
    public void setPresidentName(String presidentName) {
        this.presidentName = presidentName;
    }

    private String corporationName;
    public String getCorporationName() {
        return corporationName;
    }
    public void setCorporationName(String corporationName) {
        this.corporationName = corporationName;
    }

    /**
     * mapperからentityを生成する
     * @return entity
     */
    public CorporationUserEntity generateEntity() {
        return new ModelMapper().map(this, CorporationUserEntity.class);
    }
}
