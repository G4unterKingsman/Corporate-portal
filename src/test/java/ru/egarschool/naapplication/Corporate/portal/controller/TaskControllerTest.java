package ru.egarschool.naapplication.Corporate.portal.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import ru.egarschool.naapplication.Corporate.portal.dto.TaskDto;
import ru.egarschool.naapplication.Corporate.portal.service.TaskServiceImpl;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskControllerTest {

    @Mock
    private Model model;
    @Mock
    private BindingResult bindingResult;

    @Mock
    private TaskServiceImpl taskService;

    @InjectMocks
    private TaskController taskController;

    @Test
    void TestGetAllTasks() {
        when(taskService.findAll()).thenReturn(Arrays.asList(new TaskDto(), new TaskDto()));

        String view = taskController.getAllTasks(model);

        assertEquals("all_tasks", view);
        verify(taskService).findAll();
        verify(model).addAttribute(eq("tasks"), anyList());
    }


    @Test
    void TestGetAddTaskForm() {
        String view = taskController.getAddTaskForm(model);

        assertEquals("add_task", view);
        verify(model).addAttribute(eq("task"), any(TaskDto.class));
    }

    @Test
    void TestCreateTask_valid() {
        when(bindingResult.hasErrors()).thenReturn(false);

        String view = taskController.create(new TaskDto(), bindingResult);

        assertEquals("redirect:/all_tasks", view);
        verify(taskService).create(any(TaskDto.class));
    }

    @Test
    void TestCreateTask_inValid() {
        when(bindingResult.hasErrors()).thenReturn(true);

        String view = taskController.create(new TaskDto(), bindingResult);

        assertEquals("add_task", view);
        verify(taskService, never()).create(any(TaskDto.class));
    }

    @Test
    void TestGetInfoTask() {
        TaskDto taskDto = new TaskDto();
        when(taskService.getById(1L)).thenReturn(taskDto);

        String view = taskController.getInfoTask(model, 1L);

        assertEquals("show_task", view);

        verify(taskService).getById(1L);
        verify(model).addAttribute(eq("task"), any(TaskDto.class));
    }

    @Test
    void TestGetUpdateTaskForm() {
        TaskDto taskDto = new TaskDto();
        when(taskService.getById(1L)).thenReturn(taskDto);

        String view = taskController.getEditForm(model, 1L);

        assertEquals("edit_task", view);
        verify(taskService).getById(1L);
        verify(model).addAttribute("taskDto", taskDto);
    }

    @Test
    void testUpdateTask_Valid() {
        when(bindingResult.hasErrors()).thenReturn(false);

        String view = taskController.update(new TaskDto(), bindingResult, 1L, model);

        assertEquals("redirect:/all_tasks/1", view);
        verify(taskService).update(any(TaskDto.class), eq(1L));
    }

    @Test
    void testUpdateTask_inValid() {
        when(bindingResult.hasErrors()).thenReturn(true);

        String view = taskController.update(new TaskDto(), bindingResult, 1L, model);

        assertEquals("edit_task", view);
        verify(taskService, never()).update(any(TaskDto.class), eq(1L));
    }

    @Test
    void TestDeleteTask() {
        String view = taskController.deleteTask(1L);

        assertEquals("redirect:/all_tasks", view);
        verify(taskService).delete(1L);
    }
}