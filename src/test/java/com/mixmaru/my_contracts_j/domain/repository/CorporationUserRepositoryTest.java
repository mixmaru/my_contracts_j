package com.mixmaru.my_contracts_j.domain.repository;

import com.mixmaru.my_contracts_j.domain.entity.CorporationUserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CorporationUserRepositoryTest {

    @Autowired
    private CorporationUserRepository repository;

    @Test
    @Transactional
    void save() {
        // 準備
        var now = ZonedDateTime.of(2021,1,2,3,4,5,666666666, ZoneId.of("Asia/Tokyo"));
        var entity = CorporationUserEntity.createNew("会社", "社長", "担当", now);

        // 実行
        var savedEntity = repository.save(entity);

        // 検証
        assertNotNull(savedEntity.getId());
        assertEquals("会社", savedEntity.getCorporationName());
        assertEquals("社長", savedEntity.getPresidentName());
        assertEquals("担当", savedEntity.getContactPersonName());
        assertEquals(now, savedEntity.getCreatedAt());
        assertEquals(now, savedEntity.getUpdatedAt());
    }
}