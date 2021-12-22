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
    public ResponseEntity<String> get(@PathVariable("id") Long id) {
        var user = userApplication.getIndividualUser(id);
        if(user.isPresent()) {
            // userが存在するならデータをjson形式で返す
            try{
                var body = user.orElseThrow().toJson();
                return new ResponseEntity<>(body, HttpStatus.OK);
            } catch (JsonProcessingException e) {
                // jsonパースが失敗した場合
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            // userが存在しなければ404
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
