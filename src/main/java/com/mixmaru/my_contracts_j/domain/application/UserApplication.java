package com.mixmaru.my_contracts_j.domain.application;


import com.mixmaru.my_contracts_j.domain.entity.IndividualUser;
import com.mixmaru.my_contracts_j.repository.IndividualUserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;

@Service
public class UserApplication {
    private final IndividualUserRepository repository;

    public UserApplication(IndividualUserRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public IndividualUser registerNewIndividualUser(String name) {
        var user = new IndividualUser();
        user.setName(name);
        var now = ZonedDateTime.now();
        user.setCreatedAt(now);
        user.setUpdatedAt(now);
        return repository.save(user);
    }
}
