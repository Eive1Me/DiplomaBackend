package com.example.diploma.services;

import com.example.diploma.dto.requests.StatusRequestDto;
import com.example.diploma.dto.responses.StatusResponseDto;
import com.example.diploma.entities.StatusEntity;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public interface StatusService {

    StatusResponseDto create(@NotNull @Valid StatusRequestDto requestDto);

    StatusEntity createEntity(@NotNull StatusEntity status);

    StatusResponseDto read(@NotNull Long id);

    StatusEntity readEntity(@NotNull Long id);

    StatusResponseDto update(@NotNull Long id, @NotNull @Valid StatusRequestDto requestDto);

    StatusEntity updateEntity(@NotNull StatusEntity status);

    void delete(@NotNull Long id);

}
