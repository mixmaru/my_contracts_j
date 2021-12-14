package com.mixmaru.my_contracts_j.controller;

import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * ここ見ながら書いた
 * 実際にwebサーバーを立ててテスト
 * https://spring.pleiades.io/guides/gs/testing-web/
 * @throws Exception
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void RegistUser() throws Exception {
        @Data
        class Request {
            private String name;
        }
        var request = new Request();
        request.setName("yamada");

        assertThat(this.restTemplate.postForObject(
                "http://localhost:" + port + "/user/?name=yamada",
                request,
                String.class
        )).contains("yamada");
    }
}