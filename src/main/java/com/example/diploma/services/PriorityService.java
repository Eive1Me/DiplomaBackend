package com.example.diploma.services;

import com.example.diploma.dto.requests.PriorityRequestDto;
import com.example.diploma.dto.responses.PriorityResponseDto;
import com.example.diploma.entities.PriorityEntity;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface PriorityService {

    PriorityResponseDto create(@NotNull @Valid PriorityRequestDto requestDto);

    PriorityEntity createEntity(@NotNull PriorityEntity priority);

    PriorityResponseDto read(@NotNull Long id);

    PriorityEntity readEntity(@NotNull Long id);

    List<PriorityResponseDto> readAll();

    List<PriorityEntity> readAllEntity();

    PriorityResponseDto update(@NotNull Long id, @NotNull @Valid PriorityRequestDto requestDto);

    PriorityEntity updateEntity(@NotNull PriorityEntity priority);

    void delete(@NotNull Long id);

}
