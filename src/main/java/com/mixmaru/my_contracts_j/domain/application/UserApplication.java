package com.mixmaru.my_contracts_j.domain.application;


import com.mixmaru.my_contracts_j.domain.entity.IndividualUserEntity;
import com.mixmaru.my_contracts_j.domain.repository.IndividualUserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;

@Service
public class UserApplication {
    private final IndividualUserRepository individualUserRepository;

    public UserApplication(
            IndividualUserRepository individualUserRepository
    ) {
        this.individualUserRepository = individualUserRepository;
    }

    @Transactional
    public IndividualUserEntity registerNewIndividualUser(String name, ZonedDateTime createdAt) {
        var newUser = new IndividualUserEntity(name, createdAt);
        return individualUserRepository.save(newUser);
    }
}
