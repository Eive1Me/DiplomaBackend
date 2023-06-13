package com.example.diploma.services.implementations;

import com.example.diploma.dto.requests.TaskRequestDto;
import com.example.diploma.dto.responses.TaskResponseDto;
import com.example.diploma.entities.GroupEntity;
import com.example.diploma.entities.TaskEntity;
import com.example.diploma.mappers.TaskMapper;
import com.example.diploma.rearranging.GeneticAlgorithm;
import com.example.diploma.repos.TaskRepository;
import com.example.diploma.services.*;
import com.example.diploma.utils.AppException;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

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
        GroupEntity group = null;
        if (requestDto.getGroupId() != null) {
            group = groupService.readEntity(requestDto.getGroupId());
        }
        TaskEntity task = mapper.toEntity(
                requestDto,
                userService.readEntity(requestDto.getUserId()),
                categoryService.readEntity(requestDto.getCategoryId()),
                priorityService.readEntity(requestDto.getPriorityId()),
                group,
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
        return repository.findById(id)
                .orElseThrow(() -> new AppException.NotFoundException("Task with id = " + id + " is not found"));
    }

    @Override
    public List<TaskResponseDto> readAll(@Nullable Long id) {
        List<TaskEntity> entities = readAllEntity(id);
        if (!entities.isEmpty()) {
            return entities.stream().map(mapper::toDto).toList();
        } else {
            throw new AppException.NotFoundException("Task not found or empty");
        }
    }

    @Override
    public List<TaskEntity> readAllEntity(@Nullable Long id) {
        return id != null ? repository.findAll().stream()
                .filter(taskEntity -> taskEntity.getUserId().getId().compareTo(id) == 0)
                .toList() : repository.findAll();
    }

    @Override
    public TaskResponseDto update(Long id, TaskRequestDto requestDto) {
        GroupEntity group = null;
        if (requestDto.getGroupId() != null) {
            group = groupService.readEntity(requestDto.getGroupId());
        }
        TaskEntity task = mapper.toEntity(
                requestDto,
                userService.readEntity(requestDto.getUserId()),
                categoryService.readEntity(requestDto.getCategoryId()),
                priorityService.readEntity(requestDto.getPriorityId()),
                group,
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
    public List<TaskResponseDto> rearrange(Long id){
        List<TaskEntity> tasks = readAllEntity(id);
        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm();
        List<TaskEntity> newTasks = geneticAlgorithm.runGeneticAlgorithm(tasks).getTasks();
        if (!newTasks.isEmpty()) {
            return newTasks.stream().map(mapper::toDto).toList();
        } else {
            throw new AppException.NotFoundException("User with specified id not found or his taskList is empty");
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
