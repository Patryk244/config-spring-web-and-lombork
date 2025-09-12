package com.crud.tasks.controller;
import com.crud.tasks.domain.TaskDto;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/v1/tasks")
public class TaskController {

    @RequestMapping(method = RequestMethod.GET, value = "")
    public List<TaskDto> getTasks() {
        return new ArrayList<>();
    }

    @PostMapping("/{id}")
    public TaskDto getTask(Long id) {
        return new TaskDto(1L, "test title", "test_content");
    }

    @DeleteMapping("{/taskId}")
    public void deleteTask(Long taskId) {
    }

    @PutMapping("/{taskDto}")
    public TaskDto updateTask(TaskDto taskDto) {
        return new TaskDto(1L, "Edited test title", "test content");
    }

    @PostMapping("{taskDto}")
    public void createTask(TaskDto taskDto) {}
}