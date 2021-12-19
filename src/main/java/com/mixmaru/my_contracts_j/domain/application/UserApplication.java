package com.mixmaru.my_contracts_j.domain.application;


import com.mixmaru.my_contracts_j.domain.entity.IndividualUserEntity;
import com.mixmaru.my_contracts_j.domain.repository.IndividualUserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.Optional;

@Service
public class UserApplication {
    private final IndividualUserRepository individualUserRepository;

    public UserApplication(
            IndividualUserRepository individualUserRepository
    ) {
        this.individualUserRepository = individualUserRepository;
    }

    /**
     * 個人userを新規登録する
     * @param name user名
     * @param createdAt 作成日
     * @return 新規登録された個人userのEntity
     */
    @Transactional
    public IndividualUserEntity registerNewIndividualUser(String name, ZonedDateTime createdAt) {
        var newUser = new IndividualUserEntity(name, createdAt);
        return individualUserRepository.save(newUser);
    }

    /**
     * 個人userを取得する
     * @param id 個人userのid
     * @return 取得された個人userのentity
     */
    public Optional<IndividualUserEntity> getIndividualUser(Long id) {
        return individualUserRepository.getById(id);
    }
}
