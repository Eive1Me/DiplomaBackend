package com.example.diploma.mappers;

import com.example.diploma.dto.requests.PriorityRequestDto;
import com.example.diploma.dto.responses.PriorityResponseDto;
import com.example.diploma.entities.PriorityEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PriorityMapper {

    public PriorityEntity toEntity(PriorityRequestDto priorityRequestDto) {
        return PriorityEntity.builder()
                .value(priorityRequestDto.getValue())
                .build();
    }

    public PriorityResponseDto toDto(PriorityEntity priority) {
        return PriorityResponseDto.builder()
                .id(priority.getId())
                .value(priority.getValue())
                .build();
    }

}
