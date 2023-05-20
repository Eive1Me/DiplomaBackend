package com.example.diploma.dto.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class GroupRequestDto {

    @JsonProperty("name")
    @NotBlank(message = "Не указано название группы")
    String name;

    @JsonProperty("userId")
    @NotNull(message = "Не указан владелец")
    Long userId;

    public GroupRequestDto(String name, Long userId) {
        this.name = name;
        this.userId = userId;
    }
}
