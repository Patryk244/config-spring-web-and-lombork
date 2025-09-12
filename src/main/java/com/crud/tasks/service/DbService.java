package com.crud.tasks.service;
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

    public List<Task> getTasksById(final long id) {
        return repository.findAllById(id);
    }

    public void getToRemoveById(final Long id) {
        repository.deleteAllById(id);
    }
}