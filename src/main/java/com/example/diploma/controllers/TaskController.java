package com.example.diploma.controllers;

import com.example.diploma.dto.requests.TaskRequestDto;
import com.example.diploma.dto.responses.TaskResponseDto;
import com.example.diploma.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${app.baseApi}${app.apiVer1}${app.endpoints.tasks}")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService service;

    @GetMapping("{id}")
    public TaskResponseDto get(@PathVariable Long id) {
        return service.read(id);
    }

    @PostMapping
    public TaskResponseDto post(@RequestBody TaskRequestDto requestDto) {
        return service.create(requestDto);
    }

    @GetMapping("all/{userId}")
    public List<TaskResponseDto> getAllForId(@PathVariable Long userId) {
        return service.readAll(userId);
    }

    @GetMapping("all")
    public List<TaskResponseDto> getAll() {
        return service.readAll(null);
    }

    @PutMapping("{id}")
    public TaskResponseDto put(@PathVariable Long id, @RequestBody TaskRequestDto requestDto) {
        return service.update(id, requestDto);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
