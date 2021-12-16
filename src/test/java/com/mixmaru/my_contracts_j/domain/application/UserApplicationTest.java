package com.mixmaru.my_contracts_j.domain.application;

import com.mixmaru.my_contracts_j.domain.repository.IndividualUserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserApplicationTest {
    @Autowired
    private UserApplication app;

    @Autowired
    private IndividualUserRepository individualUserRepository;

    @Test
    void registerNewIndividualUserが実行でき登録できる() {
        // 実行
        var now = ZonedDateTime.of(2021,2,3,4,5,6,7, ZoneId.of("Asia/Tokyo"));
        var savedUser = app.registerNewIndividualUser("太郎", now);

        // 検証
        assertNotNull(savedUser);
//        var user = infra.findById(savedUser.getUserId());
//        assertEquals(savedUser.getUserId(), user.orElseThrow().getUserId());
//        assertEquals("太郎", user.orElseThrow().getName());
//
//        // 実行
//        savedUser = app.registerNewIndividualUser("次郎");
//        // 検証
//        user = infra.findById(savedUser.getUserId());
//        assertEquals(savedUser.getUserId(), user.orElseThrow().getUserId());
//        assertEquals("次郎", user.orElseThrow().getUserId());
    }
}
