package com.mixmaru.my_contracts_j.domain.application;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserApplicationTest {
    @Autowired
    private UserApplication app;

    @Test
    @Transactional
    void registerNewIndividualUserで登録できgetIndividualUserで取得できる() {
        // 準備
        var now = ZonedDateTime.of(2021,2,3,4,5,6,555555555, ZoneId.of("Asia/Tokyo"));
        var savedUser = app.registerNewIndividualUser("太郎", now);

        assertNotNull(savedUser);

        // 実行
        var gotUser = app.getIndividualUser(savedUser.getId());

        // 検証

        assertTrue(gotUser.isPresent());
        assertEquals(savedUser.getId(), gotUser.orElseThrow().getId());
        assertEquals("太郎", gotUser.orElseThrow().getName());
        assertEquals(now, gotUser.orElseThrow().getCreatedAt());
        assertEquals(now, gotUser.orElseThrow().getUpdatedAt());
    }

    @Test
    @Transactional
    void getIndividualUser_データがなければ空データが返ってくる() {
        // 実行
        var gotUser = app.getIndividualUser(-1000L);

        // 検証
        assertTrue(gotUser.isEmpty());
    }

    @Test
    @Transactional
    void registerNewCorporationUserで登録できる() {
        // 実行
        var now = ZonedDateTime.of(2021,2,3,4,5,6,555555555, ZoneId.of("Asia/Tokyo"));
        var savedUser = app.registerNewCorporationUser(
                "担当太郎",
                "社長太郎",
                "会社",
                now);

        // 検証
        assertNotNull(savedUser);
        assertEquals("会社", savedUser.getCorporationName());
        assertEquals("社長太郎", savedUser.getPresidentName());
        assertEquals("担当太郎", savedUser.getContactPersonName());
        assertEquals(now, savedUser.getCreatedAt());
        assertEquals(now, savedUser.getUpdatedAt());
    }
}
