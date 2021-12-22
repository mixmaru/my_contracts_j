package com.mixmaru.my_contracts_j.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mixmaru.my_contracts_j.controller.request.CreateUserRequest;
import com.mixmaru.my_contracts_j.domain.application.UserApplication;
import com.mixmaru.my_contracts_j.domain.entity.CorporationUserEntity;
import com.mixmaru.my_contracts_j.domain.entity.IndividualUserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.*;

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
    public void RegisterUser_individualUserの登録ができる() throws Exception {
        // モック作成
        var now = ZonedDateTime.of(2021, 12, 24, 22, 10, 10, 0, ZoneId.of("Asia/Tokyo"));
        var retUser = new IndividualUserEntity("山田", now);
        retUser.setId(1L);

        when(userApplication.registerNewIndividualUser(eq("山田"), any(ZonedDateTime.class))).thenReturn(retUser);

        var objectMapper = new ObjectMapper();
        var request = new CreateUserRequest();
        request.setType("individual_user");
        request.setName("山田");
        var result = this.mockMvc.perform(
                post("/user/")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                ).andReturn();
        assertEquals("{\"id\":1,\"created_at\":\"2021-12-24T22:10:10+09:00\",\"updated_at\":\"2021-12-24T22:10:10+09:00\",\"name\":\"山田\"}", result.getResponse().getContentAsString(StandardCharsets.UTF_8));
    }

    @Test
    public void RegisterUser_corporationUserの登録ができる() throws Exception {
        // モック作成
        var now = ZonedDateTime.of(2021, 12, 24, 22, 10, 10, 0, ZoneId.of("Asia/Tokyo"));
        var retUser = new CorporationUserEntity();
        retUser.setId(1L);
        retUser.setContactPersonName("岡田");
        retUser.setPresidentName("社長太郎");
        retUser.setCorporationName("会社");
        retUser.setCreatedAt(now);
        retUser.setUpdatedAt(now);

        when(userApplication.registerNewCorporationUser(eq("岡田"), eq("社長太郎"), eq("会社"), any(ZonedDateTime.class))).thenReturn(retUser);

        // リクエスト作成
        var request = new CreateUserRequest();
        request.setType("corporation_user");
        request.setContactPersonName("岡田");
        request.setPresidentName("社長太郎");
        request.setCorporationName("会社");
        var contents = this.mockMvc.perform(
                post("/user/")
                        .content(new ObjectMapper().writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        assertEquals("{\"id\":1,\"created_at\":\"2021-12-24T22:10:10+09:00\",\"updated_at\":\"2021-12-24T22:10:10+09:00\",\"contact_person_name\":\"岡田\",\"president_name\":\"社長太郎\",\"corporation_name\":\"会社\"}", contents);
    }

    @Test
    public void get_userが存在する場合データが取得できる() throws Exception {
        // モック作成
        var now = ZonedDateTime.of(2021, 12, 24, 22, 10, 10, 0, ZoneId.of("Asia/Tokyo"));
        var retUser = new IndividualUserEntity("yamada", now);
        retUser.setId(1L);

        when(userApplication.getIndividualUser(1L)).thenReturn(Optional.ofNullable(retUser));

        this.mockMvc.perform(get("/user/1")).andExpect(content().json("{\"id\":1,\"created_at\":\"2021-12-24T22:10:10+09:00\",\"updated_at\":\"2021-12-24T22:10:10+09:00\",\"name\":\"yamada\"}"));
    }

    @Test
    public void get_userが存在しない場合404が返る() throws Exception {
        // モック作成
        when(userApplication.getIndividualUser(1L)).thenReturn(Optional.empty());

        // 検証
        this.mockMvc.perform(get("/user/-100"))
                .andExpect(
                        status().isNotFound()
                );
    }
}