package com.mixmaru.my_contracts_j.domain.repository;

import com.mixmaru.my_contracts_j.domain.entity.IndividualUserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class IndividualUserRepositoryTest {

    @Autowired
    private IndividualUserRepository individualUserRepository;

    @Test
    @Transactional
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

    @Test
    @Transactional
    void getById_1件データが取得できる() {
        // 準備
        var now = ZonedDateTime.of(2021,1,2,3,4,5,6, ZoneId.of("Asia/Tokyo"));
        var newUser = new IndividualUserEntity("yamada", now);
        var savedUser = individualUserRepository.save(newUser);

        // 実行
        var loadedUser = individualUserRepository.getById(savedUser.getId());

        // 検証
        assertTrue(loadedUser.isPresent());
        assertEquals("yamada", loadedUser.orElseThrow().getName());
        assertEquals(now, loadedUser.orElseThrow().getCreatedAt());
        assertEquals(now, loadedUser.orElseThrow().getUpdatedAt());
    }

    @Test
    void getById_対象データがなかったとき() {
        // 実行
        var loadedUser = individualUserRepository.getById(-100L);

        // 検証
        assertFalse(loadedUser.isPresent());
        assertTrue(loadedUser.isEmpty());
    }

}