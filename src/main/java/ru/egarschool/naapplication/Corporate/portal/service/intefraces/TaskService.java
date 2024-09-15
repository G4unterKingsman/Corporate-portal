package ru.egarschool.naapplication.Corporate.portal.service.intefraces;

import ru.egarschool.naapplication.Corporate.portal.dto.TaskDto;

import java.util.List;

public interface TaskService {

    TaskDto getById(Long id);

    List<TaskDto> findAll();
    TaskDto create(TaskDto taskDto);

    TaskDto update(TaskDto taskDto, Long id);

    void delete(Long id);

    void switchStatus(Long id, boolean isCancel);
}
