package com.example.diploma.dto.requests;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class StatusRequestDto {

    @JsonProperty("value")
    @NotBlank(message = "Не указано значение статуса")
    String value;

    @JsonCreator
    public StatusRequestDto(@JsonProperty("value") String value) {
        this.value = value;
    }

}
