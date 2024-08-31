package ru.egarschool.naapplication.Corporate.portal.service.impl;

import ru.egarschool.naapplication.Corporate.portal.dto.TaskDto;

import java.util.List;

public interface TaskService {

    public TaskDto getById(Long id);

    public List<TaskDto> findAll();
    public TaskDto create(TaskDto taskDto);

    public TaskDto update(TaskDto taskDto, Long id);
}
