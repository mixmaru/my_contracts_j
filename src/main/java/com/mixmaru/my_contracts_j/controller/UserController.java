package com.mixmaru.my_contracts_j.controller;

import com.mixmaru.my_contracts_j.domain.application.UserApplication;
import com.mixmaru.my_contracts_j.domain.entity.IndividualUserEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.ZonedDateTime;
import java.util.Optional;

@RestController
public class UserController {

    private final UserApplication userApplication;

    public UserController(UserApplication userApplication) {
        this.userApplication = userApplication;
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Optional<IndividualUserEntity>> get(@PathVariable("id") Long id) {
        var user = userApplication.getIndividualUser(id);
        HttpStatus status;
        if(user.isPresent()) {
            status = HttpStatus.OK;
        } else {
            status = HttpStatus.NO_CONTENT;
        }

        return new ResponseEntity<Optional<IndividualUserEntity>>(user, status);
    }

    @PostMapping("/user/")
    public IndividualUserEntity register(@RequestParam("name") String name) {
        var now = ZonedDateTime.now();
        return userApplication.registerNewIndividualUser(name, now);
    }
}
