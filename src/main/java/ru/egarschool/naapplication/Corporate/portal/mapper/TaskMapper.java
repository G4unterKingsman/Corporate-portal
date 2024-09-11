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


    // ? тоже самое что и toEntity, но не мапится поле whoGivenTask, присваивание вручную в сервисе
    @Mapping(source = "whoGivenTask", target = "whoGivenTask.id", ignore = true)
    @Mapping(source = "completed", target = "completed", ignore = true)
    @Mapping(source = "created", target = "created", ignore = true)
    void toUpdateTaskFromDto(TaskDto byName, @MappingTarget TaskEntity task);
}
