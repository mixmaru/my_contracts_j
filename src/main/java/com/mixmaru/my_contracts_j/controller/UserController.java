package com.mixmaru.my_contracts_j.controller;

import com.mixmaru.my_contracts_j.domain.application.UserApplication;
import com.mixmaru.my_contracts_j.domain.entity.IndividualUserEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;

@RestController
public class UserController {

    private final UserApplication userApplication;

    public UserController(UserApplication userApplication) {
        this.userApplication = userApplication;
    }

    @PostMapping("/user/")
    public IndividualUserEntity register(@RequestParam("name") String name) {
        var now = ZonedDateTime.now();
        var result = userApplication.registerNewIndividualUser(name, now);
        return result;
    }
}
