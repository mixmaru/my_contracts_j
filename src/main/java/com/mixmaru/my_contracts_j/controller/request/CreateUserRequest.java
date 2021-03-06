package com.mixmaru.my_contracts_j.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class CreateUserRequest {
    private String type;

    // individualUserç”¨
    private String name;

    // corporationUserç”¨
    private String contactPersonName;
    private String presidentName;
    private String corporationName;
}