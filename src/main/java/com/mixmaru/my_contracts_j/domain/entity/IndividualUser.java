package com.mixmaru.my_contracts_j.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "users_individual")
public class IndividualUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String name;
    private Date createdAt;
    private Date updatedAt;
}
