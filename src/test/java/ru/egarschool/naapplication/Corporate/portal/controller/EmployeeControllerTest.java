package ru.egarschool.naapplication.Corporate.portal.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import ru.egarschool.naapplication.Corporate.portal.dto.EmployeeDto;
import ru.egarschool.naapplication.Corporate.portal.service.EmployeeServiceImpl;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {

    @Mock
    private EmployeeServiceImpl employeeService;

    @Mock
    private Model model;

    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private EmployeeController employeeController;

    @Test
    void testFindAll() {
        when(employeeService.getAll()).thenReturn(Arrays.asList(new EmployeeDto(), new EmployeeDto()));

        String view = employeeController.findAll(model);

        assertEquals("all_employees", view);
        verify(employeeService).getAll();
        verify(model).addAttribute(eq("employees"), anyList());
    }

    @Test
    void testGetCreateForm() {
        String view = employeeController.getCreateForm(model);

        assertEquals("add_employee", view);
        verify(model).addAttribute(eq("employeeDto"), any(EmployeeDto.class));
    }

    @Test
    void testCreate_ValidDto() {
        when(bindingResult.hasErrors()).thenReturn(false);

        String view = employeeController.create(new EmployeeDto(), bindingResult);

        assertEquals("redirect:/all_employees", view);
        verify(employeeService).create(any(EmployeeDto.class));
    }

    @Test
    void testCreate_InvalidDto() {
        when(bindingResult.hasErrors()).thenReturn(true);

        String view = employeeController.create(new EmployeeDto(), bindingResult);

        assertEquals("add_employee", view);
        verify(employeeService, never()).create(any(EmployeeDto.class));
    }

    @Test
    void testGetInfoEmployee() {
        EmployeeDto employeeDto = new EmployeeDto();
        when(employeeService.getById(1L)).thenReturn(employeeDto);

        String view = employeeController.getInfoEmployee(model, 1L);

        assertEquals("show_employee", view);
        verify(employeeService).getById(1L);
        verify(model).addAttribute("employee", employeeDto);
    }

    @Test
    void testGetProfileEmployee() {
        EmployeeDto employeeDto = new EmployeeDto();
        when(employeeService.getById(1L)).thenReturn(employeeDto);

        String view = employeeController.getProfileEmployee(model, 1L);

        assertEquals("redirect:/all_employees/1", view);
        verify(employeeService).getById(1L);
        verify(model).addAttribute("employee", employeeDto);
    }

    @Test
    void testGetUpdateForm() {
        EmployeeDto employeeDto = new EmployeeDto();
        when(employeeService.getById(1L)).thenReturn(employeeDto);

        String view = employeeController.getUpdateForm(model, 1L);

        assertEquals("edit_employee", view);
        verify(employeeService).getById(1L);
        verify(model).addAttribute("employeeDto", employeeDto);
    }

    @Test
    void testUpdate_Valid() {
        when(bindingResult.hasErrors()).thenReturn(false);

        String view = employeeController.update(new EmployeeDto(), bindingResult, 1L);

        assertEquals("redirect:/all_employees/1", view);
        verify(employeeService).update(any(EmployeeDto.class), eq(1L));
    }

    @Test
    void testUpdate_inValid() {
        when(bindingResult.hasErrors()).thenReturn(true);

        String view = employeeController.update(new EmployeeDto(), bindingResult, 1L);

        assertEquals("edit_employee", view);
        verify(employeeService, never()).update(any(EmployeeDto.class), eq(1L));
    }

    @Test
    void testDeleteEmployee() {
        String view = employeeController.deleteEmployee(1L);

        assertEquals("redirect:/all_employees", view);
        verify(employeeService).delete(1L);
    }
}
