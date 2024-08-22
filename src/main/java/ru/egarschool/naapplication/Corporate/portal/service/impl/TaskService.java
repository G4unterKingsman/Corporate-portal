package ru.egarschool.naapplication.Corporate.portal.service.impl;

import ru.egarschool.naapplication.Corporate.portal.entity.EmployeeEntity;
import ru.egarschool.naapplication.Corporate.portal.entity.TaskEntity;

import java.util.List;

public interface TaskService {
    public List<TaskEntity> findAll();
    public TaskEntity create(TaskEntity taskEntity, Long idWhoGave, Long idWhoGiven);
}
