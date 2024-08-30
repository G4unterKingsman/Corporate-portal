package ru.egarschool.naapplication.Corporate.portal.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.egarschool.naapplication.Corporate.portal.dto.OrderDto;
import ru.egarschool.naapplication.Corporate.portal.dto.TaskDto;
import ru.egarschool.naapplication.Corporate.portal.entity.EmployeeEntity;
import ru.egarschool.naapplication.Corporate.portal.entity.OrderEntity;
import ru.egarschool.naapplication.Corporate.portal.entity.TaskEntity;
import ru.egarschool.naapplication.Corporate.portal.mapper.OrderMapper;
import ru.egarschool.naapplication.Corporate.portal.mapper.TaskMapper;
import ru.egarschool.naapplication.Corporate.portal.repository.EmployeeRepo;
import ru.egarschool.naapplication.Corporate.portal.repository.TaskRepo;
import ru.egarschool.naapplication.Corporate.portal.service.impl.TaskService;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepo taskRepo;
    private final EmployeeRepo employeeRepo;


    public List<TaskDto> findAll(){
        List<TaskEntity> orders = taskRepo.findAll();
        return orders.stream()
                .map(e -> TaskMapper.getTaskDto(e))
                .collect(Collectors.toList());
    }
    public TaskDto findById(Long id){
        TaskEntity task =  taskRepo.findById(id).orElseThrow();
        return TaskMapper.getTaskDto(task);
    }

    public TaskDto create(TaskDto taskDto){
        TaskEntity task = TaskMapper.getTask(taskDto);
        taskRepo.save(task);
        return TaskMapper.getTaskDto(task);
    }


    public TaskDto update(TaskDto taskDto, Long id) {
        TaskEntity task = taskRepo.save(TaskMapper.getTask(taskDto));
        return TaskMapper.getTaskDto(task);
    }

}
