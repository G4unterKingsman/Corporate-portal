package ru.egarschool.naapplication.Corporate.portal.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.egarschool.naapplication.Corporate.portal.dto.TaskDto;
import ru.egarschool.naapplication.Corporate.portal.entity.EmployeeEntity;
import ru.egarschool.naapplication.Corporate.portal.entity.TaskEntity;
import ru.egarschool.naapplication.Corporate.portal.mapper.EmployeeMapper;
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
    private final TaskMapper taskMapper;
    private final EmployeeRepo employeeRepo;


    public List<TaskDto> findAll(){
        List<TaskEntity> orders = taskRepo.findAll();
        return orders.stream()
                .map(e -> taskMapper.toDto(e))
                .collect(Collectors.toList());
    }

    public TaskDto getById(Long id){
        TaskEntity task =  taskRepo.findById(id).orElseThrow();
        return taskMapper.toDto(task);
    }

    public TaskDto create(TaskDto taskDto){
        TaskEntity task = taskMapper.toEntity(taskDto);

        return getTaskDto(taskDto, task);
    }


    @Transactional
    public TaskDto update(TaskDto taskDto, Long id) {
        TaskEntity task = taskRepo.findById(id).orElseThrow();
        return getTaskDto(taskDto, task);
    }

    private TaskDto getTaskDto(TaskDto taskDto, TaskEntity task) {
        EmployeeEntity employeeWhoGave = employeeRepo.findByName(taskDto.getWhoGaveTask().getName());
        EmployeeEntity employeeWhoGiven = employeeRepo.findByName(taskDto.getWhoGivenTask().getName());
        taskMapper.toUpdateOrderFromDto(taskDto,task);
        task.setWhoGaveTask(employeeWhoGave);
        task.setWhoGivenTask(employeeWhoGiven);

        taskRepo.save(task);
        return taskMapper.toDto(task);
    }


}
