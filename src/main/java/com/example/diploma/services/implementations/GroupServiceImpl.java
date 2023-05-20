package com.example.diploma.services.implementations;

import com.example.diploma.dto.requests.GroupRequestDto;
import com.example.diploma.dto.responses.GroupResponseDto;
import com.example.diploma.entities.GroupEntity;
import com.example.diploma.mappers.GroupMapper;
import com.example.diploma.repos.GroupRepository;
import com.example.diploma.services.GroupService;
import com.example.diploma.services.UserService;
import com.example.diploma.utils.AppException;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final GroupMapper mapper;
    private final GroupRepository repository;

    private final UserService userService;

    @Override
    public GroupResponseDto create(GroupRequestDto requestDto) {
        return mapper.toDto(createEntity(mapper.toEntity(requestDto, userService.readEntity(requestDto.getUserId()))));
    }

    @Override
    public GroupEntity createEntity(GroupEntity group) {
        try {
            return repository.save(group);
        } catch (DataIntegrityViolationException e) {
            throw new AppException.DatabaseException("Error while adding to database: " + e.getMessage());
        }
    }

    @Override
    public GroupResponseDto read(Long id) {
        return mapper.toDto(readEntity(id));
    }

    @Override
    public GroupEntity readEntity(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new AppException.NotFoundException("Group with id = " + id + " is not found"));
    }

    @Override
    public List<GroupResponseDto> readAll(@Nullable Long id) {
        List<GroupEntity> entities = readAllEntity(id);
        if (!entities.isEmpty()) {
            return entities.stream().map(mapper::toDto).toList();
        } else {
            throw new AppException.NotFoundException("Group not found or empty");
        }
    }

    @Override
    public List<GroupEntity> readAllEntity(@Nullable Long id) {
        return id != null ? repository.findAll().stream().filter(groupEntity -> groupEntity.getUsers().stream()
                .anyMatch(userEntity -> userEntity.getId().compareTo(id) == 0)).toList() : repository.findAll();
    }

    @Override
    public GroupResponseDto update(Long id, GroupRequestDto requestDto) {
        GroupEntity group = mapper.toEntity(requestDto, userService.readEntity(requestDto.getUserId()));
        group.setId(id);
        return mapper.toDto(updateEntity(group));
    }

    @Override
    public GroupEntity updateEntity(GroupEntity group) {
        try {
            return repository.save(group);
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
