package com.crud.tasks.controller;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import lombok.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/v1/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final DbService service;
    private final TaskMapper taskMapper;



    @GetMapping
    public List<TaskDto> getTasks() {
        List<Task> tasks = service.getAllTasks();
        return taskMapper.mapToTaskDtoList(tasks);
    }

    @GetMapping(value = "/{taskId}")
    public List<TaskDto> getTask(@PathVariable Long taskId) {
        List<Task> task = service.getTasksById(taskId);
        return taskMapper.mapToTaskDtoList(task);
    }

    @DeleteMapping("/{taskId}")
    public void deleteTask(@PathVariable Long taskId) {
    }

    @PutMapping("/{taskId}")
    public TaskDto updateTask(@PathVariable Long taskId, @RequestBody TaskDto taskDto) {
        return new TaskDto(
                taskId,
                "Edited: " + taskDto.getTitle(),
                "Edited: " + taskDto.getContent()
        );
    }

    @PostMapping
    public void createTask(@RequestBody TaskDto taskDto) {
    }
}