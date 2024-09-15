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


    /**
     * Обновление сотрудника из DTO без поля userAccount, т.к оно задаётся вручную в сервисе
     */
    @Mapping(source = "userAccount", target = "userAccount.id", ignore = true)
    void updateEmployeeFromDTO(EmployeeDto employeeDto, @MappingTarget EmployeeEntity employee);

}
