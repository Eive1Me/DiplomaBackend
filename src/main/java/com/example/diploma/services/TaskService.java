package com.example.diploma.services;

import com.example.diploma.entities.TaskEntity;
import com.example.diploma.dto.requests.TaskRequestDto;
import com.example.diploma.dto.responses.TaskResponseDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public interface TaskService {

    TaskResponseDto create(@NotNull @Valid TaskRequestDto requestDto);
    TaskEntity createEntity(@NotNull TaskEntity task);

    TaskResponseDto read(@NotNull Long id);
    TaskEntity readEntity(@NotNull Long id);

    TaskResponseDto update(@NotNull Long id, @NotNull @Valid TaskRequestDto requestDto);
    TaskEntity updateEntity(@NotNull TaskEntity task);

    void delete(@NotNull Long id);

}
