package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DbServiceTest {

    @InjectMocks
    private DbService dbService;

    @Mock
    private TaskRepository repository;

    @Test
    void shouldGetAllTasks() {
        // Given
        Task task = new Task(1L, "Test task", "Test content");
        List<Task> taskList = List.of(task);
        when(repository.findAll()).thenReturn(taskList);

        // When
        List<Task> allTasks = dbService.getAllTasks();

        // Then
        assertNotNull(allTasks);
        assertEquals(1, allTasks.size());
    }

    @Test
    void shouldSaveTask() {
        // Given
        Task task = new Task(1L, "Test task", "Test content");
        when(repository.save(task)).thenReturn(task);

        // When
        Task savedTask = dbService.saveTask(task);

        // Then
        assertNotNull(savedTask);
        assertEquals("Test task", savedTask.getTitle());
    }

    @Test
    void shouldGetTask() {
        // Given
        Task task = new Task(1L, "Test task", "Test content");
        when(repository.findById(1L)).thenReturn(Optional.of(task));

        // When
        Task foundTask = dbService.getTask(1L);

        // Then
        assertNotNull(foundTask);
        assertEquals("Test task", foundTask.getTitle());
    }
}
