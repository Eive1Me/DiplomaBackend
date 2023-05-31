package com.example.diploma.services;

import com.example.diploma.dto.requests.UserRequestDto;
import com.example.diploma.dto.responses.UserResponseDto;
import com.example.diploma.entities.UserEntity;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface UserService {

    UserResponseDto create(@NotNull @Valid UserRequestDto requestDto);

    UserEntity createEntity(@NotNull UserEntity user);

    UserResponseDto addGroup(@NotNull Long userId, @NotNull Long groupId);

    UserResponseDto getUserByEssentials(@NotNull String login, @NotNull String password);

    UserResponseDto read(@NotNull Long id);

    UserEntity readEntity(@NotNull Long id);

    List<UserResponseDto> readAll(@Nullable Long id);

    List<UserEntity> readAllEntity(@Nullable Long id);

    UserResponseDto update(@NotNull Long id, @NotNull @Valid UserRequestDto requestDto);

    UserEntity updateEntity(@NotNull UserEntity user);

    void delete(@NotNull Long id);

}
