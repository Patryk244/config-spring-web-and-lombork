package com.crud.tasks.service;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.domain.TaskNotFoundException;
import jakarta.transaction.Transactional;
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

    @Transactional
    public void getToRemoveById(final Long id) {
        if (!repository.existsById(id)) {
            throw new TaskNotFoundException(id);
        }
        repository.deleteById(id);
    }

    @Transactional
    public void save(Task task) {
        repository.save(task);
    };

}