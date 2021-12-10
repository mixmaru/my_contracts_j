package com.mixmaru.my_contracts_j.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/")
    public String getRoot() {
        return "root_test";
    }
}
