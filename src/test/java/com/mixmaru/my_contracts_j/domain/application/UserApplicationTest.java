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
    void registerNewIndividualUserで登録できgetUserで取得できる() {
        // 準備
        var now = ZonedDateTime.of(2021,2,3,4,5,6,555555555, ZoneId.of("Asia/Tokyo"));
        var savedUser = app.registerNewIndividualUser("太郎", now);

        assertNotNull(savedUser);

        // 実行
        var response = app.getUser(savedUser.getId());

        // 検証

        var individualUser = response.getIndividualUserEntity();
        var corporationUser= response.getCorporationUserEntity();
        assertNull(corporationUser);
        assertNotNull(individualUser);
        assertEquals(savedUser.getId(), individualUser.getId());
        assertEquals("太郎", individualUser.getName());
        assertEquals(now, individualUser.getCreatedAt());
        assertEquals(now, individualUser.getUpdatedAt());
    }

    @Test
    @Transactional
    void getIndividualUser_データがなければ空データが返ってくる() {
        // 実行
        var response = app.getUser(-1000L);

        // 検証
        assertNull(response.getIndividualUserEntity());
        assertNull(response.getCorporationUserEntity());
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

    @Test
    @Transactional
    void getUserでCorporationUserを取得できる() {
        // 準備
        var now = ZonedDateTime.of(2021,2,3,4,5,6,555555555, ZoneId.of("Asia/Tokyo"));
        var savedUser = app.registerNewCorporationUser(
                "担当太郎",
                "社長太郎",
                "会社",
                now);

        assertNotNull(savedUser);

        // 実行
        var response = app.getUser(savedUser.getId());

        // 検証

        var individualUser = response.getIndividualUserEntity();
        var corporationUser= response.getCorporationUserEntity();
        assertNull(individualUser);
        assertNotNull(corporationUser);
        assertEquals(savedUser.getId(), corporationUser.getId());
        assertEquals("担当太郎", corporationUser.getContactPersonName());
        assertEquals("社長太郎", corporationUser.getPresidentName());
        assertEquals("会社", corporationUser.getCorporationName());
        assertEquals(now, corporationUser.getCreatedAt());
        assertEquals(now, corporationUser.getUpdatedAt());
    }
}
