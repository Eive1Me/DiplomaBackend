package com.example.diploma.services;

import com.example.diploma.dto.requests.CategoryRequestDto;
import com.example.diploma.dto.responses.CategoryResponseDto;
import com.example.diploma.entities.CategoryEntity;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface CategoryService {

    CategoryResponseDto create(@NotNull @Valid CategoryRequestDto requestDto);

    CategoryEntity createEntity(@NotNull CategoryEntity category);

    CategoryResponseDto read(@NotNull Long id);

    CategoryEntity readEntity(@NotNull Long id);

    List<CategoryResponseDto> readAll(@Nullable Long id);

    List<CategoryEntity> readAllEntity(@Nullable Long id);

    CategoryResponseDto update(@NotNull Long id, @NotNull @Valid CategoryRequestDto requestDto);

    CategoryEntity updateEntity(@NotNull CategoryEntity category);

    void delete(@NotNull Long id);

}
