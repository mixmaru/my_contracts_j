package com.mixmaru.my_contracts_j.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.ZonedDateTimeSerializer;
import lombok.ToString;

import java.time.ZonedDateTime;

@ToString
public abstract class UserEntity {

    ///// private field /////
    private long id;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

    ///// constructor /////
    UserEntity() {}

    ///// public method /////
    public long getId() {
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

    public String toJson() throws JsonProcessingException {
        var mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        return mapper.writeValueAsString(this);
    }
}
