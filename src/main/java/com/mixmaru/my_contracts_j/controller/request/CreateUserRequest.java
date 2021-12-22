package com.mixmaru.my_contracts_j.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class CreateUserRequest {
    private String type;

    // individualUser用
    private String name;

    // corporationUser用
    private String contactPersonName;
    private String presidentName;
    private String corporationName;
}