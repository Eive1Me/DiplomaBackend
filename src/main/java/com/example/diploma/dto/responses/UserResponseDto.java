package com.example.diploma.dto.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class UserResponseDto {

    @JsonProperty("id")
    Long id;

    @JsonProperty("login")
    String login;

    @JsonProperty("password")
    String password;

}
