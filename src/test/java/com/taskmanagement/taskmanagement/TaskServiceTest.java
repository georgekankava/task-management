package com.taskmanagement.taskmanagement;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.Mockito.*;

/**
 * Created by georgekankava on 30.07.17.
 */
@RunWith(MockitoJUnitRunner.class)
public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    @Test
    public void testCreateTask() {
        Task task = taskService.createTask("name", "description");
        assertThat(task.getName()).isNotNull();
        assertThat(task.getDescription()).isNotNull();
    }

    @Test
    public void testEditTask() {
        Task task = mock(Task.class);
        when(task.getId()).thenReturn(1);
        when(task.getName()).thenReturn("name1");
        when(task.getDescription()).thenReturn("description1");
        when(taskRepository.findOne(1)).thenReturn(task);
        Task editedTask = taskService.editTask(1, "name1", "description1");
        assertThat(task.getId()).isEqualTo(1);
        assertThat(task.getName()).isEqualTo("name1");
        assertThat(task.getDescription()).isEqualTo("description1");
    }

    @Test
    public void testCompleteTask() {
        Task task1 = mock(Task.class);
        Task task2 = mock(Task.class);
        when(task1.isCompleted()).thenReturn(false);
        when(task1.getCompletedDate()).thenReturn(new Date());
        when(task2.isCompleted()).thenReturn(true);
        when(taskRepository.findOne(1)).thenReturn(task1);
        when(taskRepository.findOne(2)).thenReturn(task2);
        taskService.completeTask(1);
        assertThat(task1.getCompletedDate()).isNotNull();
        taskService.completeTask(2);
        assertThat(task2.getCompletedDate()).isNull();
    }
}
