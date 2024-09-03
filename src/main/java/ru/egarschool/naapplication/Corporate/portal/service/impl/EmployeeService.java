package ru.egarschool.naapplication.Corporate.portal.service.impl;


import ru.egarschool.naapplication.Corporate.portal.dto.EmployeeDto;
import ru.egarschool.naapplication.Corporate.portal.entity.EmployeeEntity;

import java.util.List;

public interface EmployeeService {
    EmployeeDto getById(Long id);
    EmployeeDto getByName(String nane);

    List<EmployeeDto> getAll();

    EmployeeDto create(EmployeeDto employeeDto);
    EmployeeDto update(EmployeeDto employeeDto, Long id);

    void delete(Long id);

    EmployeeEntity findByUsername(String username);
}
