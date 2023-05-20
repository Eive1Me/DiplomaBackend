package com.example.diploma.mappers;

import com.example.diploma.entities.*;
import com.example.diploma.dto.requests.TaskRequestDto;
import com.example.diploma.dto.responses.TaskResponseDto;
import com.example.diploma.entities.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TaskMapper {

    private final UserMapper userMapper;
    private final CategoryMapper categoryMapper;
    private final PriorityMapper priorityMapper;
    private final GroupMapper groupMapper;
    private final StatusMapper statusMapper;

    public TaskEntity toEntity(TaskRequestDto taskRequestDto, UserEntity userEntity, CategoryEntity categoryEntity, PriorityEntity priorityEntity, GroupEntity groupEntity, StatusEntity statusEntity) {
        return TaskEntity.builder()
                .userId(userEntity)
                .name(taskRequestDto.getName())
                .categoryId(categoryEntity)
                .priorityId(priorityEntity)
                .plannedTime(taskRequestDto.getPlannedTime())
                .deadlineTime(taskRequestDto.getDeadlineTime())
                .desc(taskRequestDto.getDesc())
                .groupId(groupEntity)
                .statusId(statusEntity)
                .completeTime(taskRequestDto.getCompleteTime())
                .build();
    }

    public TaskResponseDto toDto(TaskEntity task) {
        return TaskResponseDto.builder()
                .id(task.getId())
                .userId(userMapper.toDto(task.getUserId()))
                .name(task.getName())
                .categoryId(categoryMapper.toDto(task.getCategoryId()))
                .priorityId(priorityMapper.toDto(task.getPriorityId()))
                .plannedTime(task.getPlannedTime())
                .deadlineTime(task.getDeadlineTime())
                .desc(task.getDesc())
                .groupId(groupMapper.toDto(task.getGroupId()))
                .statusId(statusMapper.toDto(task.getStatusId()))
                .completeTime(task.getCompleteTime())
                .build();
    }

}
