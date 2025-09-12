package com.crud.tasks.repository;

import com.crud.tasks.domain.TaskDto;
import org.springframework.data.repository.CrudRepository;
import com.crud.tasks.domain.Task;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface TaskRepository extends CrudRepository<Task, Integer> {
    List<Task> findAll();
    List<Task> findAllById(Long id);
    List<Task> deleteById(Long id);
    boolean existsById(Long id);

}