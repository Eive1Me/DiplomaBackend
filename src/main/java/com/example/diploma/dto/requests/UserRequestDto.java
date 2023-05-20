package com.example.diploma.dto.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class UserRequestDto {

    @JsonProperty("login")
    @NotBlank(message = "Не указан логин")
    String login;

    @JsonProperty("password")
    @NotBlank(message = "Не указан пароль")
    String password;

    public UserRequestDto(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
