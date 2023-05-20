package com.example.diploma.dto.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;

import java.sql.Timestamp;

@Value
@Builder(toBuilder = true)
public class TaskRequestDto {

    @JsonProperty("userId")
    @NotNull(message = "Не указан пользователь")
    Long userId;

    @JsonProperty("name")
    @NotBlank(message = "Не указано название задачи")
    String name;

    @JsonProperty("categoryId")
    Long categoryId;

    @JsonProperty("priorityId")
    Long priorityId;

    @JsonProperty("plannedTime")
    Timestamp plannedTime;

    @JsonProperty("deadlineTime")
    Timestamp deadlineTime;

    @JsonProperty("desc")
    String desc;

    @JsonProperty("groupId")
    Long groupId;

    @JsonProperty("statusId")
    @NotNull(message = "Не указан статус задачи")
    Long statusId;

    @JsonProperty("completeTime")
    Timestamp completeTime;

    public TaskRequestDto(Long userId, String name, Long categoryId, Long priorityId, Timestamp plannedTime, Timestamp deadlineTime, String desc, Long groupId, Long statusId, Timestamp completeTime) {
        this.userId = userId;
        this.name = name;
        this.categoryId = categoryId;
        this.priorityId = priorityId;
        this.plannedTime = plannedTime;
        this.deadlineTime = deadlineTime;
        this.desc = desc != null ? desc.trim() : null;
        this.groupId = groupId;
        this.statusId = statusId;
        this.completeTime = completeTime;
    }
}
