package com.example.diploma.services;

import com.example.diploma.dto.requests.GroupRequestDto;
import com.example.diploma.dto.responses.GroupResponseDto;
import com.example.diploma.entities.GroupEntity;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface GroupService {

    GroupResponseDto create(@NotNull @Valid GroupRequestDto requestDto);

    GroupEntity createEntity(@NotNull GroupEntity group);

    GroupResponseDto read(@NotNull Long id);

    GroupEntity readEntity(@NotNull Long id);

    List<GroupResponseDto> readAll(@Nullable Long id);

    List<GroupEntity> readAllEntity(@Nullable Long id);

    GroupResponseDto update(@NotNull Long id, @NotNull @Valid GroupRequestDto requestDto);

    GroupEntity updateEntity(@NotNull GroupEntity group);

    void delete(@NotNull Long id);

}

