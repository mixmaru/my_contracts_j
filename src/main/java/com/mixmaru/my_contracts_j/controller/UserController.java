package com.mixmaru.my_contracts_j.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.ZonedDateTimeSerializer;
import com.mixmaru.my_contracts_j.controller.request.CreateUserRequest;
import com.mixmaru.my_contracts_j.domain.application.UserApplication;
import com.mixmaru.my_contracts_j.domain.entity.CorporationUserEntity;
import com.mixmaru.my_contracts_j.domain.entity.IndividualUserEntity;
import com.mixmaru.my_contracts_j.domain.entity.UserEntity;
import lombok.ToString;
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
        UserDto userDto;
        if(response.getIndividualUserEntity() != null) {
            var entity = response.getIndividualUserEntity();
            userDto = new IndividualUserDto(entity);
        } else if(response.getCorporationUserEntity() != null) {
            var entity = response.getCorporationUserEntity();
            userDto = new CorporationUserDto(entity);
        } else {
            // userが存在しなければ404
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        try {
            return new ResponseEntity<>(userDto.toJson(), HttpStatus.OK);
        } catch (JsonProcessingException e) {
            // jsonパースが失敗した場合
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/user/")
    public ResponseEntity<String> register(@RequestBody CreateUserRequest request) {
        var now = ZonedDateTime.now();
        switch (request.getType()) {
            case "individual_user":
                var individualEntity = userApplication.registerNewIndividualUser(request.getName(), now);
                return createResponseEntity(new IndividualUserDto(individualEntity));
            case "corporation_user":
                var corpEntity = userApplication.registerNewCorporationUser(
                        request.getContactPersonName(),
                        request.getPresidentName(),
                        request.getCorporationName(),
                        now);
                return createResponseEntity(new CorporationUserDto(corpEntity));
            default:
                throw new IllegalArgumentException();
        }
    }

    private ResponseEntity<String> createResponseEntity(UserDto dto) {
        try {
            return new ResponseEntity<>(dto.toJson(), HttpStatus.OK);
        } catch (JsonProcessingException e) {
            // jsonパースが失敗した場合
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ToString
    @JsonPropertyOrder({"id", "type" ,"name", "created_at", "updated_at"})
    private static final class IndividualUserDto extends UserDto {

        private final IndividualUserEntity entity;

        private IndividualUserDto(IndividualUserEntity entity) {
            super(entity);
            this.entity = entity;
        }

        @Override
        public String getType() {
            return "individual_user";
        }

        @JsonGetter("name")
        public String getName() {
            return entity.getName();
        }
    }

    @ToString
    @JsonPropertyOrder({"id", "type" ,"corporation_name", "president_name", "contact_person_name", "created_at", "updated_at"})
    private static final class CorporationUserDto extends UserDto {

        private final CorporationUserEntity entity;

        private CorporationUserDto(CorporationUserEntity entity) {
            super(entity);
            this.entity = entity;
        }

        @Override
        public String getType() {
            return "corporation_user";
        }

        @JsonGetter("corporation_name")
        public String getCorporationName() {
            return entity.getCorporationName();
        }

        @JsonGetter("president_name")
        public String getPresidentName() {
            return entity.getPresidentName();
        }

        @JsonGetter("contact_person_name")
        public String getContactPersonName() {
            return entity.getContactPersonName();
        }
    }

    @ToString
    private static abstract class UserDto {

        private final UserEntity entity;

        public UserDto(UserEntity entity) {
            this.entity = entity;
        }

        @JsonGetter("id")
        public long getId() {
            return entity.getId();
        }

        @JsonGetter("type")
        public abstract String getType();

        @JsonGetter("created_at")
        @JsonSerialize(using = ZonedDateTimeSerializer.class)
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZZZZZ")
        public ZonedDateTime getCreatedAt() {
            return entity.getCreatedAt();
        }

        @JsonGetter("updated_at")
        @JsonSerialize(using = ZonedDateTimeSerializer.class)
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZZZZZ")
        public ZonedDateTime getUpdatedAt() {
            return entity.getUpdatedAt();
        }

        public String toJson() throws JsonProcessingException {
            var mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            return mapper.writeValueAsString(this);
        }
    }
}
