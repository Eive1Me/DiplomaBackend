package com.example.diploma.services.implementations;

import com.example.diploma.services.*;
import com.example.diploma.entities.TaskEntity;
import com.example.diploma.mappers.TaskMapper;
import com.example.diploma.repos.TaskRepository;
import com.example.diploma.dto.requests.TaskRequestDto;
import com.example.diploma.dto.responses.TaskResponseDto;
import com.example.diploma.utils.AppException;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskMapper mapper;
    private final TaskRepository repository;

    private final UserService userService;
    private final CategoryService categoryService;
    private final PriorityService priorityService;
    private final GroupService groupService;
    private final StatusService statusService;

    @Override
    public TaskResponseDto create(TaskRequestDto requestDto) {
        TaskEntity task = mapper.toEntity(
                requestDto,
                userService.readEntity(requestDto.getUserId()),
                categoryService.readEntity(requestDto.getCategoryId()),
                priorityService.readEntity(requestDto.getPriorityId()),
                groupService.readEntity(requestDto.getGroupId()),
                statusService.readEntity(requestDto.getStatusId()));
        return mapper.toDto(createEntity(task));
    }

    @Override
    public TaskEntity createEntity(TaskEntity task) {
        try {
            return repository.save(task);
        } catch (DataIntegrityViolationException e) {
            throw new AppException.DatabaseException("Error while adding to database: " + e.getMessage());
        }
    }

    @Override
    public TaskResponseDto read(Long id) {
        return mapper.toDto(readEntity(id));
    }

    @Override
    public TaskEntity readEntity(Long id) {
        return repository.findById(id).orElseThrow(() -> new AppException.NotFoundException("Task with id = " + id + " is not found"));
    }

    @Override
    public TaskResponseDto update(Long id, TaskRequestDto requestDto) {
        TaskEntity task = mapper.toEntity(
                requestDto,
                userService.readEntity(requestDto.getUserId()),
                categoryService.readEntity(requestDto.getCategoryId()),
                priorityService.readEntity(requestDto.getPriorityId()),
                groupService.readEntity(requestDto.getGroupId()),
                statusService.readEntity(requestDto.getStatusId()));
        task.setId(id);
        return mapper.toDto(updateEntity(task));
    }

    @Override
    public TaskEntity updateEntity(TaskEntity task) {
        try {
            return repository.save(task);
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
