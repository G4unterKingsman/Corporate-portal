package ru.egarschool.naapplication.Corporate.portal.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.egarschool.naapplication.Corporate.portal.dto.TaskDto;
import ru.egarschool.naapplication.Corporate.portal.entity.EmployeeEntity;
import ru.egarschool.naapplication.Corporate.portal.entity.TaskEntity;
import ru.egarschool.naapplication.Corporate.portal.entity.enums.TaskStatus;
import ru.egarschool.naapplication.Corporate.portal.exception.TaskNotFoundException;
import ru.egarschool.naapplication.Corporate.portal.mapper.TaskMapper;
import ru.egarschool.naapplication.Corporate.portal.repository.EmployeeRepo;
import ru.egarschool.naapplication.Corporate.portal.repository.TaskRepo;
import ru.egarschool.naapplication.Corporate.portal.service.intefraces.TaskService;

import java.time.Duration;
import java.time.LocalDateTime;
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
        List<TaskEntity> tasks = taskRepo.findAll();
        return tasks.stream()
                .map(taskMapper::toDto)
                .collect(Collectors.toList());
    }

    public TaskDto getById(Long id){
        TaskEntity task =  taskRepo.findById(id).orElseThrow(
                () ->  new TaskNotFoundException("Задачи с идентификатором " + id + " нет"));
        return taskMapper.toDto(task);
    }

    public TaskDto create(TaskDto taskDto){
        TaskEntity task = taskMapper.toEntity(taskDto);
        String username = securityService.getCurrentUsername();
        EmployeeEntity employeeWhoGave = employeeRepo.findEmployeeEntityByUserAccount_Username(username).orElseThrow(
                () ->  new UsernameNotFoundException("Сотрудник с username " + username + " не найден"));
        EmployeeEntity employeeWhoGiven = employeeService.findEmployeeByName(taskDto.getWhoGivenTask().getName());

        taskDto.setStatus(TaskStatus.CREATED);
        taskDto.setCreated(LocalDateTime.now());
        taskDto.setWhoGaveTask(employeeWhoGave); // сеттим текущего сотрудника-пользователя как хозяина задачи
        task.setWhoGivenTask(employeeWhoGiven); // вручную сеттим сотрудника, кому направлена задача
        taskDto.setCompleted(calculateTimeComplete(taskDto)); // считаем для дто дату дедлайна

        task.setCompleted(calculateTimeComplete(taskDto));

        taskMapper.toUpdateTaskFromDto(taskDto,task);
        taskRepo.save(task);
        return taskDto;
    }

    public TaskDto update(TaskDto taskDto, Long id) {
        TaskEntity task = taskRepo.findById(id).orElseThrow(
                () ->  new TaskNotFoundException("Задачи с идентификатором " + id + " нет"));
        String username = securityService.getCurrentUsername();
        EmployeeEntity employeeWhoGave = employeeRepo.findEmployeeEntityByUserAccount_Username(username).orElseThrow(
                () ->  new UsernameNotFoundException("Сотрудник с username " + username + " не найден"));
        EmployeeEntity employeeWhoGiven = employeeService.findEmployeeByName(taskDto.getWhoGivenTask().getName());

        taskDto.setWhoGaveTask(employeeWhoGave); // сеттим текущего сотрудника-пользователя как хозяина задачи
        task.setWhoGivenTask(employeeWhoGiven); // вручную сеттим сотрудника, кому направлена задача
        taskDto.setCreated(task.getCreated()); // вкручную мапим дату создания
        taskDto.setCompleted(calculateTimeComplete(taskDto)); // считаем для дто дату дедлайна
        task.setCompleted(calculateTimeComplete(taskDto)); // считаем для сущности дату дедлайна
        taskDto.setStatus(task.getStatus());
        taskMapper.toUpdateTaskFromDto(taskDto,task);
        taskRepo.save(task);
        return taskDto;
    }

    @Override
    public void delete(Long id) {
        taskRepo.deleteById(id);
    }

    @Override
    public void switchStatus(Long id, boolean isCancel) {
        TaskEntity task = taskRepo.findById(id).orElseThrow(
                () ->  new TaskNotFoundException("Задачи с идентификатором " + id + " нет"));

        if (task.getStatus() == null && !isCancel) {
            task.setStatus(TaskStatus.CREATED);
        }
        else if (task.getStatus() == TaskStatus.CREATED && !isCancel) {
            task.setStatus(TaskStatus.IN_PROGRESS);
        }
        else if (task.getStatus() == TaskStatus.IN_PROGRESS && !isCancel) {
            task.setStatus(TaskStatus.COMPLETED);
        }
        else if (task.getStatus() == TaskStatus.IN_PROGRESS && isCancel) {
            task.setStatus(TaskStatus.CANCELLED);
        }
        task.setUpdated(LocalDateTime.now());
        taskRepo.save(task);
    }


    public String getOwnerUsername(Long id) {
        TaskEntity task = taskRepo.findById(id).orElseThrow(
                () ->  new TaskNotFoundException("Задачи с идентификатором " + id + " нет"));
        return task.getWhoGaveTask().getUserAccount().getUsername();
    }

    public String getAssigneeUsername(Long id) {
        TaskEntity task = taskRepo.findById(id).orElseThrow(
                () -> new TaskNotFoundException("Задачи с идентификатором " + id + " нет"));
        return task.getWhoGivenTask().getUserAccount().getUsername();
    }


    // метод подсчёта даты завершения  = дата создания + часы отведенные на выполнение
    private LocalDateTime calculateTimeComplete(TaskDto taskDto) {
        return taskDto.getCreated().plusHours(taskDto.getTimeAllowed());
    }

}
