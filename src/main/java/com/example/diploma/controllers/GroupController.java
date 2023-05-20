package com.example.diploma.controllers;

import com.example.diploma.dto.requests.GroupRequestDto;
import com.example.diploma.dto.responses.GroupResponseDto;
import com.example.diploma.services.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${app.baseApi}${app.apiVer1}${app.endpoints.groups}")
@RequiredArgsConstructor
public class GroupController {

    private final GroupService service;

    @GetMapping("{id}")
    public GroupResponseDto get(@PathVariable Long id) {
        return service.read(id);
    }

    @GetMapping("all/{userId}")
    public List<GroupResponseDto> getAllForId(@PathVariable Long userId) {
        return service.readAll(userId);
    }

    @GetMapping("all")
    public List<GroupResponseDto> getAll() {
        return service.readAll(null);
    }

    @PostMapping
    public GroupResponseDto post(@RequestBody GroupRequestDto requestDto) {
        return service.create(requestDto);
    }

    @PutMapping("{id}")
    public GroupResponseDto put(@PathVariable Long id, @RequestBody GroupRequestDto requestDto) {
        return service.update(id, requestDto);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
