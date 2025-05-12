package ru.gaunter.app.corporate.portal.service.intefraces;


import ru.gaunter.app.corporate.portal.dto.EmployeeDto;
import ru.gaunter.app.corporate.portal.entity.EmployeeEntity;

import java.util.List;

public interface EmployeeService {
    EmployeeDto getById(Long id);

    List<EmployeeDto> getAll();

    EmployeeDto create(EmployeeDto employeeDto);
    EmployeeDto update(EmployeeDto employeeDto, Long id);

    void delete(Long id);

    EmployeeEntity findEmployeeByName(String name);


    String getOwnerUsername(Long id);

}
