package com.crud.tasks.service;
import com.crud.tasks.domain.TaskNotFoundException;
import lombok.*;

import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.domain.Task;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@RequiredArgsConstructor
public class DbService {

    private final TaskRepository repository;

    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    public Task saveTask(Task task) {
        return repository.save(task);
    }

    public Task getTask(final Long taskId) throws TaskNotFoundException {
        return repository.findById(taskId).orElseThrow(TaskNotFoundException::new);
    }

    public void deleteTask(final Long taskId) {
        repository.removeTaskById(taskId);
    }

}