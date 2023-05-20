package com.example.diploma.services;

import com.example.diploma.entities.GroupEntity;
import com.example.diploma.dto.requests.GroupRequestDto;
import com.example.diploma.dto.responses.GroupResponseDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public interface GroupService {

    GroupResponseDto create(@NotNull @Valid GroupRequestDto requestDto);
    GroupEntity createEntity(@NotNull GroupEntity group);

    GroupResponseDto read(@NotNull Long id);
    GroupEntity readEntity(@NotNull Long id);

    GroupResponseDto update(@NotNull Long id, @NotNull @Valid GroupRequestDto requestDto);
    GroupEntity updateEntity(@NotNull GroupEntity group);

    void delete(@NotNull Long id);

}

