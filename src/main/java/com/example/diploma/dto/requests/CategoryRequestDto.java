package com.example.diploma.dto.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class CategoryRequestDto {

    @JsonProperty("userId")
    @NotNull(message = "Не указан владелец")
    Long userId;

    @JsonProperty("name")
    @NotBlank(message = "Не указано название категории")
    String name;

    @JsonProperty("desc")
    String desc;

    @JsonProperty("colour")
    String colour;

    public CategoryRequestDto(Long userId, String name, String desc, String colour) {
        this.userId = userId;
        this.name = name;
        this.desc = desc != null ? desc.trim() : null;
        this.colour = colour != null ? colour.trim() : null;
    }
}
