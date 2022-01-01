package com.mixmaru.my_contracts_j.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mixmaru.my_contracts_j.controller.request.CreateUserRequest;
import com.mixmaru.my_contracts_j.domain.application.UserApplication;
import com.mixmaru.my_contracts_j.domain.entity.UserEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;

@RestController
public class UserController {

    private final UserApplication userApplication;

    public UserController(UserApplication userApplication) {
        this.userApplication = userApplication;
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<String> get(@PathVariable("id") long id) {
        var response = userApplication.getUser(id);
        UserEntity user = null;
        if(response.getIndividualUserEntity() != null) {
            user = response.getIndividualUserEntity();
        } else if(response.getCorporationUserEntity() != null) {
            user = response.getCorporationUserEntity();
        } else {
            // userが存在しなければ404
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        try {
            return new ResponseEntity<>(user.toJson(), HttpStatus.OK);
        } catch (JsonProcessingException e) {
            // jsonパースが失敗した場合
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/user/")
    public UserEntity register(@RequestBody CreateUserRequest request) {
        var now = ZonedDateTime.now();
        switch (request.getType()) {
            case "individual_user":
                return userApplication.registerNewIndividualUser(request.getName(), now);
            case "corporation_user":
                return userApplication.registerNewCorporationUser(
                        request.getContactPersonName(),
                        request.getPresidentName(),
                        request.getCorporationName(),
                        now);
            default:
                throw new IllegalArgumentException();
        }
    }
}
