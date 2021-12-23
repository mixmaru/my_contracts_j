package com.mixmaru.my_contracts_j.domain.repository;

import com.mixmaru.my_contracts_j.domain.entity.CorporationUserEntity;
import com.mixmaru.my_contracts_j.infra.user.CorporationUserInfraRepository;
import com.mixmaru.my_contracts_j.infra.user.mapper.CorporationUserMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public class CorporationUserRepository {

    private final CorporationUserInfraRepository repository;

    private final ModelMapper modelMapper;

    public CorporationUserRepository(
            CorporationUserInfraRepository repository,
            ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public CorporationUserEntity save(CorporationUserEntity entity) {
        var mapper = modelMapper.map(entity, CorporationUserMapper.class);
        var savedMapper = repository.save(mapper);
        return savedMapper.generateEntity();
    }

//    public Optional<IndividualUserEntity> getById(Long id) {
//        var loadedUser = individualUserInnerRepository.findById(id);
//
//        // 返却用entityに組み立てる
//        return loadedUser.map(IndividualUserMapper::generateEntity);
//    }
}
