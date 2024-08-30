package ru.egarschool.naapplication.Corporate.portal.mapper;

import ru.egarschool.naapplication.Corporate.portal.dto.OrderDto;
import ru.egarschool.naapplication.Corporate.portal.dto.TaskDto;
import ru.egarschool.naapplication.Corporate.portal.entity.OrderEntity;
import ru.egarschool.naapplication.Corporate.portal.entity.TaskEntity;

public class TaskMapper {
    public static TaskEntity getTask(TaskDto taskDto) {
        TaskEntity task = new TaskEntity();
        task.setId(taskDto.getId());
        task.setTitle(taskDto.getTitle());
        task.setCreated(taskDto.getCreated());
        task.setDescription(taskDto.getDescription());
        task.setWhoGaveTask(taskDto.getWhoGaveTask());
        task.setWhoGivenTask(taskDto.getWhoGivenTask());
        return task;
    }

    public static TaskDto getTaskDto(TaskEntity task) {
        return TaskDto.builder()
                .id(task.getId())
                .title(task.getTitle())
                .created(task.getCreated())
                .description(task.getDescription())
                .whoGaveTask(task.getWhoGaveTask())
                .whoGivenTask(task.getWhoGivenTask())
                .build();
    }
}
