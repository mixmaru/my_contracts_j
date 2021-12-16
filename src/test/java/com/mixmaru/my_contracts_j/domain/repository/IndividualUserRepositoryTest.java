package com.mixmaru.my_contracts_j.domain.repository;

import com.mixmaru.my_contracts_j.domain.entity.IndividualUserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class IndividualUserRepositoryTest {

    @Autowired
    private IndividualUserRepository individualUserRepository;

    @Test
    void save() {
        // 準備
        var now = ZonedDateTime.of(2021,1,2,3,4,5,6, ZoneId.of("Asia/Tokyo"));
        var newUser = new IndividualUserEntity("yamada", now);

        // 実行
        var savedUser = individualUserRepository.save(newUser);

        // 検証
        assertNotEquals(0, savedUser.getId());
        assertEquals("yamada", savedUser.getName());
        assertEquals(now, savedUser.getCreatedAt());
        assertEquals(now, savedUser.getUpdatedAt());
    }
}