package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TaskMapperTestSuite {

    @Autowired
    private TaskMapper taskMapper;

    @Test
    void testMapToTask() {
        // Given
        TaskDto taskDto = new TaskDto(1L, "Test Title", "Test Content");

        // When
        Task task = taskMapper.mapToTask(taskDto);

        // Then
        assertNotNull(task);
        assertEquals(1L, task.getId());
        assertEquals("Test Title", task.getTitle());
        assertEquals("Test Content", task.getContent());
    }

    @Test
    void testMapToTaskDto() {
        // Given
        Task task = new Task(2L, "Another Title", "Another Content");

        // When
        TaskDto taskDto = taskMapper.mapToTaskDto(task);

        // Then
        assertNotNull(taskDto);
        assertEquals(2L, taskDto.getId());
        assertEquals("Another Title", taskDto.getTitle());
        assertEquals("Another Content", taskDto.getContent());
    }

    @Test
    void testMapToTaskDtoList() {
        // Given
        Task task1 = new Task(3L, "List Title 1", "List Content 1");
        Task task2 = new Task(4L, "List Title 2", "List Content 2");
        List<Task> taskList = List.of(task1, task2);

        // When
        List<TaskDto> taskDtoList = taskMapper.mapToTaskDtoList(taskList);

        // Then
        assertNotNull(taskDtoList);
        assertEquals(2, taskDtoList.size());
        assertEquals(3L, taskDtoList.get(0).getId());
        assertEquals("List Title 2", taskDtoList.get(1).getTitle());
    }
}