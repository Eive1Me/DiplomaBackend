package com.example.diploma.controllers;

import com.example.diploma.dto.requests.StatusRequestDto;
import com.example.diploma.dto.responses.StatusResponseDto;
import com.example.diploma.services.StatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${app.baseApi}${app.apiVer1}${app.endpoints.statuses}")
@RequiredArgsConstructor
public class StatusController {

    private final StatusService service;

    @GetMapping("{id}")
    public StatusResponseDto get(@PathVariable Long id) {
        return service.read(id);
    }

    @GetMapping("all")
    public List<StatusResponseDto> getAll() {
        return service.readAll();
    }

    @PostMapping
    public StatusResponseDto post(@RequestBody StatusRequestDto requestDto) {
        return service.create(requestDto);
    }

    @PutMapping("{id}")
    public StatusResponseDto put(@PathVariable Long id, @RequestBody StatusRequestDto requestDto) {
        return service.update(id, requestDto);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
