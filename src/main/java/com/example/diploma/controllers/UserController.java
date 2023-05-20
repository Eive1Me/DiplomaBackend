package com.example.diploma.controllers;

import com.example.diploma.services.UserService;
import com.example.diploma.dto.requests.UserRequestDto;
import com.example.diploma.dto.responses.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${app.baseApi}${app.apiVer1}${app.endpoints.users}")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping("{id}")
    public UserResponseDto get(@PathVariable Long id) {
        return service.read(id);
    }

    @GetMapping("all/{id}")
    public List<UserResponseDto> getAll(@PathVariable Long id) {
        return service.readAll(id);
    }

    @PostMapping("{userId}/{groupId}")
    public UserResponseDto connectGroup(@PathVariable Long userId, @PathVariable Long groupId) {
        return service.addGroup(userId, groupId);
    }

    @PostMapping
    public UserResponseDto post(@RequestBody UserRequestDto requestDto) {
        return service.create(requestDto);
    }

    @PutMapping("{id}")
    public UserResponseDto put(@PathVariable Long id, @RequestBody UserRequestDto requestDto) {
        return service.update(id, requestDto);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
