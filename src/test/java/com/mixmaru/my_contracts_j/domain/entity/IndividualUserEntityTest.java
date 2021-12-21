package com.mixmaru.my_contracts_j.domain.entity;

import org.junit.jupiter.api.Test;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;

class IndividualUserEntityTest {

    @Test
    void testToJson() {
        var entity = new IndividualUserEntity();
        entity.setId(1L);
        entity.setName("yamada");
        entity.setCreatedAt(ZonedDateTime.of(2021,1,2,3,4,5,666666666, ZoneId.of("Asia/Tokyo")));
        entity.setUpdatedAt(ZonedDateTime.of(2021,1,2,3,4,5,666666666, ZoneId.of("Asia/Tokyo")));

        assertDoesNotThrow(() -> {
            assertEquals(
                    "{\"id\":1,\"created_at\":\"2021-01-02T03:04:05+09:00\",\"updated_at\":\"2021-01-02T03:04:05+09:00\",\"name\":\"yamada\"}",
                    entity.toJson()
            );
        });
    }
}