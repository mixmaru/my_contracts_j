package com.mixmaru.my_contracts_j.domain.application;

import com.mixmaru.my_contracts_j.repository.IndividualUserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserApplicationTest {
    @Autowired
    private UserApplication app;

    @Autowired
    private IndividualUserRepository repository;

    @Test
    void registerNewIndividualUserが実行でき登録できる() {
        // 実行
        var savedUser = app.registerNewIndividualUser("太郎");
        // 検証
        var user = repository.findById(savedUser.getUserId());
        assertEquals(savedUser.getUserId(), user.orElseThrow().getUserId());

        // 実行
        savedUser = app.registerNewIndividualUser("次郎");
        // 検証
        user = repository.findById(savedUser.getUserId());
        assertEquals(savedUser.getUserId(), user.orElseThrow().getUserId());
    }
}
