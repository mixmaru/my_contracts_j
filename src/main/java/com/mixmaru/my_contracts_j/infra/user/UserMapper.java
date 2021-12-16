package com.mixmaru.my_contracts_j.infra.user;

import lombok.Data;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Data
@Entity
@Table(name = "users")
public class UserMapper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

    public UserMapper() {}

    public UserMapper(ZonedDateTime createdAt, ZonedDateTime updatedAt) {
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}