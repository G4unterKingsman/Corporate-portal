package ru.egarschool.naapplication.Corporate.portal.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.egarschool.naapplication.Corporate.portal.dto.EmployeeDto;
import ru.egarschool.naapplication.Corporate.portal.entity.EmployeeEntity;


@Mapper(componentModel = "spring")
public interface EmployeeMapper{

    EmployeeEntity toEntity(EmployeeDto byName);

    EmployeeDto toDto(EmployeeEntity byName);




    void updateEmployeeFromDTO(EmployeeDto employeeDto, @MappingTarget EmployeeEntity employee);

}
