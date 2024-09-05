package ru.egarschool.naapplication.Corporate.portal.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.egarschool.naapplication.Corporate.portal.dto.TaskDto;
import ru.egarschool.naapplication.Corporate.portal.entity.EmployeeEntity;
import ru.egarschool.naapplication.Corporate.portal.entity.TaskEntity;
import ru.egarschool.naapplication.Corporate.portal.mapper.TaskMapper;
import ru.egarschool.naapplication.Corporate.portal.repository.EmployeeRepo;
import ru.egarschool.naapplication.Corporate.portal.repository.TaskRepo;
import ru.egarschool.naapplication.Corporate.portal.service.impl.EmployeeService;
import ru.egarschool.naapplication.Corporate.portal.service.impl.TaskService;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepo taskRepo;
    private final TaskMapper taskMapper;
    private final EmployeeRepo employeeRepo;
    private final EmployeeServiceImpl employeeService;
    private final SecurityService securityService;


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

    public TaskDto update(TaskDto taskDto, Long id) {
        TaskEntity task = taskRepo.findById(id).orElseThrow();
        return getTaskDto(taskDto, task);
    }

    private TaskDto getTaskDto(TaskDto taskDto, TaskEntity task) {
        String username = securityService.getCurrentUsername();
        EmployeeEntity employeeWhoGave = employeeRepo.findEmployeeEntityByUserAccount_Username(username).orElseThrow();
        EmployeeEntity employeeWhoGiven = employeeService.findEmployeeByName(taskDto.getWhoGivenTask().getName());
        taskDto.setWhoGaveTask(employeeWhoGave);
        task.setWhoGivenTask(employeeWhoGiven);
        taskMapper.toUpdateOrderFromDto(taskDto,task);

        taskRepo.save(task);
        return taskMapper.toDto(task);
    }

    @Override
    public void delete(Long id) {
        taskRepo.deleteById(id);
    }

    public String getOwnerUsername(Long id) {
        TaskEntity task = taskRepo.findById(id).orElseThrow();
        return task.getWhoGaveTask().getUserAccount().getUsername();
    }
}
