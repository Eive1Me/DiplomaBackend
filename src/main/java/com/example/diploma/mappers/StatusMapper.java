package com.example.diploma.mappers;

import com.example.diploma.dto.requests.StatusRequestDto;
import com.example.diploma.dto.responses.StatusResponseDto;
import com.example.diploma.entities.StatusEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StatusMapper {

    public StatusEntity toEntity(StatusRequestDto statusRequestDto) {
        return StatusEntity.builder()
                .value(statusRequestDto.getValue())
                .build();
    }

    public StatusResponseDto toDto(StatusEntity status) {
        return StatusResponseDto.builder()
                .id(status.getId())
                .value(status.getValue())
                .build();
    }

}
