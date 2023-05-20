package com.example.diploma.mappers;

import com.example.diploma.dto.requests.CategoryRequestDto;
import com.example.diploma.dto.responses.CategoryResponseDto;
import com.example.diploma.entities.CategoryEntity;
import com.example.diploma.entities.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryMapper {

    private final UserMapper userMapper;

    public CategoryEntity toEntity(CategoryRequestDto categoryRequestDto, UserEntity userEntity) {
        return CategoryEntity.builder()
                .name(categoryRequestDto.getName())
                .colour(categoryRequestDto.getColour())
                .desc(categoryRequestDto.getDesc())
                .userId(userEntity)
                .build();
    }

    public CategoryResponseDto toDto(CategoryEntity category) {
        return CategoryResponseDto.builder()
                .id(category.getId())
                .name(category.getName())
                .colour(category.getColour())
                .desc(category.getDesc())
                .userId(userMapper.toDto(category.getUserId()))
                .build();
    }

}
