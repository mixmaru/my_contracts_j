package com.mixmaru.my_contracts_j.controller;

import com.mixmaru.my_contracts_j.domain.application.UserApplication;
import com.mixmaru.my_contracts_j.domain.entity.IndividualUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

/**
 * ここ見ながら書いた
 * webサーバーモックでテスト
 * https://spring.pleiades.io/guides/gs/testing-web/
 * @throws Exception
 */
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerMockMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserApplication userApplication;

    @Test
    public void RegisterUser() throws Exception {
        // モック作成
        var retUser = new IndividualUser();
        retUser.setUserId(1L);
        retUser.setName("yamada");
        var now = ZonedDateTime.of(2021, 12, 24, 22, 10, 10, 0, ZoneId.of("Asia/Tokyo"));
        retUser.setCreatedAt(now);
        retUser.setUpdatedAt(now);

        when(userApplication.registerNewIndividualUser("yamada")).thenReturn(retUser);

        this.mockMvc.perform(post("/user/").param("name", "yamada")).andExpect(content().string("{\"userId\":1,\"name\":\"yamada\",\"createdAt\":\"2021-12-24T22:10:10+09:00\",\"updatedAt\":\"2021-12-24T22:10:10+09:00\"}"));
    }
}