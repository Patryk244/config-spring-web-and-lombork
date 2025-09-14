package com.crud.tasks.controller;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import jakarta.transaction.Transactional;
import lombok.*;
import org.springframework.http.*;
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
        System.out.println("Version One");
        return taskMapper.mapToTaskDtoList(tasks);
    }

    @GetMapping(value = "{taskId}")
    public ResponseEntity<TaskDto> getTask(@PathVariable Long taskId) throws TaskNotFoundException {
        return ResponseEntity.ok(taskMapper.mapToTaskDto(service.getTask(taskId)));
    }

    @Transactional
    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId) throws TaskNotFoundException {
        try {
            service.deleteTask(taskId);
        } catch (Exception e) {
            throw new TaskNotFoundException();

        }
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{taskId}")
    public TaskDto updateTask(@PathVariable Long taskId, @RequestBody TaskDto taskDto) {
        return new TaskDto(
                taskId,
                "Edited: " + taskDto.getTitle(),
                "Edited: " + taskDto.getContent()
        );
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createTask(@RequestBody TaskDto taskDto) {
        Task task = taskMapper.mapToTask(taskDto);
        service.saveTask(task);
    }
}