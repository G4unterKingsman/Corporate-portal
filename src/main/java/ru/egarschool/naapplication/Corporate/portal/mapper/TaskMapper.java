package ru.egarschool.naapplication.Corporate.portal.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.egarschool.naapplication.Corporate.portal.dto.TaskDto;
import ru.egarschool.naapplication.Corporate.portal.entity.TaskEntity;



@Mapper(componentModel = "spring")
public interface TaskMapper {

    TaskEntity toEntity(TaskDto taskDto);

    TaskDto toDto(TaskEntity task) ;


    // не мапить поле whoGivenTask, присваивание вручную в сервисе
    @Mapping(source = "whoGivenTask", target = "whoGivenTask.id", ignore = true)
    void toUpdateOrderFromDto(TaskDto byName, @MappingTarget TaskEntity order);
}
