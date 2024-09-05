package ru.egarschool.naapplication.Corporate.portal.controller.rest;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.egarschool.naapplication.Corporate.portal.dto.EmployeeDto;
import ru.egarschool.naapplication.Corporate.portal.service.EmployeeServiceImpl;

import java.util.List;

@RestController
@RequestMapping("api/employees")
@RequiredArgsConstructor
public class EmployeeRestController {

    private final EmployeeServiceImpl employeeService;
    @GetMapping
    public List<EmployeeDto> getAllEmployees(){
        return employeeService.getAll();
    }

    @GetMapping("/{id}")
    public EmployeeDto getEmployeeById(@PathVariable Long id){
        return employeeService.getById(id);
    }

    @PostMapping
    public EmployeeDto createEmployee(@RequestBody EmployeeDto employeeDto) {
        return employeeService.create(employeeDto);
    }

    @PutMapping("/{id}")
    public EmployeeDto updateEmployee(@PathVariable Long id, @RequestBody EmployeeDto employeeDto) {
        return employeeService.update(employeeDto, id);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.delete(id);
    }

}
