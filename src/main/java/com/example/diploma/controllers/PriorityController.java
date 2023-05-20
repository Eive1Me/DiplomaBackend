package com.example.diploma.controllers;

import com.example.diploma.dto.requests.PriorityRequestDto;
import com.example.diploma.dto.responses.PriorityResponseDto;
import com.example.diploma.services.PriorityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${app.baseApi}${app.apiVer1}${app.endpoints.priorities}")
@RequiredArgsConstructor
public class PriorityController {

    private final PriorityService service;

    @GetMapping("{id}")
    public PriorityResponseDto get(@PathVariable Long id) {
        return service.read(id);
    }

    @PostMapping
    public PriorityResponseDto post(@RequestBody PriorityRequestDto requestDto) {
        return service.create(requestDto);
    }

    @PutMapping("{id}")
    public PriorityResponseDto put(@PathVariable Long id, @RequestBody PriorityRequestDto requestDto) {
        return service.update(id, requestDto);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
