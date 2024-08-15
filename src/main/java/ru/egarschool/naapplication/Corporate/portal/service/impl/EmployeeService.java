package ru.egarschool.naapplication.Corporate.portal.service.impl;


import org.springframework.stereotype.Service;
import ru.egarschool.naapplication.Corporate.portal.entity.EmployeeEntity;

import java.util.List;

public interface EmployeeService {
    EmployeeEntity findById(Long id);

    List<EmployeeEntity> findAll();

    EmployeeEntity create(EmployeeEntity employeeEntity);
}
