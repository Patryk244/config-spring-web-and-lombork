package com.crud.tasks.repository;

import org.springframework.data.repository.CrudRepository;
import com.crud.tasks.domain.Task;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface TaskRepository extends CrudRepository<Task, Integer> {
    @Override
    List<Task> findAll();
    @Override
    Task save(Task task);


    Optional<Task> findById(long id);

    Optional<Task> removeTaskById(long id);
}