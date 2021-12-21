package com.mixmaru.my_contracts_j.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.ZonedDateTimeSerializer;

import java.time.ZonedDateTime;

abstract class UserEntity {
    public Long getId() {
        return id;
    }

    @JsonGetter("id")
    public void setId(Long id) {
        this.id = id;
    }

    @JsonGetter("created_at")
    @JsonSerialize(using = ZonedDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZZZZZ")
    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @JsonGetter("updated_at")
    @JsonSerialize(using = ZonedDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZZZZZ")
    public ZonedDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(ZonedDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    private Long id;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

    public UserEntity() {}

    public UserEntity(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
        this.updatedAt = createdAt;
    }

    public UserEntity(Long id, ZonedDateTime createdAt, ZonedDateTime updatedAt) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String toJson() throws JsonProcessingException {
        var mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        return mapper.writeValueAsString(this);
    }
}
