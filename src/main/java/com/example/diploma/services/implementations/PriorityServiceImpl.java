package com.example.diploma.services.implementations;

import com.example.diploma.dto.requests.PriorityRequestDto;
import com.example.diploma.dto.responses.PriorityResponseDto;
import com.example.diploma.entities.PriorityEntity;
import com.example.diploma.mappers.PriorityMapper;
import com.example.diploma.repos.PriorityRepository;
import com.example.diploma.services.PriorityService;
import com.example.diploma.utils.AppException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PriorityServiceImpl implements PriorityService {

    private final PriorityMapper mapper;
    private final PriorityRepository repository;

    @Override
    public PriorityResponseDto create(PriorityRequestDto requestDto) {
        return mapper.toDto(createEntity(mapper.toEntity(requestDto)));
    }

    @Override
    public PriorityEntity createEntity(PriorityEntity priority) {
        try {
            return repository.save(priority);
        } catch (DataIntegrityViolationException e) {
            throw new AppException.DatabaseException("Error while adding to database: " + e.getMessage());
        }
    }

    @Override
    public PriorityResponseDto read(Long id) {
        return mapper.toDto(readEntity(id));
    }

    @Override
    public PriorityEntity readEntity(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new AppException.NotFoundException("Category with id = " + id + " is not found"));
    }

    @Override
    public PriorityResponseDto update(Long id, PriorityRequestDto requestDto) {
        PriorityEntity priority = mapper.toEntity(requestDto);
        priority.setId(id);
        return mapper.toDto(updateEntity(priority));
    }

    @Override
    public PriorityEntity updateEntity(PriorityEntity priority) {
        try {
            return repository.save(priority);
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
