package ru.egarschool.naapplication.Corporate.portal.service.impl;

import ru.egarschool.naapplication.Corporate.portal.dto.OrderDto;
import ru.egarschool.naapplication.Corporate.portal.dto.TaskDto;
import ru.egarschool.naapplication.Corporate.portal.entity.EmployeeEntity;
import ru.egarschool.naapplication.Corporate.portal.entity.OrderEntity;
import ru.egarschool.naapplication.Corporate.portal.entity.TaskEntity;

import java.util.List;

public interface TaskService {
    public List<TaskEntity> findAll();
    public TaskEntity create(TaskEntity taskEntity, Long idWhoGave, Long idWhoGiven);

    public TaskEntity update(TaskEntity taskEntity, TaskDto taskDto);
}
