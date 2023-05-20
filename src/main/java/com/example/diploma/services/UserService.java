package com.example.diploma.services;

import com.example.diploma.entities.UserEntity;
import com.example.diploma.dto.requests.UserRequestDto;
import com.example.diploma.dto.responses.UserResponseDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface UserService {

    UserResponseDto create(@NotNull @Valid UserRequestDto requestDto);
    UserEntity createEntity(@NotNull UserEntity user);

    UserResponseDto addGroup(@NotNull Long userId, @NotNull Long groupId);

    UserResponseDto read(@NotNull Long id);
    UserEntity readEntity(@NotNull Long id);

    List<UserResponseDto> readAll(@NotNull Long id);

    List<UserEntity> readAllEntity(@NotNull Long id);

    UserResponseDto update(@NotNull Long id, @NotNull @Valid UserRequestDto requestDto);
    UserEntity updateEntity(@NotNull UserEntity user);

    void delete(@NotNull Long id);

}
