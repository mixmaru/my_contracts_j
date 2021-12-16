package com.mixmaru.my_contracts_j.domain.repository;

import com.mixmaru.my_contracts_j.domain.entity.IndividualUserEntity;
import com.mixmaru.my_contracts_j.infra.individual_user.IndividualUserInfraRepository;
import com.mixmaru.my_contracts_j.infra.individual_user.IndividualUserMapper;
import com.mixmaru.my_contracts_j.infra.user.UserMapper;
import com.mixmaru.my_contracts_j.infra.user.UserInfraRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public class IndividualUserRepository {

    private final UserInfraRepository userRepository;
    private final IndividualUserInfraRepository individualUserInnerRepository;

    public IndividualUserRepository(
            UserInfraRepository userRepository,
            IndividualUserInfraRepository individualUserInnerRepository
    ) {
        this.userRepository = userRepository;
        this.individualUserInnerRepository = individualUserInnerRepository;
    }

    @Transactional
    public IndividualUserEntity save(IndividualUserEntity entity) {
        // User保存
        var userMapper = new UserMapper(entity.getCreatedAt(), entity.getUpdatedAt());
        var savedUser = userRepository.save(userMapper);

        // IndividualUser保存
        var individualUserMapper = new IndividualUserMapper(savedUser.getId(), entity.getName());
        var savedIndividualUser = individualUserInnerRepository.save(individualUserMapper);

        // 返却用entityに組み立てる
        entity.setId(savedUser.getId());
        entity.setName(savedIndividualUser.getName());
        entity.setCreatedAt(savedUser.getCreatedAt());
        entity.setUpdatedAt(savedUser.getUpdatedAt());
        return entity;
    }
}
