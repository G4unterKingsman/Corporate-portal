package ru.egarschool.naapplication.Corporate.portal.mapper;

import ru.egarschool.naapplication.Corporate.portal.controller.EmployeeController;
import ru.egarschool.naapplication.Corporate.portal.dto.EmployeeDto;
import ru.egarschool.naapplication.Corporate.portal.entity.EmployeeEntity;

public class EmployeeMapper {
    public static EmployeeEntity getEmployee(EmployeeEntity employee, EmployeeDto employeeDto) {
        employee.setName(employeeDto.getName());
        employee.setAge(employeeDto.getAge());
        employee.setPost(employeeDto.getPost());
        employee.setJoined(employeeDto.getJoined());
        employee.setWorkExperienceYears(employeeDto.getWorkExperienceYears());
        employee.setDescription(employeeDto.getDescription());
        employee.setEmployOrders(employeeDto.getEmployOrders());
        employee.setTaskForEmploy(employeeDto.getTaskForEmploy());
        employee.setTaskFromEmploy(employeeDto.getTaskFromEmploy());
        return employee;
    }

    public static EmployeeDto getEmployeeDto(EmployeeEntity employee) {
        return EmployeeDto.builder()
                .name(employee.getName())
                .age(employee.getAge())
                .post(employee.getPost())
                .joined(employee.getJoined())
                .workExperienceYears(employee.getWorkExperienceYears())
                .description(employee.getDescription())
                .employOrders(employee.getEmployOrders())
                .taskForEmploy(employee.getTaskForEmploy())
                .taskFromEmploy(employee.getTaskFromEmploy())
                .build();
    }
}
