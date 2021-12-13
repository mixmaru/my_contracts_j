package com.mixmaru.my_contracts_j.controller;

import com.mixmaru.my_contracts_j.domain.application.UserApplication;
import com.mixmaru.my_contracts_j.domain.entity.IndividualUser;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserApplication userApplication;

    public UserController(UserApplication userApplication) {
        this.userApplication = userApplication;
    }

    @PostMapping("/user/")
    public IndividualUser register(@RequestParam("name") String name) {
        var result = userApplication.registerNewIndividualUser(name);
        return result;
    }
}
