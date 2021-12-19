package com.mixmaru.my_contracts_j.domain.repository;

import com.mixmaru.my_contracts_j.domain.entity.IndividualUserEntity;
import com.mixmaru.my_contracts_j.infra.user.IndividualUserInfraRepository;
import com.mixmaru.my_contracts_j.infra.user.mapper.IndividualUserMapper;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public class IndividualUserRepository {

    private final IndividualUserInfraRepository individualUserInnerRepository;

    public IndividualUserRepository(
            IndividualUserInfraRepository individualUserInnerRepository
    ) {
        this.individualUserInnerRepository = individualUserInnerRepository;
    }

    @Transactional
    public IndividualUserEntity save(IndividualUserEntity entity) {
        // IndividualUser保存
        var individualUserMapper = new IndividualUserMapper(entity.getName(), entity.getCreatedAt(), entity.getUpdatedAt());
        var savedIndividualUser = individualUserInnerRepository.save(individualUserMapper);

        // 返却用entityに組み立てる
        entity.setId(savedIndividualUser.getId());
        entity.setName(savedIndividualUser.getName());
        entity.setCreatedAt(savedIndividualUser.getCreatedAt());
        entity.setUpdatedAt(savedIndividualUser.getUpdatedAt());
        return entity;
    }
}
