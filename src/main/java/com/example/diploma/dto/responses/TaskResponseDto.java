package com.example.diploma.dto.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

import java.sql.Timestamp;

@Value
@Builder(toBuilder = true)
public class TaskResponseDto {

    @JsonProperty("id")
    Long id;

    @JsonProperty("userId")
    UserResponseDto userId;

    @JsonProperty("name")
    String name;

    @JsonProperty("categoryId")
    CategoryResponseDto categoryId;

    @JsonProperty("priorityId")
    PriorityResponseDto priorityId;

    @JsonProperty("plannedTime")
    Timestamp plannedTime;

    @JsonProperty("deadlineTime")
    Timestamp deadlineTime;

    @JsonProperty("desc")
    String desc;

    @JsonProperty("groupId")
    GroupResponseDto groupId;

    @JsonProperty("statusId")
    StatusResponseDto statusId;

    @JsonProperty("completeTime")
    Timestamp completeTime;

}
