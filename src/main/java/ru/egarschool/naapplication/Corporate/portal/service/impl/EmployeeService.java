package ru.egarschool.naapplication.Corporate.portal.service.impl;


import org.springframework.stereotype.Service;
import ru.egarschool.naapplication.Corporate.portal.dto.EmployeeDto;
import ru.egarschool.naapplication.Corporate.portal.entity.EmployeeEntity;

import java.util.List;

public interface EmployeeService {
    EmployeeDto findById(Long id);

    List<EmployeeDto> getAll();

    EmployeeDto create(EmployeeDto employeeDto);
    EmployeeDto update(EmployeeDto employeeDto, Long id);
}
