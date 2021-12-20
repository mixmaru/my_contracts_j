package com.mixmaru.my_contracts_j.controller;

import com.mixmaru.my_contracts_j.domain.application.UserApplication;
import com.mixmaru.my_contracts_j.domain.entity.IndividualUserEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;

@RestController
public class UserController {

    private final UserApplication userApplication;

    public UserController(UserApplication userApplication) {
        this.userApplication = userApplication;
    }

    @GetMapping("/user/{id}")
    public IndividualUserEntity get(@PathVariable("id") Long id) {
        return userApplication.getIndividualUser(id).orElseThrow();
    }

    @PostMapping("/user/")
    public IndividualUserEntity register(@RequestParam("name") String name) {
        var now = ZonedDateTime.now();
        var result = userApplication.registerNewIndividualUser(name, now);
        return result;
    }
}
