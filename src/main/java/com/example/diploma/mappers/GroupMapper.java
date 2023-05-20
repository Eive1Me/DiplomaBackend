package com.example.diploma.mappers;

import com.example.diploma.entities.GroupEntity;
import com.example.diploma.dto.requests.GroupRequestDto;
import com.example.diploma.dto.responses.GroupResponseDto;
import com.example.diploma.entities.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GroupMapper {

    private final UserMapper userMapper;

    public GroupEntity toEntity(GroupRequestDto groupRequestDto, UserEntity userEntity){
        return GroupEntity.builder()
                .name(groupRequestDto.getName())
                .userId(userEntity)
                .build();
    }

    public GroupResponseDto toDto(GroupEntity group) {
        return GroupResponseDto.builder()
                .id(group.getId())
                .name(group.getName())
                .userId(userMapper.toDto(group.getUserId()))
                .build();
    }

}
