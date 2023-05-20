package com.example.diploma.services;

import com.example.diploma.entities.PriorityEntity;
import com.example.diploma.dto.requests.PriorityRequestDto;
import com.example.diploma.dto.responses.PriorityResponseDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public interface PriorityService {

    PriorityResponseDto create(@NotNull @Valid PriorityRequestDto requestDto);
    PriorityEntity createEntity(@NotNull PriorityEntity priority);

    PriorityResponseDto read(@NotNull Long id);
    PriorityEntity readEntity(@NotNull Long id);

    PriorityResponseDto update(@NotNull Long id, @NotNull @Valid PriorityRequestDto requestDto);
    PriorityEntity updateEntity(@NotNull PriorityEntity priority);

    void delete(@NotNull Long id);

}
