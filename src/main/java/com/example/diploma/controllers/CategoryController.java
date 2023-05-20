package com.example.diploma.controllers;

import com.example.diploma.dto.requests.CategoryRequestDto;
import com.example.diploma.dto.responses.CategoryResponseDto;
import com.example.diploma.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${app.baseApi}${app.apiVer1}${app.endpoints.categories}")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService service;

    @GetMapping("{id}")
    public CategoryResponseDto get(@PathVariable Long id) {
        return service.read(id);
    }

    @GetMapping("all/{userId}")
    public List<CategoryResponseDto> getAllForId(@PathVariable Long userId) {
        return service.readAll(userId);
    }

    @GetMapping("all")
    public List<CategoryResponseDto> getAll() {
        return service.readAll(null);
    }

    @PostMapping
    public CategoryResponseDto post(@RequestBody CategoryRequestDto requestDto) {
        return service.create(requestDto);
    }

    @PutMapping("{id}")
    public CategoryResponseDto put(@PathVariable Long id, @RequestBody CategoryRequestDto requestDto) {
        return service.update(id, requestDto);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
