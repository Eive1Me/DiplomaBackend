package com.example.diploma.services;

import com.example.diploma.dto.requests.CategoryRequestDto;
import com.example.diploma.dto.responses.CategoryResponseDto;
import com.example.diploma.entities.CategoryEntity;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public interface CategoryService {

    CategoryResponseDto create(@NotNull @Valid CategoryRequestDto requestDto);
    CategoryEntity createEntity(@NotNull CategoryEntity category);

    CategoryResponseDto read(@NotNull Long id);
    CategoryEntity readEntity(@NotNull Long id);

    CategoryResponseDto update(@NotNull Long id, @NotNull @Valid CategoryRequestDto requestDto);
    CategoryEntity updateEntity(@NotNull CategoryEntity category);

    void delete(@NotNull Long id);

}
