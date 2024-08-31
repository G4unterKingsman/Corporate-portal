package ru.egarschool.naapplication.Corporate.portal.service.impl;


import ru.egarschool.naapplication.Corporate.portal.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    EmployeeDto getById(Long id);
    EmployeeDto getByName(String nane);

    List<EmployeeDto> getAll();

    EmployeeDto create(EmployeeDto employeeDto);
    EmployeeDto update(EmployeeDto employeeDto, Long id);
}
