package com.example.diploma.services.implementations;

import com.example.diploma.entities.StatusEntity;
import com.example.diploma.mappers.StatusMapper;
import com.example.diploma.services.StatusService;
import com.example.diploma.repos.StatusRepository;
import com.example.diploma.dto.requests.StatusRequestDto;
import com.example.diploma.dto.responses.StatusResponseDto;
import com.example.diploma.utils.AppException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatusServiceImpl implements StatusService {

    private final StatusMapper mapper;
    private final StatusRepository repository;

    @Override
    public StatusResponseDto create(StatusRequestDto requestDto) {
        return mapper.toDto(createEntity(mapper.toEntity(requestDto)));
    }

    @Override
    public StatusEntity createEntity(StatusEntity status) {
        try {
            return repository.save(status);
        } catch (DataIntegrityViolationException e) {
            throw new AppException.DatabaseException("Error while adding to database: " + e.getMessage());
        }
    }

    @Override
    public StatusResponseDto read(Long id) {
        return mapper.toDto(readEntity(id));
    }

    @Override
    public StatusEntity readEntity(Long id) {
        return repository.findById(id).orElseThrow(() -> new AppException.NotFoundException("Status with id = " + id + " is not found"));
    }

    @Override
    public StatusResponseDto update(Long id, StatusRequestDto requestDto) {
        StatusEntity status = mapper.toEntity(requestDto);
        status.setId(id);
        return mapper.toDto(updateEntity(status));
    }

    @Override
    public StatusEntity updateEntity(StatusEntity status) {
        try {
            return repository.save(status);
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
