package com.example.diploma.services.implementations;

import com.example.diploma.dto.requests.UserRequestDto;
import com.example.diploma.dto.responses.UserResponseDto;
import com.example.diploma.entities.GroupEntity;
import com.example.diploma.entities.UserEntity;
import com.example.diploma.mappers.UserMapper;
import com.example.diploma.repos.GroupRepository;
import com.example.diploma.repos.UserRepository;
import com.example.diploma.services.UserService;
import com.example.diploma.utils.AppException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper mapper;
    private final UserRepository repository;

    private final GroupRepository groupRepository;

    @Override
    public UserResponseDto create(UserRequestDto requestDto) {
        return mapper.toDto(createEntity(mapper.toEntity(requestDto)));
    }

    @Override
    public UserEntity createEntity(UserEntity user) {
        try {
            return repository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new AppException.DatabaseException("Error while adding to database: " + e.getMessage());
        }
    }

    @Override
    public UserResponseDto addGroup(Long userId, Long groupId) {
        UserEntity user = readEntity(userId);
        ArrayList<GroupEntity> groupEntities = new ArrayList<>(user.getGroups().stream().toList());
        try {
            GroupEntity entity = groupRepository.findById(groupId).orElseThrow(
                    () -> new AppException.NotFoundException("Group with id = " + groupId + " is not found"));
            groupEntities.add(entity);
            user.setGroups(groupEntities);
        } catch (NoSuchElementException e) {
            throw new AppException.NotFoundException("Not found: " + e.getMessage());
        }
        return mapper.toDto(repository.save(user));
    }

    @Override
    public UserResponseDto read(Long id) {
        return mapper.toDto(readEntity(id));
    }

    @Override
    public UserEntity readEntity(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new AppException.NotFoundException("User with id = " + id + " is not found"));
    }

    @Override
    public List<UserResponseDto> readAll(Long id) {
        List<UserEntity> entities = readAllEntity(id);
        if (!entities.isEmpty()) {
            return entities.stream().map(mapper::toDto).toList();
        } else {
            throw new AppException.NotFoundException("User not found or empty");
        }
    }

    @Override
    public List<UserEntity> readAllEntity(Long id) {
        return id != null ? repository.findAll().stream().filter(userEntity -> userEntity.getGroups().stream()
                .anyMatch(groupEntity -> groupEntity.getId().compareTo(id) == 0)).toList() : repository.findAll();
    }

    @Override
    public UserResponseDto update(Long id, UserRequestDto requestDto) {
        UserEntity user = mapper.toEntity(requestDto);
        user.setId(id);
        return mapper.toDto(updateEntity(user));
    }

    @Override
    public UserEntity updateEntity(UserEntity user) {
        try {
            return repository.save(user);
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
