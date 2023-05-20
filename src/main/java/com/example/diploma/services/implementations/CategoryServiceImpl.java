package com.example.diploma.services.implementations;

import com.example.diploma.dto.requests.CategoryRequestDto;
import com.example.diploma.dto.responses.CategoryResponseDto;
import com.example.diploma.entities.CategoryEntity;
import com.example.diploma.mappers.CategoryMapper;
import com.example.diploma.repos.CategoryRepository;
import com.example.diploma.services.CategoryService;
import com.example.diploma.services.UserService;
import com.example.diploma.utils.AppException;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryMapper mapper;
    private final CategoryRepository repository;

    private final UserService userService;

    @Override
    public CategoryResponseDto create(CategoryRequestDto requestDto) {
        return mapper.toDto(createEntity(mapper.toEntity(requestDto, userService.readEntity(requestDto.getUserId()))));
    }

    @Override
    public CategoryEntity createEntity(CategoryEntity category) {
        try {
            return repository.save(category);
        } catch (DataIntegrityViolationException e) {
            throw new AppException.DatabaseException("Error while adding to database: " + e.getMessage());
        }
    }

    @Override
    public CategoryResponseDto read(Long id) {
        return mapper.toDto(readEntity(id));
    }

    @Override
    public CategoryEntity readEntity(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new AppException.NotFoundException("Category with id = " + id + " is not found"));
    }

    @Override
    public List<CategoryResponseDto> readAll(@Nullable Long id) {
        List<CategoryEntity> entities = readAllEntity(id);
        if (!entities.isEmpty()) {
            return entities.stream().map(mapper::toDto).toList();
        } else {
            throw new AppException.NotFoundException("Category not found or empty");
        }
    }

    @Override
    public List<CategoryEntity> readAllEntity(@Nullable Long id) {
        return id != null ? repository.findAll().stream()
                .filter(categoryEntity -> categoryEntity.getUserId().getId().compareTo(id) == 0)
                .toList() : repository.findAll();
    }

    @Override
    public CategoryResponseDto update(Long id, CategoryRequestDto requestDto) {
        CategoryEntity category = mapper.toEntity(requestDto, userService.readEntity(requestDto.getUserId()));
        category.setId(id);
        return mapper.toDto(updateEntity(category));
    }

    @Override
    public CategoryEntity updateEntity(CategoryEntity category) {
        try {
            return repository.save(category);
        } catch (DataIntegrityViolationException e) {
            throw new AppException.DatabaseException("Error while updating database: " + e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        try {
            repository.delete(readEntity(id));
        } catch (DataIntegrityViolationException e) {
            throw new AppException.DatabaseException("Error while deleting User record: " + e.getMessage());
        }
    }
}
