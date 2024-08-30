package ru.egarschool.naapplication.Corporate.portal.mapper;

import ru.egarschool.naapplication.Corporate.portal.dto.EmployeeDto;
import ru.egarschool.naapplication.Corporate.portal.entity.EmployeeEntity;

public class EmployeeMapper{
    public static EmployeeEntity getEmployee(EmployeeDto employeeDto) {
        if (employeeDto == null) {return null;}
        EmployeeEntity employee = new EmployeeEntity();
        employee.setId(employeeDto.getId());
        employee.setName(employeeDto.getName());
        employee.setAge(employeeDto.getAge());
        employee.setPost(employeeDto.getPost());
        employee.setJoined(employeeDto.getJoined());
        employee.setWorkExperienceYears(employeeDto.getWorkExperienceYears());
        employee.setDescription(employeeDto.getDescription());
        employee.setTaskFromEmploy(employeeDto.getTaskFromEmploy());
        employee.setTaskForEmploy(employeeDto.getTaskForEmploy());
        employee.setEmployOrders(employeeDto.getEmployOrders());
        return employee;
    }

    public static EmployeeDto getEmployeeDto(EmployeeEntity employee) {
        if (employee == null) {return null;}

        return EmployeeDto.builder()
                .id(employee.getId())
                .name(employee.getName())
                .age(employee.getAge())
                .post(employee.getPost())
                .joined(employee.getJoined())
                .workExperienceYears(employee.getWorkExperienceYears())
                .description(employee.getDescription())
                .taskFromEmploy(employee.getTaskFromEmploy())
                .taskForEmploy(employee.getTaskForEmploy())
                .employOrders(employee.getEmployOrders())
                .build();
    }
}
