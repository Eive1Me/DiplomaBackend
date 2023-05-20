package com.example.diploma.dto.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class CategoryResponseDto {

    @JsonProperty("id")
    Long id;

    @JsonProperty("userId")
    UserResponseDto userId;

    @JsonProperty("name")
    String name;

    @JsonProperty("desc")
    String desc;

    @JsonProperty("colour")
    String colour;

}
