package ru.egarschool.naapplication.Corporate.portal.controller.rest;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.egarschool.naapplication.Corporate.portal.dto.EmployeeDto;
import ru.egarschool.naapplication.Corporate.portal.service.EmployeeServiceImpl;
import ru.egarschool.naapplication.Corporate.portal.service.impl.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("api/employees")
@RequiredArgsConstructor
public class EmployeeRestController {

    private final EmployeeServiceImpl employeeService;

    @GetMapping
    public List<EmployeeDto> getAllEmployes(){
        return employeeService.getAll();
    }


    @GetMapping("/{id}")
    public EmployeeDto getEmployeeById(@PathVariable Long id){
        return employeeService.getById(id);
    }
}
