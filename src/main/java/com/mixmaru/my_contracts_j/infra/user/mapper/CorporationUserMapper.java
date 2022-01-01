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

    private String contactPersonName;
    private String presidentName;
    private String corporationName;

    public static CorporationUserMapper from(CorporationUserEntity entity) {
        return new ModelMapper().map(entity, CorporationUserMapper.class);
    }

    public String getContactPersonName() {
        return contactPersonName;
    }

    public void setContactPersonName(String contactPersonName) {
        this.contactPersonName = contactPersonName;
    }

    public String getPresidentName() {
        return presidentName;
    }

    public void setPresidentName(String presidentName) {
        this.presidentName = presidentName;
    }

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
    public CorporationUserEntity newCorporationUserEntity() {
        return new ModelMapper().map(this, CorporationUserEntity.class);
    }
}
