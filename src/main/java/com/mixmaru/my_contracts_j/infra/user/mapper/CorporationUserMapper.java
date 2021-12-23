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
        var retEntity = new CorporationUserEntity();
        retEntity.setId(this.getId());
        retEntity.setCorporationName(this.corporationName);
        retEntity.setPresidentName(this.presidentName);
        retEntity.setContactPersonName(this.contactPersonName);
        retEntity.setCreatedAt(this.getCreatedAt());
        retEntity.setUpdatedAt(this.getUpdatedAt());
        return retEntity;
    }
}
