package com.example.diploma.dto.requests;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class PriorityRequestDto {

    @JsonProperty("value")
    @NotBlank(message = "Не указано значение приоритета")
    String value;

    @JsonCreator
    public PriorityRequestDto(@JsonProperty("value") String value) {
        this.value = value;
    }

}
