package com.mixmaru.my_contracts_j.domain.repository;

import com.mixmaru.my_contracts_j.domain.entity.IndividualUserEntity;
import com.mixmaru.my_contracts_j.infra.user.IndividualUserInfraRepository;
import com.mixmaru.my_contracts_j.infra.user.mapper.IndividualUserMapper;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

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
        var individualUserMapper = IndividualUserMapper.of(entity);
        var savedIndividualUser = individualUserInnerRepository.save(individualUserMapper);

        // 返却用entityに組み立てる
        return savedIndividualUser.generateEntity();
    }

    public Optional<IndividualUserEntity> getById(Long id) {
        var loadedUser = individualUserInnerRepository.findById(id);

        // 返却用entityに組み立てる
        return loadedUser.map(IndividualUserMapper::generateEntity);
    }
}
